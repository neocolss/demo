#!/bin/bash

JENKINS_URL="http://ret1crtcvgctp07.hosting.xxxx.cloud/jenkins"
#JENKINS_USER=sa_tiers_payant
#JENKINS_USER_TOKEN=9JpaA2mE8jxKD7u6Ku62
JENKINS_USER=admin
JENKINS_USER_TOKEN=110e039de67ceb67dee3209d338275bace

for arg in "$@"
do
    case "$arg" in
    $1)		#echo "jobname : $arg" 
			jobname=$arg
            ;;
    *)		#echo "param $arg" #parametres attendus sous la forme de : "cle1=valeur1 cle2=valeur2 ..."
			#additionalParameters=" $additionalParameters -p $arg "
			additionalParameters="$additionalParameters&$arg"
            ;;
    esac
done

#jobname="Batch400RapprochementAccreditation_SP_ACC_FAB84"
if [ -z "$jobname" ]; then
  echo "jobname obligatoire !"
  exit 1
fi

WGET="wget --auth-no-challenge --http-user=$JENKINS_USER --http-password=$JENKINS_USER_TOKEN --secure-protocol=TLSv1  --no-proxy "


function wait_jenkins {

  # Paramètres
  local url=$1
  local wait_tag=$2
  local wait_pattern=$3
  local finish_tag=$4
  local finish_pattern=$5
  local delay=${6:-1}
  local return_variable=${7}

  local waiting=true

  while $waiting; do
    sleep 1
    jenkins_information=$($WGET -O- $url | cat)
	 
    wait_value=$(echo "${jenkins_information}" | grep "${wait_tag}" | sed -e "s:.*<${wait_tag}>::" -e "s:</${wait_tag}>.*::")
    finish_value=$(echo "${jenkins_information}" | grep "${finish_tag}" | sed -e "s:.*<${finish_tag}>::" -e "s:</${finish_tag}>.*::")


    waiting=false
    # Test pour savoir si on a terminé
    if echo "${finish_value}" | grep -E "^${finish_pattern}\$" >/dev/null; then
      # Retour de variable
      if [ -n "${return_variable}" ]; then
        eval ${return_variable}=${finish_value}
      fi
      # Sortie OK
      return 0
    else
      # Test pour vérifier qu'on continue l'attente
      if echo "${wait_value}" | grep -E "^${wait_pattern}\$" >/dev/null; then
        waiting=true
        # echo "${wait_tag}: ${wait_pattern} / ${wait_value}"
        # echo "${finish_tag}: ${finish_pattern} / ${finish_value}"
      else
        echo "Erreur ${wait_tag} == ${wait_value}"
        echo "Erreur ${finish_tag} != ${finish_value}"
        # Sortie KO
        return 1
      fi
    fi
  done
}

echo "jobname=$jobname"
echo "additionalParameters=$additionalParameters"
echo "JENKINS_URL=$JENKINS_URL"


#crumb=$($WGET -O- ${JENKINS_URL}/crumbIssuer/api/xml?xpath=concat\(//crumbRequestField,\":\",//crumb\))
#echo "crumb:$crumb"

if [ -z "$additionalParameters" ]; then
  echo "target: $JENKINS_URL/job/$jobname/build"
    post_build=$($WGET --header=$crumb $JENKINS_URL'/job/'$jobname'/build' --post-data="" --server-response -O/dev/null 2>&1)


else
  echo "target: $JENKINS_URL/job/$jobname/buildWithParameters?$additionalParameters"
  post_build=$($WGET --header=$crumb $JENKINS_URL'/job/'$jobname'/buildWithParameters?'$additionalParameters --post-data="" --server-response -O/dev/null  2>&1)

fi

echo "post_build=$post_build"

# Lecture de l'identifiant en file d'attente Jenkins
queue_location=$(echo "$post_build" | grep Location: | sed -e 's/^[[:space:]]*//' | cut -d" " -f2 | tr -d '\r')

echo "queue_location :[$queue_location]"
if [ -z "$queue_location" ]; then
    echo "Jenkins ($JENKINS_URL) ou job ($jobname) introuvable "
	exit 1
fi

# Attente jusqu'au début du build (file d'attente)
wait_jenkins "$queue_location/api/xml" "inQueueSince" ".*" "number" "[0-9]+" 1 "build_id" && \

# Attente pendant le build
wait_jenkins "$JENKINS_URL/job/$jobname/${build_id}/api/xml" "building" "true" "result" "SUCCESS"
rc=$?

if [ $rc -ne 0 ]; then
  echo "Job $jobname" " : ERREUR"
else
  echo "Job $jobname" " : terminé OK"
fi

exit $rc




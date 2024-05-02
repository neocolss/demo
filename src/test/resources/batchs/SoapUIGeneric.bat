@ECHO off
SET NAME=%~nx0
SET "REPORT_DIR=%~dp0%4"
ECHO "REPORT_DIR=%REPORT_DIR%"
SET "PROJECT_DIR=%1"
SET "SUITE_NAME=%2"
SET "TEST_NAME=%3"
ECHO "TEST_NAME=%TEST_NAME%"

SET TITLETEXT=Script %NAME% running in %PROJECT_DIR%
TITLE %TITLETEXT%
ECHO %TITLETEXT%

REM Update ready-api-ui-3.3.0.jar version if you update ReadyAPI version
REM SOAPUI_HOME is an environment variable for readyapi home folder
ECHO Checking if ^"%SOAPUI_HOME%\bin\ready-api-ui-3.3.0.jar^" exists.
IF EXIST "%SOAPUI_HOME%\bin\ready-api-ui-3.3.0.jar" (
  SET "PRO_ARGS=-F"XML" -R"JUnit-Style HTML Report""
) ELSE (
  SET PRO_ARGS=
)

REM The TestCase TestSuite is the main suite to validate these tests
SET "ARGUMENTS=-s"%SUITE_NAME%" -c"%TEST_NAME%" -r -M -a -j"
SET "ARGUMENTS=%ARGUMENTS% -f"%REPORT_DIR%" %PRO_ARGS% %PROJECT_DIR%"
ECHO "ARGUMENTS____=%ARGUMENTS%"

ECHO -----------------------------------------------------
ECHO -- Running soapui testcase runner ...
ECHO -- Project dir: %PROJECT_DIR%
ECHO -- SoapUI home: %SOAPUI_HOME%
ECHO -- JAVA_HOME: %JAVA_HOME%
ECHO -- PRO_ARGS: %PRO_ARGS%
ECHO -- Command: [%SOAPUI_HOME%\bin\testrunner.bat %ARGUMENTS%]
ECHO -----------------------------------------------------
ECHO.
ECHO Running...
CALL "%SOAPUI_HOME%\bin\testrunner.bat" %ARGUMENTS%

exit
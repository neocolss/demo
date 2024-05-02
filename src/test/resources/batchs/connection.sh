@echo off
# Generate the script. Will overwrite any existing temp.txt
echo open ret1crtcvgctp01.production.net> temp.txt
echo ymourtaji>> temp.txt
echo NEOcol1005@>> temp.txt
echo cd /XCHANGE/CVG-ACCR/files/IS/out/EXP/ACC>> temp.txt
echo ls -la>> temp.txt
echo pause>> temp.txt
echo disconnect>> temp.txt
echo quit>> temp.txt

# Launch FTP and pass it the script
ftp -s:temp.txt

# Clean up.
del temp.txt
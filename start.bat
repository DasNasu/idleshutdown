@echo off
cd "C:\Users\Nils Alex\Desktop\MC-Server\Server 1.9"
color 0b


:start
"C:\Program Files\Java\jre1.8.0_162\bin\java.exe" -Xmx6G -Xms2G -jar spigot-1.9.jar nogui
set CURRENTTIME=%TIME::=.%
set CURRENTTIME=%CURRENTTIME:~0,8%
set ARCHIVNAME=Backup-%DATE%-%CURRENTTIME%
mkdir "C:\Users\Nils Alex\Desktop\MC-Server\Backups\Server 1.9\%ARCHIVNAME%"
xcopy "C:\Users\Nils Alex\Desktop\MC-Server\Server 1.9" "C:\Users\Nils Alex\Desktop\MC-Server\Backups\Server 1.9\%ARCHIVNAME%\" /Y /E /S

exit
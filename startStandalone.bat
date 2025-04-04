set ProjectPath=%~dp0
java -jar "%ProjectPath%\libraries\selenium-server-4.21.0.jar" standalone --selenium-manager true
pause
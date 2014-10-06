
@SET MODULES_DIR=modules
@SET WORKING_DIR=%~dp0

@IF NOT EXIST "%WORKING_DIR%%MODULES_DIR%" mkdir "%WORKING_DIR%%MODULES_DIR%"

@echo Strating App...
@java -cp %WORKING_DIR%module-execution-container-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.classloader.shell.Shell %WORKING_DIR%%MODULES_DIR%
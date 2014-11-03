
@SET WORKING_DIR=%~dp0
@SET SCANNING_THREADS_COUNT=2

@IF NOT EXIST "%WORKING_DIR%%MODULES_DIR%" mkdir "%WORKING_DIR%%MODULES_DIR%"

@echo Strating App...
@java -cp %WORKING_DIR%folder-statistics-app-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.multithreading.folderstatistics.shell.Shell %SCANNING_THREADS_COUNT%
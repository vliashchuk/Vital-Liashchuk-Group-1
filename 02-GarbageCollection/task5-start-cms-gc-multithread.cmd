@SET JAR_PATH=../

@SET MODULES_DIR=modules
@SET WORKING_DIR=%~dp0/../01-Classloading/module-execution-container/target/

@Echo Starting App with CMS GC Multithread...
@java -Xms2m -Xmx18m -Xmn1m -XX:PermSize=24m -XX:MaxPermSize=36m -XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=2 -cp %WORKING_DIR%module-execution-container-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.classloader.shell.Shell %WORKING_DIR%%MODULES_DIR%

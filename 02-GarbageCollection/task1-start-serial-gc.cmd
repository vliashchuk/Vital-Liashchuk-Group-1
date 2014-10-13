@SET JAR_PATH=../

@SET MODULES_DIR=modules
@SET WORKING_DIR=%~dp0/../01-Classloading/module-execution-container/target/

@Echo Starting App with Serial GC...
@java -Xms6m -Xmx18m -Xmn2m -XX:PermSize=20m -XX:MaxPermSize=30m -XX:+UseSerialGC -cp %WORKING_DIR%module-execution-container-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.classloader.shell.Shell %WORKING_DIR%%MODULES_DIR%

@SET JAR_PATH=../

@SET MODULES_DIR=modules
@SET WORKING_DIR=%~dp0/../01-Classloading/module-execution-container/target/

@Echo Starting App with Parallel Old GC...
@java -Xms9m -Xmx18m -Xmn3m -XX:PermSize=40m -XX:MaxPermSize=40m -XX:+UseParallelOldGC -cp %WORKING_DIR%module-execution-container-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.classloader.shell.Shell %WORKING_DIR%%MODULES_DIR%

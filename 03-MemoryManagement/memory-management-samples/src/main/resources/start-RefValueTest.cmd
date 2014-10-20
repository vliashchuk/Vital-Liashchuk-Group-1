@SET WORKING_DIR=%~dp0

@echo Strating RefValueTest...
@java -cp %WORKING_DIR%memory-management-samples-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.memorymanagment.RefValueTest -Xmx356M -Xms356M -XXNewSize=100M -XXMaxNewSize=100 -XX:SurvivorRatio=1 -XX:ThreadStackSize=1024
@pause
@SET WORKING_DIR=%~dp0

@echo Strating RefValueTest...
@java -Xmx356M -Xms356M -XX:NewSize=100M -XX:MaxNewSize=100M -XX:SurvivorRatio=1 -Xss1M -cp %WORKING_DIR%memory-management-samples-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.memorymanagment.RefValueTest
@pause
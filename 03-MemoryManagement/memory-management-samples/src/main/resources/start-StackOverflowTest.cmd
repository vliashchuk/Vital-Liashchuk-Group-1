@SET WORKING_DIR=%~dp0

@echo Strating MemoryEaterFixed...
@java -Xss104K -cp %WORKING_DIR%memory-management-samples-1.0-SNAPSHOT.jar;%WORKING_DIR%log4j-1.2.17.jar com.epam.jmp.tasks.memorymanagment.StackOverflowTest
@pause
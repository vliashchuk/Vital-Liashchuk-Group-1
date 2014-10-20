In order to start Tasks samples run corresponding cmd script in ./memory-management-samples/target dir.
Read below my commets to each task.

1. Java.MP.C4.M1.MemoryManagement Default Task1
	"Please add the class MemoryEater.java from 
	http://10.6.102.126/svn/mentor/trunk/src/main/java/com/epam/heap
	to your application.
	Please analyse the reason of the error.
	Add research artifacts and change the code so that fix it.
	Please give 2M for thread stack, 1024 M (maximum) for heap, I want to have the following heap structure :5-Eden,2-S0,4-S1"

Application stops with OutOfMemoryError error.
It creates lots of objects but GC can not clean them because application holds references to all this objects.
In order to fix this memory leak we need to clean references after we don't need them.
See details in comments of MemoryEaterFixed class.

There is no ability to set different size of Survivor areas like "5-Eden,2-S0,4-S1"" using documented JVM patameters.
Run	./memory-management-samples/target/start-MemoryEaterFixed.cmd or
	./memory-management-samples/target/start-MemoryEater.cmd
to start sample.


2. Java.MP.C4.M1.MemoryManagement Default Task2 Part1
	"Please add to your application code which includes passing by reference.
	Please don't add returning the reference from method.
	Make creation of the new object in method where you pass value by reference
	(the new object is for value). Please analyse the code structure,
	change the code by returning the value of scalar type and object type.
	Please give 256 M for tanured area of heap,
	I want to have the following heap structure :3-Eden,3-S0,3-S1, give 1M to thread stack."
	
It seems that there is no documented JVM patameter for tanured area size.
But we can set max and start size for whole heap area and max and start size for New Generation areas.
So TENURED = HEAP - NEW
Or we can set max and start size of heap and set NewRatio - raito between new and old genereation.
Run ./memory-management-samples/target/start-RefValueTest.cmd to start sample.

2. Java.MP.C4.M1.MemoryManagement Default Task2 Part2
	"Create recursive method with recursion depth parameter.
	Run method with parameter value big enough to rasie StackOverflowError.
	Decrease value of parameter in order to get rid of error.
	Add code that creates object in method.
	Run method again and explain result."
	
When we create object in method and don't keep reference to this object in local variable 
we don't get StackOverflowError.
But	after adding local variable and saving reference of object to it we get StackOverflowError while app is running.
It is because size of method frame become bigger
and all recursion invocations don't fit thread stack.

Run ./memory-management-samples/target/start-StackOverflowTest.cmd to start sample.

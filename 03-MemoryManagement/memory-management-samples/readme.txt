In order to start Tasks samples run corresponding cmd script in target dir.
Read below my commets to each task.

1. Java.MP.C4.M1.MemoryManagement Default Task2 Part1
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
Run ./target/start-RefValueTest.cmd to start sample.




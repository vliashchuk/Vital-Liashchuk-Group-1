package com.epam.jmp.tasks.memorymanagment;

import org.apache.log4j.Logger;

public class StackOverflowTest {

	private static final Logger LOGGER = Logger.getLogger(StackOverflowTest.class);
	
	public static final Integer RECURSION_DEPTH = 800; //for -Xss104K
	
	public static void main(String[] args) {
		doRecursion(RECURSION_DEPTH);
	}

//	When we create object in method and don't keep reference to this object in local variable 
//	we don't get StackOverflowError.
//	But	after adding local variable and saving reference of object to it we get StackOverflowError while app is running.
//	It is because size of method frame become bigger
//	and all recursion invocations don't fit thread stack.
	
	private static void doRecursion(int depth){
//		Integer i1;
//		Integer i2;
//		Integer i3;
//		Integer i4;
//		new Integer(1);
//		new Integer(2);
//		new Integer(3);
//		new Integer(4);
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(2);
		Integer i3 = new Integer(3);
		Integer i4 = new Integer(4);
//		long l1 = 1l;
//		long l2 = 2l;
//		long l3 = 3l;
//		long l4 = 4l;
//		long l1;
//		long l2;
//		long l3;
//		long l4;
		
		
		LOGGER.info("depth: " + depth);
		
		if(depth>0){
			depth--;
			doRecursion(depth);
		}
	}
	
}

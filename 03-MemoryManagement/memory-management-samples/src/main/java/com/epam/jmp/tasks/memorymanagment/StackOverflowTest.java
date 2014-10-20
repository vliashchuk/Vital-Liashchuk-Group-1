package com.epam.jmp.tasks.memorymanagment;

import org.apache.log4j.Logger;

public class StackOverflowTest {

	private static final Logger LOGGER = Logger.getLogger(StackOverflowTest.class);
	
	private static final Integer RECURSION_DEPTH = 5000;
	
	public static void main(String[] args) {
		doRecursion(RECURSION_DEPTH);
	}

	private static void doRecursion(int depth){
//		Integer[] a1 = new Integer[1000];
//		Integer[] a2 = new Integer[1000];
//		Integer[] a3 = new Integer[1000];
//		Integer[] a4 = new Integer[1000];
		long l1 = 1l;
		long l2 = 2l;
		long l3 = 3l;
		
		LOGGER.info("depth: " + depth);
		
		if(depth>0){
			depth--;
			doRecursion(depth);
		}
	}
	
}

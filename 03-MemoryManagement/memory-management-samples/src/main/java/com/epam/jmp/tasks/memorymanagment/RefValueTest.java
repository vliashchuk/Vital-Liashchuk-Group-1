package com.epam.jmp.tasks.memorymanagment;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class RefValueTest {

	private static final Logger LOGGER = Logger.getLogger(RefValueTest.class); 

	public static void main(String[] args) {
		int a = 0;
		int[] b = {0};
		LOGGER.info(RefValueTest.class.getSimpleName()+ " output:");
		LOGGER.info("Before:       a="+ a + " b=" + Arrays.toString(b));
		changeValue(a, b);
		LOGGER.info("Change Value: a="+ a + " b=" + Arrays.toString(b));
		setValue(a, b);
		LOGGER.info("Set Value:    a="+ a + " b=" + Arrays.toString(b));
	}

	private static void changeValue(int a, int[] b){
		//Change only local method variable
		a += 10;
		//Change value of referenced object
		b[0] = 10;
	}

	private static void setValue(int a, int[] b){
		//Set value of local method variable
		a = 20;
		//Set local variable to new object
		b = new int[]{20};
	}
}

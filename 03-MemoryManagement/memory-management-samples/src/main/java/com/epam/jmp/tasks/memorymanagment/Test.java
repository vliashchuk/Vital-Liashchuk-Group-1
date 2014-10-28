package com.epam.jmp.tasks.memorymanagment;

import java.util.Iterator;
import java.util.Vector;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		printAll(args);
	}

	public static void printAll(String[] lines) throws InterruptedException {
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
			Thread.currentThread().sleep(1000);
		}
	}
}
package com.epam.jmp.tasks.junit.mathparser.token;

public enum SummaOperator {

	PLUS("+"),
	MINUS("-");
	
	private String value;
	
	private SummaOperator(String value){
		this.value = value;
	}
	
	private String getValue(){
		return value;
	}
//	public SummaOperator valueOf(String str){
//		
//	}
}

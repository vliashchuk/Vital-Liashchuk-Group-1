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
	public static SummaOperator getOperator(String str){
		
		for(SummaOperator o:SummaOperator.values()){
			if(o.getValue().equals(str)){
				return o;
			}
		}
		return null;
		
	}
}

package com.epam.jmp.tasks.junit.mathparser.token;

import java.security.InvalidParameterException;

public enum MultiplyDivideOperator {

	MULTIPLY("*"),
	DIVIDE("/");
	
	private String value;
	
	private MultiplyDivideOperator(String value){
		this.value = value;
	}
	
	private String getValue(){
		return value;
	}
	public static MultiplyDivideOperator parseOperator(String str){
		
		MultiplyDivideOperator operator = null;
		
		str = str.replaceAll("\\s", "");
		
		for(MultiplyDivideOperator o:MultiplyDivideOperator.values()){
			if(o.getValue().equals(str)){
				operator = o;
				break;
			}
		}
		
		if(operator==null){
			throw new InvalidParameterException("Multiple operator for was not declared for" + str);
		}
		
		return operator;
		
	}
}

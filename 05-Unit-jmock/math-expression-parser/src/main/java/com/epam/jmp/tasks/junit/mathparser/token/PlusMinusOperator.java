package com.epam.jmp.tasks.junit.mathparser.token;

import java.security.InvalidParameterException;

public enum PlusMinusOperator {

	PLUS("+"),
	MINUS("-");
	
	private String value;
	
	private PlusMinusOperator(String value){
		this.value = value;
	}
	
	private String getValue(){
		return value;
	}
	public static PlusMinusOperator parseOperator(String str){
		PlusMinusOperator operator = null;
		
		str = str.replaceAll("\\s", "");
		
		for(PlusMinusOperator o:PlusMinusOperator.values()){
			if(o.getValue().equals(str)){
				operator = o;
				break;
			}
		}
		
		if(operator==null){
			throw new InvalidParameterException("Summa operator for was not declared for " + str);
		}
		
		return operator;
		
	}
}

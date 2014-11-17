package com.epam.jmp.tasks.junit.mathparser.token;

public class PlusMinusOperatorToken extends Token {

	public PlusMinusOperatorToken(String text, PlusMinusOperator operator){
		super(text);
		this.operator = operator;
	}
	
	PlusMinusOperator operator;
	
	public PlusMinusOperator getOperator(){
		return operator;
	}
}

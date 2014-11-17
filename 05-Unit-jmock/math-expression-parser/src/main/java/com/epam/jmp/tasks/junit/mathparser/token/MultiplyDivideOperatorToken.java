package com.epam.jmp.tasks.junit.mathparser.token;

public class MultiplyDivideOperatorToken extends Token {

	public MultiplyDivideOperatorToken(String text, MultiplyDivideOperator operator){
		super(text);
		this.operator = operator;
	}
	
	MultiplyDivideOperator operator;
	
	public MultiplyDivideOperator getOperator(){
		return operator;
	}
}
package com.epam.jmp.tasks.junit.mathparser.token;

public class SummaOperatorToken extends Token {

	public SummaOperatorToken(String text, SummaOperator operator){
		super(text);
		this.operator = operator;
	}

	SummaOperator operator;
	
	public SummaOperator getOperator(){
		return operator;
	}
}

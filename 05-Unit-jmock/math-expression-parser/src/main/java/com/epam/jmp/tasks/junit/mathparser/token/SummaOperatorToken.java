package com.epam.jmp.tasks.junit.mathparser.token;

public class SummaOperatorToken extends Token {

	public SummaOperatorToken(String soutce, String text, int beginIndex, int endIndex, SummaOperator operator){
		super(soutce, text, beginIndex, endIndex);
		this.operator = operator;
	}

	SummaOperator operator;
	
	public SummaOperator getOperator(){
		return operator;
	}
}

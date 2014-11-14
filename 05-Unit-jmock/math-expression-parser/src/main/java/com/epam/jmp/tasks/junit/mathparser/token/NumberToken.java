package com.epam.jmp.tasks.junit.mathparser.token;


public class NumberToken extends Token {

	public NumberToken(String soutce, String text, int beginIndex, int endIndex, Double number){
		super(soutce, text, beginIndex, endIndex);
		this.number = number;
	}

	Double number;
	
	public double getNumber(){
		return number;
	}
}

package com.epam.jmp.tasks.junit.mathparser.token;


public class NumberToken extends Token {

	public NumberToken(String text, Double number){
		super(text);
		this.number = number;
	}

	Double number;
	
	public double getNumber(){
		return number;
	}
}

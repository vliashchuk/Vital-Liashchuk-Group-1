package com.epam.jmp.tasks.junit.mathparser.token;

public class VariableToken extends Token {

	public VariableToken(String text, String name){
		super(text);
		this.name = name;
	}

	String name;
	
	public String getName(){
		return name;
	}
}


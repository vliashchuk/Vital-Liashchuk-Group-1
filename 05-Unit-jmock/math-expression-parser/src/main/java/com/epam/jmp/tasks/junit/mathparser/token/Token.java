package com.epam.jmp.tasks.junit.mathparser.token;

public class Token {

	public Token(String text){
		this.text = text;
	}
	String text;

	public String getText(){
		return text;
	}

	@Override
	public String toString(){
		return "<" + getText() +  ">";
	}
	
	
}

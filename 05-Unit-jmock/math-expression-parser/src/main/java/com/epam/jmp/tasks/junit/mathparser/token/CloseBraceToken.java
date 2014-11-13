package com.epam.jmp.tasks.junit.mathparser.token;

public class CloseBraceToken extends Token {

	public CloseBraceToken(String soutce, String text, int beginIndex, int endIndex){
		super(soutce, text, beginIndex, endIndex);
	}
	
	@Override
	public String toString(){
		return "<" + ")" +  ">";
	}
	
} 
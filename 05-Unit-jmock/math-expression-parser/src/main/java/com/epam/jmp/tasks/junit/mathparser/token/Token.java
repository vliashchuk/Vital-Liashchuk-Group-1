package com.epam.jmp.tasks.junit.mathparser.token;

public class Token {

	public Token(String soutce, String text, int beginIndex, int endIndex){
		this.soutce = soutce;
		this.text = text;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	
	String soutce;
	String text;
	private int beginIndex;
	private int endIndex;
	
	public int getBegin(){
		return beginIndex;
	}

	public int getEnd(){
		return endIndex;
	}

	public String getSource(){
		return soutce;
	}

	public String getText(){
		return text;
	}

	@Override
	public String toString(){
		return "<" + getText() +  ">";
	}
	
	
}

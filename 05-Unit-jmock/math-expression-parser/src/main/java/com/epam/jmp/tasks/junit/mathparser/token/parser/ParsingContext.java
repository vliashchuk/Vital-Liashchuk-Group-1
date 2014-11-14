package com.epam.jmp.tasks.junit.mathparser.token.parser;

public class ParsingContext {

	private final String source;
	
	private int currentPosition = 0;
	
	public ParsingContext(String source){
		
		if (source == null) {
			throw new IllegalArgumentException("source == null");
		}
		
		this.source = source;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getSource() {
		return source;
	}
		
}

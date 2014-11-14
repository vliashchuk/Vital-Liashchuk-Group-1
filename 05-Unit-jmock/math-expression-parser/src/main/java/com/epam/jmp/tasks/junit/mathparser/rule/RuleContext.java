package com.epam.jmp.tasks.junit.mathparser.rule;

import java.util.List;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class RuleContext {

	public RuleContext(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	private List<Token> tokens;
	
	private int currentPosition = 0;

	public List<Token> getTokens() {
		return tokens;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	
	
}

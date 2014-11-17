package com.epam.jmp.tasks.junit.mathparser.rule;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class RuleContext {

	public RuleContext(Token[] tokens) {
		this.tokens = tokens;
	}
	
	private Token[] tokens;
	
	private int currentPosition = 0;

	public Token getToken(int position) {
		if(position>=tokens.length){
			return null;
		} else {
			return tokens[position];
		}
	}

	public int getTokensCount() {
		return tokens.length;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	
	
}

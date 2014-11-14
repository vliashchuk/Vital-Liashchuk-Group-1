package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.WhiteSpaceToken;

public class WhiteSpaceTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s+).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {

		Token tok = new WhiteSpaceToken(text);
		
		return tok;
	}

}

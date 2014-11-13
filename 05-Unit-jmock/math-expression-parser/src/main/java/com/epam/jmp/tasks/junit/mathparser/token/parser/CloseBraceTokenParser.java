package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.CloseBraceToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class CloseBraceTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*\\)).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String source, String text, int beginIndex,
			int endIndex) {

		Token tok = new CloseBraceToken(source, text, beginIndex, endIndex);
		
		return tok;
	}

}

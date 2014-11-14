package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.OpenBraceToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class OpenBraceTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*\\().*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {

		return new OpenBraceToken(text);
	}

}

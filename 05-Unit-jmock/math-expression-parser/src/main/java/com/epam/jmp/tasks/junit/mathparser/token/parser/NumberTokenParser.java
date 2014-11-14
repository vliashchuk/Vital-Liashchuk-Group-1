package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class NumberTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*\\d{1,}(\\.\\d{1,})?).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {
		
		Double number = Double.parseDouble(text);

		Token tok = new NumberToken(text, number);
		
		return tok;
	}

}

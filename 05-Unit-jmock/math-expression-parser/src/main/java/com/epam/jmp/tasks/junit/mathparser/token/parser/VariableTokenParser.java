package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.VariableToken;

public class VariableTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*[a-zA-Z][a-zA-Z0-9]*).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {
		
		String name = text.replaceAll("\\s", "").toLowerCase();

		Token tok = new VariableToken(text, name);
		
		return tok;
	}

}

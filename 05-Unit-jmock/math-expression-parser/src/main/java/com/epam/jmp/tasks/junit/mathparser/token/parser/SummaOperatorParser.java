package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class SummaOperatorParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*[\\+-]).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String source, String text, int beginIndex,
			int endIndex) {
		
		Double number = Double.parseDouble(text);

		Token tok = new NumberToken(source, text, beginIndex, endIndex, number);
		
		return tok;
	}

}

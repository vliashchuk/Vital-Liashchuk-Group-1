package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.SummaOperator;
import com.epam.jmp.tasks.junit.mathparser.token.SummaOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class SummaOperatorParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*[\\+-]).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {
		
		SummaOperator operator = SummaOperator.getOperator(text);

		Token tok = new SummaOperatorToken(text, operator);
		
		return tok;
	}

}

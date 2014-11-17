package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperator;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class MultiplyDivideOperatorTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*[\\*/]).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {
		
		MultiplyDivideOperator operator = MultiplyDivideOperator.parseOperator(text);

		Token tok = new MultiplyDivideOperatorToken(text, operator);
		
		return tok;
	}

}

package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class PlusMinusOperatorTokenParser extends AbstractTokenParser {
	
	private static final Pattern PATTERN = Pattern.compile("(?s)^(\\s*[\\+-]).*");
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected Token createToken(String text) {
		
		PlusMinusOperator operator = PlusMinusOperator.parseOperator(text);

		Token tok = new PlusMinusOperatorToken(text, operator);
		
		return tok;
	}

}

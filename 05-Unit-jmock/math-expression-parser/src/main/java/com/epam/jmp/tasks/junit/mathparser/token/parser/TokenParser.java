package com.epam.jmp.tasks.junit.mathparser.token.parser;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public interface TokenParser {

	public Token parseToken(ParsingContext context);
}

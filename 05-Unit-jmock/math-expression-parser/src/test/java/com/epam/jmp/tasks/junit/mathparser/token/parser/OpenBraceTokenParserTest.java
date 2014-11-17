package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.OpenBraceToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class OpenBraceTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new OpenBraceTokenParser();
	}
	
	@Test
	public void test(){
		Token token = tokenParser.parseToken(new ParsingContext(" ("));
		Assert.assertNotNull(token);
		Assert.assertEquals(OpenBraceToken.class, token.getClass());

	}
	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(" )"));
		Assert.assertNull(token);
	}
}


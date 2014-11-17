package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.CloseBraceToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class CloseBraceTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new CloseBraceTokenParser();
	}
	
	@Test
	public void test(){
		Token token = tokenParser.parseToken(new ParsingContext(" )"));
		Assert.assertNotNull(token);
		Assert.assertEquals(CloseBraceToken.class, token.getClass());

	}
	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(" ("));
		Assert.assertNull(token);
	}
}

package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class NumberTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new NumberTokenParser();
	}
	
	@Test
	public void testInt(){
		Token token = tokenParser.parseToken(new ParsingContext(" 1"));
		Assert.assertNotNull(token);
		Assert.assertEquals(NumberToken.class, token.getClass());
		Assert.assertEquals(new Double(1d), ((NumberToken)token).getNumber());
	}
	
	@Test
	public void testDouble(){
		Token token = tokenParser.parseToken(new ParsingContext(" 1.1"));
		Assert.assertNotNull(token);
		Assert.assertEquals(NumberToken.class, token.getClass());
		Assert.assertEquals(new Double(1.1d), ((NumberToken)token).getNumber());
	}
	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(" )"));
		Assert.assertNull(token);
	}
}


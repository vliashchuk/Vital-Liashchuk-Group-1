package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.VariableToken;

public class VariableTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new VariableTokenParser();
	}
	
	@Test
	public void test(){
		Token token = tokenParser.parseToken(new ParsingContext(" X1"));
		Assert.assertNotNull(token);
		Assert.assertEquals(VariableToken.class, token.getClass());
		Assert.assertEquals("x1", ((VariableToken)token).getName());
	}
	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(" 1X"));
		Assert.assertNull(token);
	}
}


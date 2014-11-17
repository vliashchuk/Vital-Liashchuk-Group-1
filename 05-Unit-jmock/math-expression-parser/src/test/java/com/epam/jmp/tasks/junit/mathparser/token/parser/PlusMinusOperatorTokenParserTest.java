package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class PlusMinusOperatorTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new PlusMinusOperatorTokenParser();
	}
	
	@Test
	public void testPlus(){
		Token token = tokenParser.parseToken(new ParsingContext(" +"));
		Assert.assertNotNull(token);
		Assert.assertEquals(PlusMinusOperatorToken.class, token.getClass());
		Assert.assertEquals(PlusMinusOperator.PLUS, ((PlusMinusOperatorToken)token).getOperator());
	}
	
	@Test
	public void testMinus(){
		Token token = tokenParser.parseToken(new ParsingContext(" -"));
		Assert.assertNotNull(token);
		Assert.assertEquals(PlusMinusOperatorToken.class, token.getClass());
		Assert.assertEquals(PlusMinusOperator.MINUS, ((PlusMinusOperatorToken)token).getOperator());
	}
	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(" )"));
		Assert.assertNull(token);
	}
}


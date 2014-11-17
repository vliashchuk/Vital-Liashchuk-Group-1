package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperator;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class MultiplyDivideOperatorTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new MultiplyDivideOperatorTokenParser();
	}
	
	@Test
	public void testMultiply(){
		Token token = tokenParser.parseToken(new ParsingContext(" *"));
		Assert.assertNotNull(token);
		Assert.assertEquals(MultiplyDivideOperatorToken.class, token.getClass());
		Assert.assertEquals(MultiplyDivideOperator.MULTIPLY, ((MultiplyDivideOperatorToken)token).getOperator());
	}
	
	@Test
	public void testDivide(){
		Token token = tokenParser.parseToken(new ParsingContext(" /"));
		Assert.assertNotNull(token);
		Assert.assertEquals(MultiplyDivideOperatorToken.class, token.getClass());
		Assert.assertEquals(MultiplyDivideOperator.DIVIDE, ((MultiplyDivideOperatorToken)token).getOperator());
	}
	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(" )"));
		Assert.assertNull(token);
	}
}


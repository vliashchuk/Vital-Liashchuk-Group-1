package com.epam.jmp.tasks.junit.mathparser.token.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.WhiteSpaceToken;

public class WhiteSpaceTokenParserTest {
	
	TokenParser tokenParser;
	
	@Before
	public void prepare(){
		tokenParser = new WhiteSpaceTokenParser();
	}
	
	@Test
	public void test(){
		Token token = tokenParser.parseToken(new ParsingContext("  +"));
		Assert.assertNotNull(token);
		Assert.assertEquals(WhiteSpaceToken.class, token.getClass());
		Assert.assertEquals("  ", token.getText());
	}

	
	@Test
	public void negativeTest(){
		Token token = tokenParser.parseToken(new ParsingContext(") "));
		Assert.assertNull(token);
	}
}


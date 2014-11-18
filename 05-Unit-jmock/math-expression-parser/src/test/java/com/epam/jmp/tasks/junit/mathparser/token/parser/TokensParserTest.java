package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.token.CloseBracketToken;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperator;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.OpenBracketToken;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class TokensParserTest {
	
	TokensParser tokensParser;
	
	@Before
	public void prepare(){
		TokenParser[] tokenParsers = {new PlusMinusOperatorTokenParser(),
                new MultiplyDivideOperatorTokenParser(),
                new OpenBracketTokenParser(),
                new CloseBracketTokenParser(),
                new NumberTokenParser()};
		
		tokensParser = TokensParser.createParser(tokenParsers);
	}
	
	@Test
	public void test(){
		Token[] tokens = tokensParser.parse(" 1 * 2 / 3 + (1.5 - 1)");
		Assert.assertNotNull(tokens);
		Assert.assertEquals(tokens.length, 11);
		Assert.assertNotNull(tokens[0]);
		Assert.assertEquals(NumberToken.class, tokens[0].getClass());
		Assert.assertEquals(new Double(1d), ((NumberToken)tokens[0]).getNumber());
		Assert.assertNotNull(tokens[1]);
		Assert.assertEquals(MultiplyDivideOperatorToken.class, tokens[1].getClass());
		Assert.assertEquals(MultiplyDivideOperator.MULTIPLY, ((MultiplyDivideOperatorToken)tokens[1]).getOperator());
		Assert.assertNotNull(tokens[2]);
		Assert.assertEquals(NumberToken.class, tokens[2].getClass());
		Assert.assertEquals(new Double(2d), ((NumberToken)tokens[2]).getNumber());
		Assert.assertNotNull(tokens[3]);
		Assert.assertEquals(MultiplyDivideOperatorToken.class, tokens[3].getClass());
		Assert.assertEquals(MultiplyDivideOperator.DIVIDE, ((MultiplyDivideOperatorToken)tokens[3]).getOperator());
		Assert.assertNotNull(tokens[4]);
		Assert.assertEquals(NumberToken.class, tokens[4].getClass());
		Assert.assertEquals(new Double(3d), ((NumberToken)tokens[4]).getNumber());
		Assert.assertNotNull(tokens[5]);
		Assert.assertEquals(PlusMinusOperatorToken.class, tokens[5].getClass());
		Assert.assertEquals(PlusMinusOperator.PLUS, ((PlusMinusOperatorToken)tokens[5]).getOperator());
		Assert.assertNotNull(tokens[6]);
		Assert.assertEquals(OpenBracketToken.class, tokens[6].getClass());
		Assert.assertNotNull(tokens[7]);
		Assert.assertEquals(NumberToken.class, tokens[7].getClass());
		Assert.assertEquals(new Double(1.5d), ((NumberToken)tokens[7]).getNumber());
		Assert.assertNotNull(tokens[8]);
		Assert.assertEquals(PlusMinusOperatorToken.class, tokens[8].getClass());
		Assert.assertEquals(PlusMinusOperator.MINUS, ((PlusMinusOperatorToken)tokens[8]).getOperator());
		Assert.assertNotNull(tokens[9]);
		Assert.assertEquals(NumberToken.class, tokens[9].getClass());
		Assert.assertEquals(new Double(1d), ((NumberToken)tokens[9]).getNumber());
		Assert.assertNotNull(tokens[10]);
		Assert.assertEquals(CloseBracketToken.class, tokens[10].getClass());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegative(){
		Token[] tokens = tokensParser.parse(" 1,1 * 2 / 3 + (1.5 - 1)");
		System.out.println(Arrays.toString(tokens));
	}
}


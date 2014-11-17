package com.epam.jmp.tasks.junit.mathparser.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.expression.ConstantExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class ValueRuleTest {
	
	ValueRule rule;
	
	@Before
	public void prepare(){
		rule = new ValueRule();
	}
	
	@Test
	public void testNumberConstant() throws RuleApplyingException{
		Token[] tokens = {new NumberToken("1", 1d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		Double ret = expression.evaluate(new ExpressionContext() {
		});
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1d), ret);
	}
	
	@Test
	public void testSignBeforeNumberConstant() throws RuleApplyingException{
		Token[] tokens = {new PlusMinusOperatorToken("+", PlusMinusOperator.PLUS), new NumberToken("1", 1d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		Double ret = expression.evaluate(new ExpressionContext() {
		});
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1d), ret);
		
		tokens = new Token[]{new PlusMinusOperatorToken("-", PlusMinusOperator.MINUS), new NumberToken("1", 1d)};
		expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		ret = expression.evaluate(new ExpressionContext() {
		});
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(-1d), ret);
	}

	
//	@Test
//	public void negativeTest(){
//		Token token = tokenParser.parseToken(new ParsingContext(") "));
//		Assert.assertNull(token);
//	}
}


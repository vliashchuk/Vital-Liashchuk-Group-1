package com.epam.jmp.tasks.junit.mathparser.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.expression.ConstantExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionEvaluationException;
import com.epam.jmp.tasks.junit.mathparser.expression.SimpleExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.expression.VariableExpression;
import com.epam.jmp.tasks.junit.mathparser.token.CloseBracketToken;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.OpenBracketToken;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.VariableToken;

public class ValueRuleTest {
	
	ValueRule rule;
	
	@Before
	public void prepare(){
		rule = new ValueRule();
	}
	
	@Test
	public void testNumberConstant() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new NumberToken("1", 1d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1d), ret);
	}
	
	@Test
	public void testFractionalNumberConstant() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new NumberToken("1.5", 1.5d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1.5d), ret);
	}
	
	@Test
	public void testVariable() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new VariableToken(" x1", "x1")};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(VariableExpression.class, expression.getClass());
		SimpleExpressionContext context = new SimpleExpressionContext();
		context.setVariable("x1", new Double(1d));
		Double ret = expression.evaluate(context);
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1d), ret);
	}
	
	@Test
	public void testSignBeforeNumberConstant() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new PlusMinusOperatorToken("+", PlusMinusOperator.PLUS), new NumberToken("1", 1d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1d), ret);
		
		tokens = new Token[]{new PlusMinusOperatorToken("-", PlusMinusOperator.MINUS), new NumberToken("1", 1d)};
		expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(-1d), ret);
	}

	@Test
	public void testConstantInBrackets() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new OpenBracketToken("("), new NumberToken("1", 1d), new CloseBracketToken(")")};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(ConstantExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(1d), ret);
	}
	
	@Test(expected=RuleApplyingException.class)
	public void negativeTestConstantInBrackets() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new OpenBracketToken("("), new NumberToken("1", 1d), new OpenBracketToken("(")};
		Expression expression = rule.apply(new RuleContext(tokens));
		System.out.println(expression.evaluate(new SimpleExpressionContext()));
		Assert.fail();
	}
}


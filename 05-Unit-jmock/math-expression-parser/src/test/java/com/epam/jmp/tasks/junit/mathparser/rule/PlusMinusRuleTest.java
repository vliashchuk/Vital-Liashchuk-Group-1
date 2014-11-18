package com.epam.jmp.tasks.junit.mathparser.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionEvaluationException;
import com.epam.jmp.tasks.junit.mathparser.expression.MinusExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.PlusExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.SimpleExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class PlusMinusRuleTest  {
	
	PlusMinusRule rule;
	
	@Before
	public void prepare(){
		rule = new PlusMinusRule();
	}
	
	@Test
	public void testMultiplyOperation() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new NumberToken("1", 1d),
						  new PlusMinusOperatorToken("+", PlusMinusOperator.PLUS),
						  new NumberToken("2", 2d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(PlusExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(3d), ret);
	}
	
	@Test
	public void testMultiplyDivideSequence() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new NumberToken("1", 1d),
						  new PlusMinusOperatorToken("+", PlusMinusOperator.PLUS),
						  new NumberToken("2", 2d),
						  new PlusMinusOperatorToken("-", PlusMinusOperator.MINUS),
						  new NumberToken("3", 3d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(MinusExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(0d), ret);
	}
}
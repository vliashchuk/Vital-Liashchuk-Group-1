package com.epam.jmp.tasks.junit.mathparser.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.junit.mathparser.expression.DivideExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionEvaluationException;
import com.epam.jmp.tasks.junit.mathparser.expression.MultiplyExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.SimpleExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperator;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperatorToken;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class MultiplyDivideRuleTest {
	
	MultiplyDivideRule rule;
	
	@Before
	public void prepare(){
		rule = new MultiplyDivideRule();
	}
	
	@Test
	public void testMultiplyOperation() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new NumberToken("1", 1d),
						  new MultiplyDivideOperatorToken("*", MultiplyDivideOperator.MULTIPLY),
						  new NumberToken("2", 2d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(MultiplyExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(2d), ret);
	}
	
	@Test
	public void testMultiplyDivideSequence() throws RuleApplyingException, ExpressionEvaluationException{
		Token[] tokens = {new NumberToken("1", 1d),
						  new MultiplyDivideOperatorToken("*", MultiplyDivideOperator.MULTIPLY),
						  new NumberToken("4", 4d),
						  new MultiplyDivideOperatorToken("/", MultiplyDivideOperator.DIVIDE),
						  new NumberToken("2", 2d)};
		Expression expression = rule.apply(new RuleContext(tokens));
		Assert.assertNotNull(expression);
		Assert.assertEquals(DivideExpression.class, expression.getClass());
		Double ret = expression.evaluate(new SimpleExpressionContext());
		Assert.assertNotNull(ret);
		Assert.assertEquals(new Double(2d), ret);
	}

}


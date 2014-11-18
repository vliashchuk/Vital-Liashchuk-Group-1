package com.epam.jmp.tasks.junit.mathparser.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinusExpressionTest {
	
	Double expected;
	Expression leftOperand;
	Expression rightOperand;
	
	@Before
	public void prepare(){
		expected = -1d;
		leftOperand = new ConstantExpression(1d);
		rightOperand = new ConstantExpression(2d);
	}
	
	@Test
	public void test() throws ExpressionEvaluationException{
		Expression expression = new MinusExpression(leftOperand, rightOperand);
		Assert.assertEquals(expected,
				expression.evaluate(new SimpleExpressionContext()));
	}

	
}

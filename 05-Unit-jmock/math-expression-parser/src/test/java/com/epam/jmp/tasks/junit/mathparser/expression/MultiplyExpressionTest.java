package com.epam.jmp.tasks.junit.mathparser.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MultiplyExpressionTest {
	
	Double expected;
	Expression leftOperand;
	Expression rightOperand;
	
	@Before
	public void prepare(){
		expected = 2d;
		leftOperand = new ConstantExpression(1d);
		rightOperand = new ConstantExpression(2d);
	}
	
	@Test
	public void test(){
		Expression expression = new MultiplyExpression(leftOperand, rightOperand);
		Assert.assertEquals(expected,
				expression.evaluate(new ExpressionContext() {
				}));
	}
}

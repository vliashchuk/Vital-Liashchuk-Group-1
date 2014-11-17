package com.epam.jmp.tasks.junit.mathparser.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConstantExpressionTest {
	
	Double expected;
	Double operand;
	
	@Before
	public void prepare(){
		expected = 1d;
		operand = 1d;
	}
	
	@Test
	public void test(){
		Expression expression = new ConstantExpression(operand);
		Assert.assertEquals(expected,
				expression.evaluate(new ExpressionContext() {
				}));
	}
}


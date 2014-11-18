package com.epam.jmp.tasks.junit.mathparser.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivideExpressionTest {
	
	Double expected;
	Expression leftOperand;
	Expression rightOperand;
	
	@Before
	public void prepare(){
		expected = 0.5d;
		leftOperand = new ConstantExpression(1d);
		rightOperand = new ConstantExpression(2d);
	}
	
	@Test
	public void test() throws ExpressionEvaluationException{
		Expression expression = new DivideExpression(leftOperand, rightOperand);
		Assert.assertEquals(expected,
				expression.evaluate(new SimpleExpressionContext()));
	}
}


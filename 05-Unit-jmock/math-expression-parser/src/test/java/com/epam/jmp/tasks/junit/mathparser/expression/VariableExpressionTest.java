package com.epam.jmp.tasks.junit.mathparser.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VariableExpressionTest {
	
	String name;
	Double value;
	Expression expression;
	SimpleExpressionContext context;
	
	@Before
	public void prepare(){
		name = "x";
		value = 1d;
		expression = new VariableExpression(name);
		context = new SimpleExpressionContext();
		context.setVariable(name, value);
	}
	
	@Test
	public void test() throws ExpressionEvaluationException{
		Assert.assertEquals(value, expression.evaluate(context));
	}
	
	@Test(expected = ExpressionEvaluationException.class)
	public void negativeTest() throws ExpressionEvaluationException{
		context.setVariable(name, null);
		Assert.assertEquals(value, expression.evaluate(context));
	}
}
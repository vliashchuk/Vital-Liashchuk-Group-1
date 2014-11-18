package com.epam.jmp.tasks.junit.mathparser.expression;

public interface Expression {

	Double evaluate(ExpressionContext context) throws ExpressionEvaluationException;
	
}

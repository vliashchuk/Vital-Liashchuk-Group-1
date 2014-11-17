package com.epam.jmp.tasks.junit.mathparser.expression;

public class MultiplyExpression implements Expression{

	private Expression leftOperand;
	private Expression rightOperand;
	
	public MultiplyExpression(Expression leftOperand, Expression rightOperand){
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	@Override
	public Double evaluate(ExpressionContext context) {
		return leftOperand.evaluate(context)*rightOperand.evaluate(context);
	}

}
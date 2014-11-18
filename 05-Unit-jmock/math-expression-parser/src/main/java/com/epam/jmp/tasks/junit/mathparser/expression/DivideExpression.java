package com.epam.jmp.tasks.junit.mathparser.expression;

public class DivideExpression implements Expression{

	private Expression leftOperand;
	private Expression rightOperand;
	
	public DivideExpression(Expression leftOperand, Expression rightOperand){
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	@Override
	public Double evaluate(ExpressionContext context) throws ExpressionEvaluationException {
		return leftOperand.evaluate(context)/rightOperand.evaluate(context);
	}

}
package com.epam.jmp.tasks.junit.mathparser.expression;

public class ConstantExpression implements Expression{

	private Double value;
	
	public ConstantExpression(Double value){
		this.value = value;
	}
	
	@Override
	public Double evaluate(ExpressionContext context) {
		return value;
	}

}

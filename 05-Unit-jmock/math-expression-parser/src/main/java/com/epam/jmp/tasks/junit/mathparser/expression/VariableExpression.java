package com.epam.jmp.tasks.junit.mathparser.expression;

public class VariableExpression implements Expression{

	private String name;
	
	public VariableExpression(String name){
		this.name = name;
	}
	
	@Override
	public Double evaluate(ExpressionContext context) throws ExpressionEvaluationException {
		Double value = context.getValueForVariable(name);
		if(value == null){
			throw new ExpressionEvaluationException("Value for variable was not set: " + name);
		}
		return value;
	}

}
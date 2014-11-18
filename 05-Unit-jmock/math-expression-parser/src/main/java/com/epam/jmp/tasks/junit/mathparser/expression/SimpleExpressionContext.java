package com.epam.jmp.tasks.junit.mathparser.expression;

import java.util.HashMap;
import java.util.Map;

public class SimpleExpressionContext implements ExpressionContext{

	private Map<String, Double> vars = new HashMap<>();
	
	@Override
	public Double getValueForVariable(String name) {
		return vars.get(name.toLowerCase());
	}
	
	public void setVariable(String name, Double value){
		vars.put(name.toLowerCase(), value);
	}

}

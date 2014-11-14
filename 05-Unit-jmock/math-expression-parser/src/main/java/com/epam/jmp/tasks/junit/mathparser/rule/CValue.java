package com.epam.jmp.tasks.junit.mathparser.rule;

import com.epam.jmp.tasks.junit.mathparser.expression.ConstantExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;

public class CValue {

	public Expression parse(RuleContext context){
		
		if(NumberToken.class.isAssignableFrom(context.getTokens().get(context.getCurrentPosition()).getClass())){
			NumberToken value = (NumberToken) context.getTokens().get(context.getCurrentPosition());
			return new ConstantExpression(value.getNumber());
		}
		
		throw new RuntimeException("Parsing exception!");
		
	}
	
}

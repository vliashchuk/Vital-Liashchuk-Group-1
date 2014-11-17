package com.epam.jmp.tasks.junit.mathparser.rule;

import com.epam.jmp.tasks.junit.mathparser.expression.ConstantExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.token.CloseBraceToken;
import com.epam.jmp.tasks.junit.mathparser.token.NumberToken;
import com.epam.jmp.tasks.junit.mathparser.token.OpenBraceToken;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;

public class ValueRule implements Rule{

	@Override
	public Expression apply(RuleContext context) throws RuleApplyingException{
		
		if(context.getToken(context.getCurrentPosition()) !=null
			&&	
		   NumberToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()).getClass())){
			NumberToken value = (NumberToken) context.getToken(context.getCurrentPosition());
			context.setCurrentPosition(context.getCurrentPosition()+1);
			return new ConstantExpression(value.getNumber());
		}
		
		if(context.getToken(context.getCurrentPosition()) != null
			&&
		   PlusMinusOperatorToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()).getClass())
			&&
		   context.getToken(context.getCurrentPosition() + 1) != null
			&&
		   NumberToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()+1).getClass())){
			PlusMinusOperatorToken sign = (PlusMinusOperatorToken) context.getToken(context.getCurrentPosition());
			NumberToken value = (NumberToken) context.getToken(context.getCurrentPosition()+1);
			
			Double ret = value.getNumber();
			if(sign.getOperator().equals(PlusMinusOperator.MINUS)){
				ret = new Double(-ret.doubleValue());
			}
			context.setCurrentPosition(context.getCurrentPosition()+2);
			return new ConstantExpression(ret);
		}
		
		if(context.getToken(context.getCurrentPosition()) != null
			&&
		   OpenBraceToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()).getClass())){
			
			context.setCurrentPosition(context.getCurrentPosition()+1);
			Expression value = new PlusMinusRule().apply(context);
			
			if(context.getToken(context.getCurrentPosition()) != null
				&&
			   CloseBraceToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()).getClass())){
				context.setCurrentPosition(context.getCurrentPosition()+1);
				return value;
			}
		}

		
		throw new RuleApplyingException("Can not parse token at position " + context.getCurrentPosition() + ": " + context.getToken(context.getCurrentPosition()));
		
	}
	
}

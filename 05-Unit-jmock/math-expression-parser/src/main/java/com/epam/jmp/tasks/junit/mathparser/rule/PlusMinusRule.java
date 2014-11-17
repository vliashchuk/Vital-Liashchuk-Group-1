package com.epam.jmp.tasks.junit.mathparser.rule;

import com.epam.jmp.tasks.junit.mathparser.expression.MinusExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.PlusExpression;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperator;
import com.epam.jmp.tasks.junit.mathparser.token.PlusMinusOperatorToken;

public class PlusMinusRule implements Rule{

	@Override
	public Expression apply(RuleContext context) throws RuleApplyingException {
		
		Expression leftOperand = new MultiplyDivideRule().apply(context);
		Expression rightOperand;
		Expression ret = leftOperand;
		
		while(context.getToken(context.getCurrentPosition()) != null
			&&
		   PlusMinusOperatorToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()).getClass())){
			
			PlusMinusOperatorToken operatorToken = (PlusMinusOperatorToken) context.getToken(context.getCurrentPosition());
			
			context.setCurrentPosition(context.getCurrentPosition()+ 1);
			rightOperand = new MultiplyDivideRule().apply(context);
			
			if(operatorToken.getOperator().equals(PlusMinusOperator.PLUS)){
				ret = new PlusExpression(leftOperand, rightOperand);
			} else {
				if(operatorToken.getOperator().equals(PlusMinusOperator.MINUS)){
					ret = new MinusExpression(leftOperand, rightOperand);
				} else {
					throw new IllegalStateException("Operator is not supported:" + operatorToken.getOperator());
				}				
			}
			leftOperand = ret;
		}
		
		return ret;
	}

}
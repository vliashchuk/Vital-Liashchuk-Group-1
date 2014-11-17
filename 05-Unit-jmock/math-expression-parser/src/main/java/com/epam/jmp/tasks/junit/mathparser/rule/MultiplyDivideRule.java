package com.epam.jmp.tasks.junit.mathparser.rule;

import com.epam.jmp.tasks.junit.mathparser.expression.DivideExpression;
import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.MultiplyExpression;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperator;
import com.epam.jmp.tasks.junit.mathparser.token.MultiplyDivideOperatorToken;

public class MultiplyDivideRule implements Rule{

	@Override
	public Expression apply(RuleContext context) throws RuleApplyingException {

		
		Expression leftOperand = new ValueRule().apply(context);
		Expression rightOperand;
		Expression ret = leftOperand;
		
		while(context.getToken(context.getCurrentPosition()) !=null
				&&
			MultiplyDivideOperatorToken.class.isAssignableFrom(context.getToken(context.getCurrentPosition()).getClass())){
			
			MultiplyDivideOperatorToken operatorToken = (MultiplyDivideOperatorToken) context.getToken(context.getCurrentPosition());
			
			context.setCurrentPosition(context.getCurrentPosition()+ 1);
			rightOperand = new ValueRule().apply(context);
			if(operatorToken.getOperator().equals(MultiplyDivideOperator.MULTIPLY)){
				ret = new MultiplyExpression(leftOperand, rightOperand);
			} else {
				if(operatorToken.getOperator().equals(MultiplyDivideOperator.DIVIDE)){
					ret = new DivideExpression(leftOperand, rightOperand);
				} else {
					throw new IllegalStateException("Operator is not supported:" + operatorToken.getOperator());
				}				
			}
			leftOperand = ret;
		}
		
		return ret;
	}

}

package com.epam.jmp.tasks.junit.mathparser.rule;

import com.epam.jmp.tasks.junit.mathparser.expression.Expression;

public interface Rule {

	Expression apply(RuleContext context) throws RuleApplyingException;
	
}

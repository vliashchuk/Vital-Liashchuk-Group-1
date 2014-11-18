package com.epam.jmp.tasks.junit.mathparser;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionEvaluationException;
import com.epam.jmp.tasks.junit.mathparser.expression.SimpleExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.rule.PlusMinusRule;
import com.epam.jmp.tasks.junit.mathparser.rule.RuleApplyingException;
import com.epam.jmp.tasks.junit.mathparser.rule.RuleContext;
import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.parser.CloseBracketTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.MultiplyDivideOperatorTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.NumberTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.OpenBracketTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.PlusMinusOperatorTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.TokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.TokensParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.VariableTokenParser;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println();
		
		TokenParser[] tokenParsers = {new PlusMinusOperatorTokenParser(),
				                      new MultiplyDivideOperatorTokenParser(),
				                      new OpenBracketTokenParser(),
				                      new CloseBracketTokenParser(),
				                      new NumberTokenParser(),
				                      new VariableTokenParser()};
		
		TokensParser tokensParser = TokensParser.createParser(tokenParsers);

		String source = "1+ 1/2*x1 + 2 + X2";
		LOGGER.debug("Trying to parse:" + source);
		
		Token[] tokens = tokensParser.parse(source);
		if( tokens==null )
		{
			LOGGER.error("no tokens");
		return;
		} else {
			LOGGER.debug("Parsed tokens:");
			LOGGER.debug(Arrays.toString(tokens));
		}
		LOGGER.debug("Trying to syntax parse...");
		
		Expression expression;
		try {
			expression = new PlusMinusRule().apply(new RuleContext(tokens));
			LOGGER.debug("Trying to evaluate...");
			SimpleExpressionContext contex = new SimpleExpressionContext();
			contex.setVariable("x1", 2d);
			contex.setVariable("x2", 1d);
			Double val =expression.evaluate(contex);
			LOGGER.debug("Result: " + val);
		} catch (RuleApplyingException | ExpressionEvaluationException e) {
			LOGGER.error(e);
		}
		

		

	}

}

package com.epam.jmp.tasks.junit.mathparser;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.junit.mathparser.expression.Expression;
import com.epam.jmp.tasks.junit.mathparser.expression.ExpressionContext;
import com.epam.jmp.tasks.junit.mathparser.rule.RuleApplyingException;
import com.epam.jmp.tasks.junit.mathparser.rule.RuleContext;
import com.epam.jmp.tasks.junit.mathparser.rule.PlusMinusRule;
import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.parser.CloseBraceTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.MultiplyDivideOperatorTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.NumberTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.OpenBraceTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.PlusMinusOperatorTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.TokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.TokensParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.WhiteSpaceTokenParser;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println();
		
		TokenParser[] tokenParsers = {new PlusMinusOperatorTokenParser(),
				                      new MultiplyDivideOperatorTokenParser(),
				                      new OpenBraceTokenParser(),
				                      new CloseBraceTokenParser(),
				                      new NumberTokenParser()};
		
		TokensParser tokensParser = TokensParser.createParser(tokenParsers);

		String source = "1+ 1/2*1/2+2";
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
			Double val =expression.evaluate(new ExpressionContext() {
			});
			LOGGER.debug("Result: " + val);
		} catch (RuleApplyingException e) {
			LOGGER.error(e);
		}
		

		

	}

}

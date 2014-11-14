package com.epam.jmp.tasks.junit.mathparser;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.junit.mathparser.token.Token;
import com.epam.jmp.tasks.junit.mathparser.token.parser.CloseBraceTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.NumberTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.OpenBraceTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.SummaOperatorParser;
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
		
		TokenParser[] tokenParsers = {new WhiteSpaceTokenParser(),
				                      new SummaOperatorParser(),
				                      new OpenBraceTokenParser(),
				                      new CloseBraceTokenParser(),
				                      new NumberTokenParser()};
		
		TokensParser tokensParser = TokensParser.createParser(tokenParsers);

		String source = "  2+3-  4(1 ) 9)";

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
	}

}

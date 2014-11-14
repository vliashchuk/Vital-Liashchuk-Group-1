package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class TokensParser {

	private static final Logger LOGGER = Logger.getLogger(TokensParser.class);
	
	private List<TokenParser> parsers = new ArrayList<>();
	

	public List<TokenParser> getParsers(){
		return parsers;
	}

	public List<Token> parse(String source) {
		int pos = 0;

		List<Token> result = new ArrayList<>();

		while (true) {
			Token tok = null;

			for (TokenParser parser : getParsers()) {
				if (parser == null)
					continue;

				tok = parser.parseToken(source, pos);

				if (tok != null) {
					pos = tok.getEnd();
					break;
				}
			}
			
			if (tok == null){
				break;
			}
			
			LOGGER.trace("Parsed token: " + tok);
			result.add(tok);
			

		}
		
		if(pos!=source.length()){
			throw new IllegalArgumentException("String '" + source + "' can not be parsed starting from position " + pos + ": '" + source.substring(pos) + "'");
		}
				
		return result;
	}
	
}

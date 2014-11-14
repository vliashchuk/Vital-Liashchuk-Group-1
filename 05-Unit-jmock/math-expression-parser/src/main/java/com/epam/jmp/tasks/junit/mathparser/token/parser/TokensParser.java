package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class TokensParser {

	private static final Logger LOGGER = Logger.getLogger(TokensParser.class);
	
	private List<TokenParser> parsers;
	

	public TokensParser(List<TokenParser> parsers){
		this.parsers = parsers;
	}
	
	public static TokensParser createParser(TokenParser[] tokenParsers){
		
		List<TokenParser> parsers = new ArrayList<>();
		
		for(TokenParser tokenParser:tokenParsers){
			if(tokenParser!=null){
				parsers.add(tokenParser);
			}else{
				throw new IllegalArgumentException("Parameter keep null value :" + tokenParsers);
			}
		}
		return new TokensParser(parsers);

	}

	public Token[] parse(String source) {

		List<Token> result = new ArrayList<>();

		ParsingContext context = new ParsingContext(source);
		
		while (true) {
			Token tok = null;

			for (TokenParser parser : parsers) {

				tok = parser.parseToken(context);

				if (tok != null) {
					break;
				}
			}
			
			if (tok == null){
				break;
			}
			
			LOGGER.trace("Parsed token: " + tok);
			result.add(tok);
			

		}
		
		if(context.getCurrentPosition()!=context.getSource().length()){
			throw new IllegalArgumentException("String '" + context.getSource()
											  +"' can not be parsed starting from position "
											  + context.getCurrentPosition() + ": '"
											  + context.getSource().substring(context.getCurrentPosition()) + "'");
		}
				
		return result.toArray(new Token[result.size()]);
	}
	
}

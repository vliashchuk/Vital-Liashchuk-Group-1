package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public abstract class AbstractTokenParser implements TokenParser {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractTokenParser.class);
	
	@Override
	public final Token parseToken(ParsingContext context) {

		if (context.getCurrentPosition() >= context.getSource().length()){
			return null;
		}

		Pattern p = getPattern();
		Matcher m = p.matcher(context.getSource().substring(context.getCurrentPosition()));
		if (!m.matches()){
			return null;
		}

		String mText = m.group(1);
		
		LOGGER.trace("Creating token for extracted text: '"+ mText + "'");
		
		Token token = createToken(mText);

		context.setCurrentPosition(context.getCurrentPosition() + mText.length());
		
		return token;
	}
	
	protected abstract Pattern getPattern();
	protected abstract Token createToken(String text);
	

}

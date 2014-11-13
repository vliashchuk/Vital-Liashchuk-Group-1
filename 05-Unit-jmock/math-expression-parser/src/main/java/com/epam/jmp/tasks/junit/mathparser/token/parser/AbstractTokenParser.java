package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public abstract class AbstractTokenParser implements TokenParser {
	
	@Override
	public final Token parseToken(String source, int fromIndex) {
		if (source == null) {
			throw new IllegalArgumentException("source == null");
		}

		if (fromIndex >= source.length())
			return null;

		Pattern p = getPattern();
		Matcher m = p.matcher(source.substring(fromIndex));
		if (!m.matches())
			return null;

		String mText = m.group(1);

		Token tok = createToken(source, mText, fromIndex, fromIndex
				+ mText.length());

		return tok;
	}
	
	protected abstract Pattern getPattern();
	protected abstract Token createToken(String source, String text, int beginIndex, int endIndex);
	

}

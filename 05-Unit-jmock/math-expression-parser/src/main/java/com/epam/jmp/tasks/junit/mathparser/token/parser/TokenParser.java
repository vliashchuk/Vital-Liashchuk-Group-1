package com.epam.jmp.tasks.junit.mathparser.token.parser;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public interface TokenParser {
	/**
	* Анализирует строку символов и возвращает соответствующую лексему
	* @param source Исходная строка символов
	* @param fromIndex Индекс символа с которого начать анализ
	* @return Лексема или null, если лексема не распознана
	*/
	public Token parseToken(String source,int fromIndex);
}

package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class TokensParser {

	private List<TokenParser> parsers = new ArrayList<>();
	
	/**
	* Указывает список лексических анализаторов (анализаторов лексем)
	* @return Список лексических анализаторов
	*/
	public List<TokenParser> getParsers(){
		return parsers;
	}

	/**
	* Анализирует текст и возвращает распознанные лексемы
	* @param source Исходный текст
	* @return Список распознаны лексем
	*/
	public List<Token> parse(String source) {
		// Индекс символа с которого начать анализ
		int pos = 0;

		// Здесь будет храниться резуьтат
		List<Token> result = new ArrayList<>();

		while (true) {
			Token tok = null;

			// Последовательно перебираем анализаторы
			for (TokenParser parser : getParsers()) {
				if (parser == null)
					continue;

				// Анализируем лексему
				tok = parser.parseToken(source, pos);

				// Лексема распознана? если да то добавляем как результат
				// и переходим к след. символам
				if (tok != null) {
					pos = tok.getEnd();
					break;
				}
			}

			// Ниодин анализатор не сработал, завершаем работу
			if (tok == null)
				break;

			// Добавляем как результат
			result.add(tok);
		}
		return result;
	}
	
}

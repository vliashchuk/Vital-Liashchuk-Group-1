package com.epam.jmp.tasks.junit.mathparser.token.parser;

import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public class TokensParser {

	private List<TokenParser> parsers = new ArrayList<>();
	
	/**
	* ��������� ������ ����������� ������������ (������������ ������)
	* @return ������ ����������� ������������
	*/
	public List<TokenParser> getParsers(){
		return parsers;
	}

	/**
	* ����������� ����� � ���������� ������������ �������
	* @param source �������� �����
	* @return ������ ���������� ������
	*/
	public List<Token> parse(String source) {
		// ������ ������� � �������� ������ ������
		int pos = 0;

		// ����� ����� ��������� ��������
		List<Token> result = new ArrayList<>();

		while (true) {
			Token tok = null;

			// ��������������� ���������� �����������
			for (TokenParser parser : getParsers()) {
				if (parser == null)
					continue;

				// ����������� �������
				tok = parser.parseToken(source, pos);

				// ������� ����������? ���� �� �� ��������� ��� ���������
				// � ��������� � ����. ��������
				if (tok != null) {
					pos = tok.getEnd();
					break;
				}
			}

			// ������ ���������� �� ��������, ��������� ������
			if (tok == null)
				break;

			// ��������� ��� ���������
			result.add(tok);
		}
		return result;
	}
	
}

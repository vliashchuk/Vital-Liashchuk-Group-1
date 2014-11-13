package com.epam.jmp.tasks.junit.mathparser.token;

import java.util.List;

import com.epam.jmp.tasks.junit.mathparser.token.parser.CloseBraceTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.NumberTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.OpenBraceTokenParser;
import com.epam.jmp.tasks.junit.mathparser.token.parser.TokensParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �������������� ����������� ����������
		TokensParser tokParser = new TokensParser();

		tokParser.getParsers().add(new OpenBraceTokenParser());
		tokParser.getParsers().add(new CloseBraceTokenParser());
		tokParser.getParsers().add(new NumberTokenParser());

		// ��� �������� ��������� ������� ����� ���������
		String source = " 23 4(1 ) 9)";

		// ��������� �������
		List<Token> tokens = tokParser.parse(source);
		if( tokens==null )
		{
		System.out.println("no tokens");
		return;
		} else System.out.println(tokens);
	}

}

package com.epam.jmp.tasks.junit.mathparser.token.parser;

import com.epam.jmp.tasks.junit.mathparser.token.Token;

public interface TokenParser {
	/**
	* ����������� ������ �������� � ���������� ��������������� �������
	* @param source �������� ������ ��������
	* @param fromIndex ������ ������� � �������� ������ ������
	* @return ������� ��� null, ���� ������� �� ����������
	*/
	public Token parseToken(String source,int fromIndex);
}

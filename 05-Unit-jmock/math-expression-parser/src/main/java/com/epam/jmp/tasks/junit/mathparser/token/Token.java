package com.epam.jmp.tasks.junit.mathparser.token;

public class Token {

	public Token(String soutce, String text, int beginIndex, int endIndex){
		this.soutce = soutce;
		this.text = text;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	
	String soutce;
	String text;
	private int beginIndex;
	private int endIndex;
	
	/**
	* Возвращает индекс начала лексемы
	* @return Индекс начала лексемы
	*/
	public int getBegin(){
		return beginIndex;
	}

	/**
	* Возвращает конца лексемы (исключительно)
	* @return Индекс конца лексемы (исключительно)
	*/
	public int getEnd(){
		return endIndex;
	}

	/**
	* Возвращает исходный текст
	* @return Исходная строка
	*/
	public String getSource(){
		return soutce;
	}

	/**
	* Возвращает текст лексемы
	* @return текст лексемы
	*/
	public String getText(){
		return text;
	}

	
}

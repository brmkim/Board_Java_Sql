package com.eagle.util;

public class OutUtil {
	// 문자를 받아서 전달 받은 갯수만큼 반복 출력하는 메서드
	public static void repeatChar(String ch, int n) {
		for(int i = 1; i<= n ;i++)
			System.out.print(ch);
		System.out.println();
	}
	
	// 입력받는 문자를 30개 찍는 메서드
	public static void repeatChar(String ch) {
		repeatChar(ch, 52);
	}
	
	// "=" 문자를 30개 찍는 메서드
	public static void repeatChar() {
		repeatChar("=");
	}
	
	// 제목을 출력하는 메서드
	public static void printTitle(String title) {
		repeatChar();
		System.out.println(title);
		repeatChar();
	}
	
	// 메뉴를 출력하는 메서드
	public static void printMenu
	(String menu, String ch, int n) {
		repeatChar(ch, n);
		System.out.println(menu);
		repeatChar(ch, n);
	}
}

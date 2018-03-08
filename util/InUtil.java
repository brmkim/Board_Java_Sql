package com.eagle.util;

import java.util.Scanner;

public class InUtil
{
	public static Scanner scanner = new Scanner(System.in);

	public static String getString()
	{
		return scanner.nextLine();
	}

	public static String getString(String str)
	{
		System.out.print(str + ":");
		return getString();
	}

	public static String getMenu(String str)
	{
		System.out.print(str + ":");
		return getString();
	}

	public static String getMenu(String menu, String str)
	{
		System.out.println("=======================메뉴선택========================");
		System.out.println(menu);
		System.out.println("====================================================");
		return getMenu(str);
	}

	// 숫자가 아닌 데이터가 들어오면 예외처리한다.
	public static int getInt() throws Exception
	{
		return Integer.parseInt(getString());
	}

	// 입력데이터 항목 이름과 함께 숫자가 들어오면 예외처리한다.
	public static int getInt(String str) throws Exception
	{
		return Integer.parseInt(getString(str));
	}

	public static int getIntWithCheck()
	{
		while (true)
		{
			// 숫자를 받는다
			try
			{
				// 숫자를 받아서 리턴한다.
				return getInt();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 숫자형식에 안 맞는 경우
				System.out.println("숫자를 [0-9]입력하셔야 합니다");
			}
		}
	}

	public static int getIntWithCheck(String str)
	{
		while (true)
		{
			// 숫자를 받는다
			try
			{
				// 숫자를 받아서 리턴한다.
				return getInt(str);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				// e.printStackTrace();
				// 숫자형식에 안 맞는 경우
				System.out.println("숫자를 [0-9]입력하셔야 합니다");

			}
		}
	}

}

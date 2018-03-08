package com.eagle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil
{

	private static String driver = "oracle.jdbc.driver.OracleDriver"; // 오라클사에서 제공
	// servier 정보
	private static String server = "jdbc:oracle:thin:@401PC-B5:1521:orcl";
	private static String uid = "eagle";
	private static String upd = "eagle";
	

	static
	{ // 1. 드라이버 확인 --> static 자동 초기화 블록 실행
		try
		{
			Class.forName(driver);
//			System.out.println("정상적으로 jdbc 드라이버를 확인하였습니다.");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// connection 객체룰 전달해주는 프로그램 작성
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(server, uid, upd);
	}

	// DB관련 객체를 닫는 메서드
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException
	{
		close(con, pstmt);
		if (rs != null)
			rs.close();
	}

	// DB관련 객체를 닫는 메서드 insert, update, delect
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException
	{
		if (con != null)
			con.close();
		if (pstmt != null)
			pstmt.close();

	}
}

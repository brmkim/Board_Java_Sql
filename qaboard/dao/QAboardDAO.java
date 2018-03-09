package com.eagle.qaboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eagle.util.DBUtil;

import com.eagle.qaboard.dto.QAboardDTO;

import com.eagle.qaboard.dto.*;

public class QAboardDAO {
	/*
	 * list() method
	 */
	public List<QAboardDTO> list() throws SQLException{
		//System.out.println("QAboardDAO.list()호출됨");
		List<QAboardDTO> list = null;
		
		//RDBMS에서 데이터를 가져오는 프로그램 작성		
		// 필요객체 선언
		  //DBUtil의 getConnection()은 server에 uid, upw 이용해 연결한 DriverManager를 return 한다. 
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; // SQL문을 나타내는 객체
		ResultSet rs = null;			// 쿼리문의 반환값을 담는 객체
		
		try {
			/*
			 * 1. 드라이버 및 연결 
			 * 2. 게시판을 계층형으로 내용까지 자세히 보는 sql문 작성
			 * 3. 처리객체
			 * 4. 실행
			 * 5. 표시
			 * 6. 닫기
			 */
			// 1. 
			//System.out.println("QAboard 드라이버 및 연결 확인");
			// 2. 게시판을 계층형으로 보는 sql문
			String sql = "select no, title, writer, writedate, grp, seq, lvl from qaboard order by grp, seq asc"; 

//			String sql = "SELECT CASE WHEN (LVL = 0 OR LVL > 0) THEN NO END" + 
//					" NO, RPAD('+', LVL, '-') || TITLE \"TITLE\", WRITER, WRITEDATE" + 
//					" FROM QABOARD" + 
//					" GROUP BY NO, GRP, LVL, TITLE, WRITER, WRITEDATE" + 
//					" ORDER BY GRP";
			// 3. 처리객제
			pstmt = driver.prepareStatement(sql); // DB에 쿼리문을 보내고 결과를 PreparedStatement instance에 저장
			// 4. 실행
			rs = pstmt.executeQuery();  // PreparedStatement 클래스에 있는 executeQuery() 실행. 쿼리문을 실행하는 메서드
			// 6. 표시
			if (rs == null)
				System.out.println("데이터가 없습니다.");
			else{
				while (rs.next()){   // rs에 데이터가 있을 동안...
					if (list == null) 
						list = new ArrayList<>();
					// 게시판의 데이터를 담는 객체 생성
					QAboardDTO qaBoardDTO = new QAboardDTO();
					// 게시판 데이터 담기 (rs에서 꺼내서 boadDTO에 담는다)					
					qaBoardDTO.setNo(rs.getInt("no"));
					qaBoardDTO.setTitle(rs.getString("title"));
					qaBoardDTO.setWriter(rs.getString("writer"));
					qaBoardDTO.setWriteDate(rs.getString("writedate"));
					qaBoardDTO.setGrp(rs.getInt("grp"));
					qaBoardDTO.setSeq(rs.getInt("seq"));
					qaBoardDTO.setLvl(rs.getInt("lvl"));
					
					list.add(qaBoardDTO); // list에 boardDTO object를 담는다
					
				} // while end
				} // else end
			}catch (Exception e) {
				System.out.println("데이터를 불러오는  중 오류가 발생했습니다.");
				e.printStackTrace();
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt, rs);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end
		
		return list;
	}// public List<QAboardDTO> list() end
	
	/*
	 * view() method
	 */
	public QAboardDTO view(int n) throws SQLException {
		//System.out.println("QAboardDAO.view()호출됨");
		// 게시판의 데이터를 담는 객체 생성
		QAboardDTO qaBoardDTO = new QAboardDTO();
		//RDBMS에서 데이터를 가져오는 프로그램 작성
		// 필요객체 선언
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; 
		ResultSet rs = null;			
		
		try {		
			// 1. 
			//System.out.println("QAboard 드라이버 및 연결 확인");
			// 2.게시글을 보는 sql문
			String sql = "select no, title, content, writer, writedate, grp, seq, lvl from qaboard where no = " + n; 
			// 3. 처리객제
			pstmt = driver.prepareStatement(sql); // DB에 쿼리문을 보내고 결과를 PreparedStatement instance에 저장
			// 4. 실행
			rs = pstmt.executeQuery();  // PreparedStatement 클래스에 있는 executeQuery() 실행. 쿼리문을 실행하는 메서드
			// 6. 표시
			if (rs == null)
				System.out.println("데이터가 없습니다.");
			else{
				while (rs.next()){   // rs에 데이터가 있을 동안...
										
					// 게시글 데이터 담기 (rs에서 꺼내서 boadDTO에 담는다)					
					qaBoardDTO.setNo(rs.getInt("no"));
					qaBoardDTO.setTitle(rs.getString("title"));
					qaBoardDTO.setContent(rs.getString("content"));
					qaBoardDTO.setWriter(rs.getString("writer"));					
					qaBoardDTO.setWriteDate(rs.getString("writedate"));
					qaBoardDTO.setGrp(rs.getInt("grp"));
					qaBoardDTO.setSeq(rs.getInt("seq"));
					qaBoardDTO.setLvl(rs.getInt("lvl"));
				} // while end
				} // else end
			}catch (Exception e) {
				System.out.println("데이터를 불러오는  중 오류가 발생했습니다.");
				e.printStackTrace();
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt, rs);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end
				
		return qaBoardDTO;
	} //  QAboardDTO view(int n) end	
	
	/* 
	 * write()
	 */
	public void write(QAboardDTO dto) throws SQLException {
		//System.out.println("QAboardDAO.write()호출됨");
		
		//RDBMS에서 데이터를 가져오는 프로그램 작성
		// 필요객체 선언
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; 
		
		try {		
			// 1. 
			System.out.println("QAboard 드라이버 및 연결 확인");
			
			String sql = "insert into qaboard values (qa_seq.nextval, ?, ?, ?, sysdate, qa_seq.currval, 1, 0)";
			pstmt = driver.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.executeQuery();
			/*
			 * 이 방법 또한 가능하다. 
			 * String sql = "insert into qaboard values (qa_seq.nextval, '" + dto.getTitle() + "', '" + dto.getContent  
			 * 				+ "', '" + dto.getWriter + ", sysdate, qa_seq.currval, 1, 0)";
			 * 
			 * pstmt = driver.prepareStatement(sql); // DB에 쿼리문을 보내고 결과를 PreparedStatement instance에 저장
			 *
			 * rs = pstmt.executeQuery; // PreparedStatement 클래스에 있는 executeQuery() 실행. 쿼리문을 실행하는 메서드
			 *
			 * rs.insertRow(); // 새 글(row)을 DB table에 넣는다. 
			 */			
			
		}catch(Exception e){
			System.out.println("글쓰기를 하는 중 오류가 발생했습니다.");
			e.printStackTrace();
					
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end
	} // write() end
	
	/*
	 * 질문글 수정 method					
	 */
	public void edit(QAboardDTO dto) throws SQLException{
		//System.out.println("QAboardDAO.questionEdit()호출됨");
		
		// DBMS 연결
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; 
		
		try {
			System.out.println("QAboard 드라이버 및 연결 확인");
			
			String sql = "update qaboard set title = ?, content = ?, writer = ?, writedate = sysdate where no = ?"; 
			pstmt = driver.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.setInt(4, dto.getNo());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("글수정을 하는 중 오류가 발생했습니다.");
			e.printStackTrace();
					
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end		
	} // questionEdit() end
	
	/*
	 * 답변 글쓰기용 method
	 */
	public void answerWrite(QAboardDTO dto) throws SQLException{	
		//System.out.println("QAboardDAO.answerWrite()호출됨");
		
		// DBMS 연결
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; 
		
		try {
			
			String sql = "insert into qaboard values(qa_seq.nextval, ?, ?, ?, sysdate, "
						  + " ?, ?, ?)"; 
			//// 답변 글쓰기용 생성자
			//public QAboardDTO(String title, String content, String writer, int grp, int seq, int lvl)
			
			pstmt = driver.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.setInt(4, dto.getGrp()); 
			pstmt.setInt(5,  dto.getSeq() + 1);   // seq와 lvl은 ++ 된다 
			pstmt.setInt(6,  dto.getLvl() + 1);
			
			pstmt.executeQuery();
			
		}catch(Exception e){
			System.out.println("답변글을 작성 하는 중 오류가 발생했습니다.");
			e.printStackTrace();
					
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end		
	} // answerWrite() end
	
	/*
	 * 질문글 삭제 method
	 */
	public void questionDelete(int n) throws SQLException{	
		//System.out.println("QAboardDAO.answerDelete()");
		
		// DBMS 연결
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; 
			
		try {
			
			String sql = "delete from qaboard where grp = " + n;
			pstmt = driver.prepareStatement(sql);
			pstmt.executeQuery();
			
		}catch(Exception e){
			System.out.println("글을 삭제 하는 중 오류가 발생했습니다.");
			e.printStackTrace();
					
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end		
	
	} // questionDelete() end
	/*
	 * 답변글 삭제 method
	 */
	public void answerDelete(int n) throws SQLException{	
		//System.out.println("QAboardDAO.answerDelete()");
		
		// DBMS 연결
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; 
			
		try {
			
			String sql = "delete from qaboard where no = " + n;
			pstmt = driver.prepareStatement(sql);
			pstmt.executeQuery();
			
		}catch(Exception e){
			System.out.println("글을 삭제 하는 중 오류가 발생했습니다.");
			e.printStackTrace();
					
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end		
	
	} // answerDelete() end
	/*
	 * 질문글과 그 답변글을 부르는 method
	 */
	public List<QAboardDTO> qnaList(int n) throws SQLException {
		System.out.println("QAboardDAO.qnaList()");
		
		List<QAboardDTO> list = null;
		Connection driver = DBUtil.getConnection();	
		PreparedStatement pstmt = null; // SQL문을 나타내는 객체
		ResultSet rs = null;			// 쿼리문의 반환값을 담는 객체
		
		try {
		//	System.out.println("QAboard 드라이버 및 연결 확인");
			String sql = "select no, title, writer, writedate, grp, seq, lvl "
					+ "from qaboard where grp = " + n + " order by grp, seq asc"; 
			
			pstmt = driver.prepareStatement(sql); // DB에 쿼리문을 보내고 결과를 PreparedStatement instance에 저장
			
			rs = pstmt.executeQuery();  // PreparedStatement 클래스에 있는 executeQuery() 실행. 쿼리문을 실행하는 메서드
			
			if (rs == null)
				System.out.println("데이터가 없습니다.");
			else{
				while (rs.next()){   // rs에 데이터가 있을 동안...
					if (list == null) 
						list = new ArrayList<>();
					// 게시판의 데이터를 담는 객체 생성
					QAboardDTO qaBoardDTO = new QAboardDTO();
					// 게시판 데이터 담기 (rs에서 꺼내서 boadDTO에 담는다)					
					qaBoardDTO.setNo(rs.getInt("no"));
					qaBoardDTO.setTitle(rs.getString("title"));
					qaBoardDTO.setWriter(rs.getString("writer"));
					qaBoardDTO.setWriteDate(rs.getString("writedate"));
					qaBoardDTO.setGrp(rs.getInt("grp"));
					qaBoardDTO.setSeq(rs.getInt("seq"));
					qaBoardDTO.setLvl(rs.getInt("lvl"));
					
					list.add(qaBoardDTO); // list에 boardDTO object를 담는다
					
				} // while end
				} // else end
			}catch (Exception e) {
				System.out.println("데이터를 불러오는  중 오류가 발생했습니다.");
				e.printStackTrace();
		}finally {
			try {
				//7. 객체 닫기
				DBUtil.close(driver, pstmt, rs);
			}catch (Exception e) {
				System.out.println("커넥션을 닫는 중 오류가 발생했습니다.");
				e.printStackTrace();
			}
		} // finally end
		
		return list;
		
	} // qnaList() end	
	
} // class QAboardDAO end

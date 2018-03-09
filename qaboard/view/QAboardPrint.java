package com.eagle.qaboard.view;

import java.util.List;
import com.eagle.util.OutUtil;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardPrint {
	
	public void list(List<QAboardDTO> list){
		//System.out.println("QABoardPrint.list()호출됨");
		OutUtil.printTitle("질문게시판 리스트");
		//String menu = "글번호\t글그룹\t제목\t\t작성자\t작성일";
		//OutUtil.printMenu(menu, "-", 70);
		printAlignment("글번호", 7);
		printAlignment("글그룹", 7);
		printAlignment("\t제목", 50);
		printAlignment("\t작성자", 15);
		printAlignment("\t작성일", 50);
		System.out.println();
		OutUtil.repeatChar("-", 80);
		try {
			for (QAboardDTO boardDTO: list) {
				System.out.println(boardDTO);
//				printAlignment(boardDTO.getNo(), 5);
//				printAlignment(boardDTO.getGrp(), 5);  // 이 컬럼은 프린트할 필요가 없으나 개발시 확인차 필요
//				printAlignment(boardDTO.getTitle(), boardDTO.getLvl(),40);
//				printAlignment(boardDTO.getWriter(), 20);
//				printAlignment(boardDTO.getWriteDate(), 50);
//				System.out.println();

			}
		} catch(Exception e) {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		OutUtil.repeatChar("-", 80);
	}// list() end
	
	public void view(QAboardDTO bdto){
		//System.out.println("QAboardPrint.view()호출됨");
		OutUtil.printTitle("게시글");
//		String menu = "글번호\t\t제목\t\t글내용\t\t작성자\t작성일";
//		OutUtil.printMenu(menu, "-", 70);
		printAlignment("글번호", 7);
		printAlignment("\t글제목", 40);
		printAlignment("\t글내용", 40);
		printAlignment("\t\t작성자", 15);
		printAlignment("\t\t작성일", 50);
		System.out.println();
		OutUtil.repeatChar("-", 90);

		try {
//			System.out.println("   "+ bdto.getNo() + "\t" + bdto.getTitle() + "\t" + bdto.getContent() + "\t" + bdto.getWriter()
//							+ " "+ bdto.getWriteDate());
			printAlignment(bdto.getNo(), 5);			
			printAlignment(bdto.getTitle(), bdto.getLvl(),40);
			printAlignment(bdto.getContent(), 40);
			printAlignment(bdto.getWriter(), 15);
			printAlignment(bdto.getWriteDate(), 50);
			System.out.println();

			
		} catch (Exception e) {
			System.out.println("게시글이 없습니다.");
		}
		OutUtil.repeatChar("-", 90);
	} // view() end	
	
	
	/*
	 * 게시판 컬럼이름 및 QAboardDTO 내용 프린트 정렬을 위한 메소드
	 */
	public void printAlignment(String value, int length) { 
		System.out.print(value);
		int pad = length - value.length();
		for (int i = 0; i < pad; i++)
			System.out.print(" ");
	}
	/*
	 * 위와 같은 메소드. 
	 * int 밸류 처리를 위한 오버로딩 메소드이다 
	 */
	public void printAlignment(int value, int length) { 
		String str = String.valueOf(value);  // wrapping
		System.out.print(str);
		int pad = length - str.length();
		for (int i = 0; i < pad; i++)
			System.out.print(" ");		
	}
	/*
	 * 글제목 프린트를 위한 오버로딩 메소드. lvl 변수 추가
	 */
	public void printAlignment(String title, int lvl, int length) {
		// 패드 넣음 (계층 표현)
		for (int i = 0; i < lvl; i++)
			System.out.print("-");
		// 제목출력
		System.out.print(title);
		int pad = length - lvl - title.length();
		for (int i = 0; i < pad; i++)
			System.out.print(" ");
	}

} // class end

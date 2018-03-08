package com.eagle.qaboard.view;

import java.util.List;

import com.eagle.util.OutUtil;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardPrint {
	
	public void list(List<QAboardDTO> list){
		System.out.println("QABoardPrint.list()호출됨");
		OutUtil.printTitle("질문게시판 리스트");
		String menu = "글번호\t글그룹\t제목\t\t작성자\t작성일";
		OutUtil.printMenu(menu, "-", 70);
		try {
			for (QAboardDTO boardDTO: list)
				System.out.println(boardDTO);
		} catch(Exception e) {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		OutUtil.repeatChar("-", 70);
	}// list() end
	
	public void view(QAboardDTO bdto){
		System.out.println("QAboardPrint.view()호출됨");
		OutUtil.printTitle("게시글");
		String menu = "글번호\t\t제목\t\t글내용\t\t작성자\t작성일";
		OutUtil.printMenu(menu, "-", 70);
		
		try {
			System.out.println("   "+ bdto.getNo() + "\t" + bdto.getTitle() + "\t" + bdto.getContent() + "\t" + bdto.getWriter()
							+ " "+ bdto.getWriteDate());
		} catch (Exception e) {
			System.out.println("게시글이 없습니다.");
		}
		OutUtil.repeatChar("-", 70);
	} // view() end	
	

} // class end
/*
//글보기용 생성자 
	public QAboardDTO(int no, String title, String content, String writer, String writeDate, int grp, int seq, int lvl) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.grp = grp;   // grp, seq, lvl은 리스트 정렬을 위해 필요하다
		this.seq = seq;
		this.lvl = lvl;
*/
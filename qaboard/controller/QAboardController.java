package com.eagle.qaboard.controller;

import java.sql.SQLException;
import java.util.List;

import com.eagle.main.ControllerInterface;
import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;
import com.eagle.qaboard.service.QAboardAnswerDeleteService;
import com.eagle.qaboard.service.QAboardAnswerWriteService;
import com.eagle.qaboard.service.QAboardEditService;
import com.eagle.qaboard.service.QAboardListService;
import com.eagle.qaboard.service.QAboardListService2;
import com.eagle.qaboard.service.QAboardQuestionDeleteService;
import com.eagle.qaboard.service.QAboardViewService;
import com.eagle.qaboard.service.QAboardWriteService;
import com.eagle.util.InUtil;
import com.eagle.qaboard.view.QAboardPrint;

public class QAboardController implements ControllerInterface{
	
	public void selectMenu() throws SQLException {
		//System.out.println("BoardController.selectMenu()");
		// 메뉴출력 및 입력, 처리 무한반복
		while (true) {
			
			String menu = InUtil.getMenu("1.질문게시판보기  2.글보기 3.질문글쓰기 4.질문글삭제 5.답변글쓰기 6.답변글삭제 7.글수정 0.이전메뉴", 
					"메뉴선택");
			
			switch (menu) {
			case "1":
				System.out.println("질문게시판"); //질문게시판의 질문글, 답변글 전체 리스트 (내용 X)
				// 서비스호출해서 데이터 가져옴; 가져온 데이터를 출력
				QAboardListService boardLS = new QAboardListService();
				List<QAboardDTO> list = boardLS.process();
				// 가져온 데이터를 출력
				QAboardPrint boardPrint = new QAboardPrint();
				boardPrint.list(list);		
				
				break;
			case "2":
				System.out.println("질문/답변 글 읽기");
				//몇번 게시판의 글을 볼지 묻고 스캔.
				System.out.print("몇 번 게시글을 보겠습니까?-> ");
				try {
					int num = InUtil.getInt();
					// BoardViewService 객체 생성
					QAboardViewService qaBoardVS = new QAboardViewService();
					QAboardDTO qaBoardDTO = qaBoardVS.process(num);
					// BoardPrint 인스턴스 생성후  인스턴스 메소드 view() 호출
					QAboardPrint boardPrintView = new QAboardPrint();
					boardPrintView.view(qaBoardDTO);	
					
					
										
				} catch (Exception e) {
					System.out.println("숫자를 입력해야합니다.");
					e.printStackTrace();
				}							
				break;
			case "3":
				System.out.println("질문 글쓰기");
				// 서비스 인스턴스 생성
				QAboardWriteService writeService = new QAboardWriteService();
				try {
					// dto 생성
					QAboardDTO dto = new QAboardDTO( 
										InUtil.getMenu("제목을 입력해주세요: "), 
										InUtil.getMenu("내용을 입력해주세요: "),
										InUtil.getMenu("작성자명을 입력해주세요: "));
					// 서비스 프로세스 실행
					writeService.process(dto); // 쓴 글 DB에 자동 커밋됨
					
				}catch (Exception e) {
					e.printStackTrace();

				}
				break;
			case "4":
				System.out.println("질문 글삭제");
				System.out.println("몇번 질문글을 삭제하시겠습니까? ->");
				try {
					// 글을 불러와 프린트
					int num = InUtil.getInt();
					QAboardListService2 ls2 = new QAboardListService2();
					List<QAboardDTO> list2 = ls2.process(num);
					QAboardPrint boardPrint2 = new QAboardPrint();
					boardPrint2.list(list2);	
					// 서비스 인스턴스 생성
					QAboardQuestionDeleteService qds = new QAboardQuestionDeleteService();
					
					
					// 삭제 여부 재확인
					String str = "";
					while (true) {
						System.out.println("이 글과 이 글의 답변을 정말 삭제하시겠습니까?(y/n) ->");
						str = InUtil.getString().toLowerCase().trim();
						if (str.equals("y") || str.equals("n"))
							break;
						else {
							System.out.println("y나 n만 입력하시기 바랍니다.");
						}							
					}
					// 처리
					if (str.equals("y")) {
						qds.process(num);
						System.out.println("글(들)이 삭제되었습니다.");
					}else {
						System.out.println("글 삭제가 취소되었습니다.");
						break;  //"n" -- 아무것도 하지 않음					
					}
					
				} catch (Exception e) {
					System.out.println("숫자를 입력해야합니다.");
					e.printStackTrace();
				}					
				break;
			case "5":
				System.out.println("답변 글쓰기");
				System.out.println("몇번 질문글에 대한 답변을 쓰시겠습니까? ->");
				try {
					// 답변을 쓰고자 하는 질문글을 불러와서 프린트
					int num = InUtil.getInt();
					QAboardViewService qaBoardVS = new QAboardViewService();
					QAboardDTO qaBoardDTO = qaBoardVS.process(num);
					QAboardPrint boardPrintView = new QAboardPrint();
					boardPrintView.view(qaBoardDTO);
										
					// ----- 답변글 작성							
					QAboardAnswerWriteService awService = new QAboardAnswerWriteService();
					   //--- 답변 글쓰기용 생성자
					   //---public QAboardDTO(String title, String content, String writer, int grp, int seq, int lvl)
					QAboardDTO awdto = new QAboardDTO(
							InUtil.getString("제목을 입력해주세요: "),
							InUtil.getString("내용을 입력해주세요: "),
							InUtil.getString("작성자명을 입력해주세요: "),
							qaBoardDTO.getGrp(), // <--- qaBaordDTO 이 인스턴스는 질문글 인스턴스이다. 질문글의 GRP 밸류를 새 인스턴스의 GRP로 넣는다.
							qaBoardDTO.getSeq() + 1,  // seq와 lvl은 1씩 증가
							qaBoardDTO.getLvl() + 1			
							);  
					
					awService.process(awdto);// 쓴 글 DB에 자동 커밋됨
					
				} catch (Exception e) {
					System.out.println("숫자를 입력해야합니다.");
					e.printStackTrace();
				}	
				break;
			case "6":
				System.out.println("답변 글삭제"); //답변글은 그 글 하나만 삭제하도록 함.
				System.out.println("몇번 글을 삭제하시겠습니까? ->");
				try {
					// 글을 불러와 프린트
					int num = InUtil.getInt();
					QAboardViewService qaBoardVS = new QAboardViewService();
					QAboardDTO qaBoardDTO = qaBoardVS.process(num);
					QAboardPrint boardPrintView = new QAboardPrint();
					boardPrintView.view(qaBoardDTO);
					// 서비스 인스턴스 생성
					QAboardAnswerDeleteService ads = new QAboardAnswerDeleteService();
										
					// 삭제 여부 재확인
					String str = "";
					while (true) {
						System.out.println("이 글과 이 글의 답변을 정말 삭제하시겠습니까?(y/n) ->");
						str = InUtil.getString().toLowerCase().trim();
						if (str.equals("y") || str.equals("n"))
							break;
						else {
							System.out.println("y나 n만 입력하시기 바랍니다.");
						}							
					}
					// 처리
					if (str.equals("y")) {
						ads.process(num);
						System.out.println("글이 삭제되었습니다.");
					}else {
						System.out.println("글 삭제가 취소되었습니다.");
						break;  //"n" -- 아무것도 하지 않음					
					}
					
				} catch (Exception e) {
					System.out.println("숫자를 입력해야합니다.");
					e.printStackTrace();
				}					
				
				break;	
			case "7":
				System.out.println("글수정");
				System.out.print("몇번 글을 수정하시겠습니까? ->");
				try {
					int num = InUtil.getInt();
					// 해당 번호의 글을 부른 후 제목과 내용을 고친다. (날짜는 sql로 sysdate로 update한다.) 
					// 1) 글 불러서 프린트
					QAboardViewService qaBoardVS = new QAboardViewService();
					QAboardDTO dtoView = qaBoardVS.process(num);
					QAboardPrint boardPrintView = new QAboardPrint();
					boardPrintView.view(dtoView);	
					// 2) 수정 -- edit서비스 생성, dto 생성, edit서비스 프로세스 실행
					QAboardEditService editService = new QAboardEditService();
					QAboardDTO dtoEdit = new QAboardDTO(
											num,
											InUtil.getString("제목을 입력해주세요: "),
											InUtil.getString("내용을 입력해주세요: "),
											InUtil.getString("작성자명을 입력해주세요: "));
					
					editService.process(dtoEdit); // 수정한 대로 바로 커밋된다. 무조건 새 내용으로 덮여쓰임
					System.out.println("질문글이 수정되었습니다: \n");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "0":
				System.out.println("이전메뉴");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
								
			} // switch(menu) end			
		} // while end
	} // selectMenu() end
} // QAboardController

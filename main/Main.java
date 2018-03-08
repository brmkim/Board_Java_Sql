package com.eagle.main;

import java.sql.SQLException;

import com.eagle.gameboard.controller.GameBoardController;
import com.eagle.gamepost.controller.GamePostController;
import com.eagle.itemboard.controller.ItemBoardController;
import com.eagle.member.controller.Login;
import com.eagle.member.controller.MemberController;
import com.eagle.notice.controller.NoticeController;
import com.eagle.qaboard.controller.QAboardController;
import com.eagle.util.InUtil;
import com.eagle.util.OutUtil;

public class Main
{

	public static void main(String[] args) throws Exception
	{
		//FirstCin();
		//if (Login.getId().equals("") == false)
			Member_main();

	}

	public static void FirstCin()
	{
		Boolean result = false;
		String menu;
		System.out.println("eagle 게시판에 오신걸을 환영합니다.");

		while (true)
		{
			menu = InUtil.getMenu("1.로그인  2.회원가입  0.프로그램 종료", "메뉴선택");
			switch (menu)
			{
			case "1":
				result = Login.Loginprossce();
				if (result == true)
					return;
				break;
			case "2":
				Login.join_membership();
				break;
			case "0":
				System.out.println("시스템을 종료합니다.");
				System.exit(1);
			default:
				System.out.println("잘못된 메뉴입니다 다시입력해주세요");
				break;
			}
		}

	}

	public static void Gamemain() throws Exception
	{

		OutUtil.printTitle("★★★★게임판매 게시판에 오신 것을 환영합니다★★★★\n" + "      ★★★★원하시는 메뉴를 선택하여 주세요★★★★     ");

		// 무한반복되는 메뉴 처리
		// whileLoop:
		while (true)
		{
			// 메뉴를 출력
			String mainMenu = "1.공지사항  2.쇼핑몰  3.게임판매게시판  4.회원관리  0.종료";
			OutUtil.printMenu(mainMenu, "-", 38);
			// - 선택
			String menu = InUtil.getMenu("메뉴입력");
			System.out.println("");

			// 메뉴 처리
			switch (menu)
			{
			case "1":
				System.out.println("※※※※※※※※※※※※※※※※개 발 중 입 니 다 \n\n");
				break;

			case "2":
				System.out.println("※※※※※※※※※※※※※※※※개 발 중 입 니 다 \n\n");

				// 공지사항처리를 위해서 컨트롤러 생성, 호출
				// 생성 -> new
				// NoticeController noticeController = new NoticeController();
				// 메서드 호출해서 실행
				// noticeController.selectMenu();
				break;

			case "3":
				System.out.println("게임 판매 게시판입니다");
				GameBoardController boardController = new GameBoardController();
				boardController.selectMenu();
				break;

			case "4":
				System.out.println("※※※※※※※※※※※※※※※※개 발 중 입 니 다 \n\n");
				// MemberController memberController = new MemberController();
				// memberController.selectMenu();
				break;

			case "0":
				OutUtil.printTitle("★★★★★★★★★ 게시판을  종료합니다 ★★★★★★★★★");
				System.exit(0);

			default:
				System.out.println("잘못된 메뉴를 선택하셨습니다");
				System.out.println("[0-4] 해당 번호를 선택하세요");
				break;
			}
		}

	}

	public static void Member_main() throws SQLException
	{

		// TODO Auto-generated method stub
		// 환영인사
		// System.out.println("방가방가 ~~~~~\n");
		OutUtil.printTitle("방가방가 ~~~~~\n");
		// 메뉴입력 처리 무한 반복문
		while (true)
		{
			String mainMenu = "1.회원관리, 2.공지사항  3.게임 판매게시판  4.아이템 게시판  5.일반게시판 6.질문답변게시판 0.종료";
			// 메뉴 출력 -> 입력
			// 메뉴 1. 공지사항, 2 쇼핑몰 3 게시판 0종
			OutUtil.printMenu(mainMenu, "-", 40);
			String menu = InUtil.getMenu("메뉴 입력");
			System.out.println("");
			
			switch (menu)
			{
			case "1":
				System.out.println("회원관리");
				MemberController boardControlller = new MemberController();
				try {
					boardControlller.selectMenu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "2":
				OutUtil.printTitle("게임몰 공지사항입니다.");
				NoticeController noticeController = new NoticeController();
				noticeController.selectMenu();
				break;
			case "3":
				OutUtil.printTitle("                                   ★★★★게임판매 게시판에 오신 것을 환영합니다★★★★\n"
						+ "                                          ★★★★원하시는 메뉴를 선택하여 주세요★★★★     ");

				GameBoardController boardController = new GameBoardController();
				try
				{
					boardController.selectMenu();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "4":
				System.out.println("\n4.아이템 게시판");
				ItemBoardController itemBoardController = new ItemBoardController();
				itemBoardController.selectMenu();
				break;
			case "5":
				System.out.println("일반게시판");
				GamePostController gamePostController = new GamePostController();
				gamePostController.selectMenu(); // list 메뉴
				break;
			// System.exit(0);
			case "6":
				System.out.println("6.질문답변게시판");
				QAboardController qacon = new QAboardController();
				qacon.selectMenu();
				break;
			case "0":
				System.out.println("프로그램 종료");
				System.exit(0);
			default:
				System.out.println("잘못된 메뉴를 선택하셨습니다");
				break;
			}
		}

	}

}
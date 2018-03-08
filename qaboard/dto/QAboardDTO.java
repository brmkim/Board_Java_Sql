package com.eagle.qaboard.dto;

public class QAboardDTO {
	
	private int no;
	private String title, content, writer, writeDate;
	private int grp, seq, lvl;
		/* grp -- 한 게시글과 그 답변들은 한 그룹이며 부모-자식 관계가 된다.
    	   seq -- 같은 그룹 내 답변글 순서 
    	   lvl -- 같은 그룹 내 계층 (node level)
    	*/ 
	
	public QAboardDTO() {
		
	}
	//글보기용 생성자. 글쓰기 용으로도 쓰기로 한다. writeDate는 sysdate로 sql에 넣기. 
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
	}
	// 글리스트용 생성자 (내용 제외됨)
	public QAboardDTO(int no, String title, String writer, String writeDate, int grp, int seq, int lvl) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.grp = grp;   // grp, seq, lvl은 리스트 정렬을 위해 필요하다
		this.seq = seq;
		this.lvl = lvl;
	}
	// 글쓰기용 생성자
	public QAboardDTO(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	// 글수정용 생성자
	public QAboardDTO(int no, String title, String content, String writer) {
		this.no = no;
		this.title = title;
		this.content = content; 
		this.writer = writer;
		// 질문글은 seq가 1이고 lvl이 0 이므로 parameter로 넣지 않았다 (grp은 글번호와 동일)
	}
	// 답변 글쓰기용 생성자
	public QAboardDTO(String title, String content, String writer, int grp, int seq, int lvl) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.grp = grp; // "몇번 질문글에 대한 답변글을 쓰시겠습니까?" --> grp value 이다
		this.seq = seq; // seq++ 
		this.lvl = lvl; // lvl++ 
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate =  writeDate; // writeDate.substring(0, writeDate.indexOf(" "));
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	@Override
	public String toString(){
		String pad = "";
		for (int i = 0; i < lvl; i++)
			pad += "-";
		return  " " + no + "\t" + grp + "\t" + pad + title + "\t"  + writer + "\t" + writeDate;	
	}
	
}

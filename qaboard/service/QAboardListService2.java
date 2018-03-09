package com.eagle.qaboard.service;

import java.sql.SQLException;
import java.util.List;

import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardListService2 { // 이 것과 1의 다른 점은 이 클래스의 process는 한 질문글과 그의 답변글들의 리스트를 출력한다는 점이다.
	public List<QAboardDTO> process(int n) throws SQLException {
		//System.out.println("QAboardQnAViewService.process()호출됨");
		List<QAboardDTO> qaBoardDTO = null;
		QAboardDAO qaBoardDAO = new QAboardDAO();
		
		qaBoardDTO = qaBoardDAO.qnaList(n);
						
		return qaBoardDTO;
	}	
}

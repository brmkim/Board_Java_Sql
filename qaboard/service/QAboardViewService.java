package com.eagle.qaboard.service;

import java.sql.SQLException;
import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardViewService {

	public QAboardDTO process(int n) throws SQLException {
		//System.out.println("QAboardViewService.process()호출됨");
		QAboardDTO qaBoardDTO = new QAboardDTO();
		QAboardDAO qaBoardDAO = new QAboardDAO();
		
		qaBoardDTO = qaBoardDAO.view(n);
		
		
				
		return qaBoardDTO;
	}	
}

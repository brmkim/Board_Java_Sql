package com.eagle.qaboard.service;

import java.sql.SQLException;

import com.eagle.qaboard.dao.QAboardDAO;

public class QAboardAnswerDeleteService {
	public void process(int n) throws SQLException {
		//System.out.println("QAboardAnswerDeleteService.process()");
		
		QAboardDAO dao = new QAboardDAO();
		dao.answerDelete(n);		
	}	

}

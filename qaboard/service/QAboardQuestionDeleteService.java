package com.eagle.qaboard.service;

import java.sql.SQLException;

import com.eagle.qaboard.dao.QAboardDAO;

public class QAboardQuestionDeleteService {
	
	public void process(int n) throws SQLException {
		System.out.println("QAboardQuestionDeleteService.process()");
		
		QAboardDAO dao = new QAboardDAO();
		dao.QuestionDelete(n);		
	}	
}

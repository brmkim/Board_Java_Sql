package com.eagle.qaboard.service;

import java.sql.SQLException;

import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardAnswerWriteService {
	public void process(QAboardDTO dto) throws SQLException {
		//System.out.println("QAboardAnswerWriteService.process()");
		QAboardDAO dao = new QAboardDAO();
		dao.answerWrite(dto);
	}

}

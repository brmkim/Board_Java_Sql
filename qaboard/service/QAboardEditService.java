package com.eagle.qaboard.service;

import java.sql.SQLException;

import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardEditService {
	public void process(QAboardDTO dto) throws SQLException {
		//System.out.println("QAboardEditService.process()");
		
		QAboardDAO dao = new QAboardDAO();
		dao.edit(dto);
	}
}

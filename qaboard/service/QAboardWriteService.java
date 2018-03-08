package com.eagle.qaboard.service;

import java.sql.SQLException;

import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardWriteService {
	public void process(QAboardDTO dto) throws SQLException {
		System.out.println("QAboardWriteService.process()");
		
		QAboardDAO dao = new QAboardDAO();
		dao.write(dto);
	} // process end
} // class end

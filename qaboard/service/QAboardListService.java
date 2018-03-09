package com.eagle.qaboard.service;

import java.sql.SQLException;
import java.util.List;

import com.eagle.qaboard.dao.QAboardDAO;
import com.eagle.qaboard.dto.QAboardDTO;

public class QAboardListService {
	
	public List<QAboardDTO> process() throws SQLException{
		//System.out.println("BoardListProcess.process()호춮됨");
		List<QAboardDTO> list = null; 
		
		//list에 데이터를 가져와서 채우는 프로그램 작성. 
		QAboardDAO qaBoardDAO = new QAboardDAO();
		list = qaBoardDAO.list();
	 return list;
	}// end of process()

}

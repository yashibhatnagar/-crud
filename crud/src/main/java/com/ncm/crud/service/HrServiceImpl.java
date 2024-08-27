package com.ncm.crud.service;

import java.time.LocalDate;
import java.util.List;

import com.ncm.crud.entity.Hr;

public interface HrServiceImpl {

	public void savehr(Hr hrportal); 
		// TODO Auto-generated method stub

	public List<Hr> getdataByDateRange(LocalDate start, LocalDate end, String employee);

	

	public Hr getLeaveRequestById(Integer id);

	public boolean updateLeave(Hr hr);


	

}

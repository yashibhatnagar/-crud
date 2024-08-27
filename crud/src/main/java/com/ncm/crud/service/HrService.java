package com.ncm.crud.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncm.crud.entity.Hr;
import com.ncm.crud.repo.HrRepo;

@Service
public class HrService implements HrServiceImpl {
	@Autowired
	private HrRepo repo;

	public void savehr(Hr hrportal) {
		
		repo.save(hrportal);
		// TODO Auto-generated method stub
		
	}



	public List<Hr> getdataByDateRange(LocalDate start, LocalDate end, String employee) {
	    // Check if the end date is after the start date
	    if (start.isAfter(end)) {
	        throw new IllegalArgumentException("Start date must not be after end date.");
	    }

	    // Fetch data from the repository based on the date range and employee name
	    return repo.findByDateRangeAndEmployee(start, end, employee);
	}



	 public Hr getLeaveRequestById(Integer id) {
	        Optional<Hr> hr = repo.findById(id);
	        if (hr.isPresent()) {
	            return hr.get();
	        } else {
	            throw new RuntimeException("Leave request not found with id: " + id);
	        }
	    }

		@Override
		public boolean updateLeave(Hr hrmod) {
			Optional<Hr> existingLeaveRequest = repo.findById(hrmod.getId());
			if (existingLeaveRequest.isPresent()) {
				Hr updatedLeaveRequest = existingLeaveRequest.get();

				// Update the properties as needed
				updatedLeaveRequest.setStatus(hrmod.getStatus());
				updatedLeaveRequest.setStartDate(hrmod.getStartDate());

				updatedLeaveRequest.setModifydate(hrmod.getModifydate());

				repo.save(updatedLeaveRequest);
				return true;
			} else {
				return false;
			}
		}


}

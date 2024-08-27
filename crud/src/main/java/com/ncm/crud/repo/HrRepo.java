package com.ncm.crud.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncm.crud.entity.Hr;



@Repository
public interface HrRepo extends  JpaRepository<Hr, Integer>{

	@Query("SELECT h FROM Hr h WHERE h.startDate BETWEEN :start AND :end AND h.employeename = :employee")
	List<Hr> findByDateRangeAndEmployee(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("employee") String employee);

	@Query("SELECT DISTINCT h.status FROM Hr h")
    List<String> findDistinctStatuses();

	boolean existsByEmployeenameAndStartDateAndStatus(String employeeName, LocalDate startDate, String status);


}
package com.ncm.crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ncm.crud.entity.Student;
@Repository
public interface StudentRepositary extends  JpaRepository<Student, Integer>{

	   @Query("SELECT e.firstname FROM Student e")
	    List<String> findAllEmployeeFirstNames();
}
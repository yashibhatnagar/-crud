package com.ncm.crud.service;

import java.util.List;
import java.util.Optional;

import com.ncm.crud.entity.Student;


public interface StudentService {
Student save(Student student);
List<Student> getAll();
public void delete (Integer id) ;
public Optional<Student> getById(Integer id);
List<String> getAllStudent();
List<String> getAllEmployees();

public void deleteStudentById(int id);
	// TODO Auto-generated method 
}


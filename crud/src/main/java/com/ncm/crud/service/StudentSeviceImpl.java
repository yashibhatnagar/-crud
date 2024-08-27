package com.ncm.crud.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ncm.crud.entity.Student;
import com.ncm.crud.repo.StudentRepositary;




@Service
public  class StudentSeviceImpl implements StudentService  {
	@Autowired
	
	
	private StudentRepositary repo;
	/**/
	

	public Student save(Student student) {
		return repo.save(student);
	}

	public java.util.List<Student> getAll() {
		return repo.findAll();
	}
	
	public void delete(Integer Id) {
		repo.deleteById(Id);
	}
	public Optional<Student> getById(Integer id) {
        return repo.findById(id);
    }

	@Override
	public List<String> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	   public List<String> getAllEmployees() {
	        return repo.findAllEmployeeFirstNames();
	    }

	   public void deleteStudent(Integer id) { 
	        repo.deleteById(id);
	    }

	@Override
	public void deleteStudentById(int id) {
		// TODO Auto-generated method stub
		
	}



}
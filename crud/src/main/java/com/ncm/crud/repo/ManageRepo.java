package com.ncm.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncm.crud.entity.Manage;



@Repository
public interface ManageRepo extends JpaRepository<Manage, Long> {

	
}

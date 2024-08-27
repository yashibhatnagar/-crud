package com.ncm.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ncm.crud.entity.CreateUser;
@Repository
public interface UserRepo extends  JpaRepository<CreateUser, Integer>{
	@Query("SELECT user FROM CreateUser user WHERE user.email = ?1")
    public CreateUser findByEmail(String email);

}
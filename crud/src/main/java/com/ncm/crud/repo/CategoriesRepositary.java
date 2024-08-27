package com.ncm.crud.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncm.crud.entity.Category;





@Repository
public interface CategoriesRepositary extends JpaRepository<Category, Integer> {

	/* Object findAll(Long categoryId); */

	Optional<Category> findById(Long categoryId);

	

}
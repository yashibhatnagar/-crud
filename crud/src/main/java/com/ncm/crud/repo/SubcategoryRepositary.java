package com.ncm.crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncm.crud.entity.SubCategory;



public interface SubcategoryRepositary extends JpaRepository<SubCategory, Long> {
	  List<SubCategory> findByCategoryId(Long categoryId);
}

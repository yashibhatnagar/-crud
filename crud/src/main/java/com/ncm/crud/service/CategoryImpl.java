package com.ncm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncm.crud.entity.Category;
import com.ncm.crud.repo.CategoriesRepositary;

@Service
public class CategoryImpl {

	 @Autowired
	    private CategoriesRepositary categoryRepository;

	  
	    public Category saveCategory(Category category) {
	        return categoryRepository.save(category);
	    }


		public  List<Category> getAllCategories() {
			 return categoryRepository.findAll();
		}


		public Category getCategoryById(Long categoryId) {
		     return categoryRepository.findById(categoryId).orElse(null);
		}


	}


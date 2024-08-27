package com.ncm.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ncm.crud.entity.Category;
import com.ncm.crud.service.CategoryImpl;


@Controller
public class CategoryController {
	@Autowired
		private CategoryImpl service;
		@GetMapping("/category")
		public String category() {
			
			return "category";
		}
		
		@PostMapping("/category")
	    public String addCategory(@ModelAttribute Category category) {
	        service.saveCategory(category);
	        return "dashboard";
	    }

}

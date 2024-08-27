package com.ncm.crud.service;

import com.ncm.crud.entity.Category;

public interface CategoryService {
	public Category save2(Category category);

	public Object getAllCategories();

	public Category getCategoryById(Long categoryId);

	
	public void saveCategory(Category category);

}

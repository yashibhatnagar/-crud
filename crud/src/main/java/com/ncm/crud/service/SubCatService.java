package com.ncm.crud.service;

import java.util.List;


import com.ncm.crud.entity.SubCategory;

public interface SubCatService {
	
	public void save(SubCategory subCategory);

	public SubCategory getSubcategoryById(Long subcategoryId);

	public List<SubCategory> getSubcategoriesByCategoryId(Long categoryId);

	public SubCategory saveSubcategory(SubCategory subcategory);

	public Object getAllSubcategories();

	
}

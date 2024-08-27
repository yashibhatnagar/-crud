package com.ncm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncm.crud.entity.Category;
import com.ncm.crud.entity.SubCategory;
import com.ncm.crud.repo.CategoriesRepositary;
import com.ncm.crud.repo.SubcategoryRepositary;


@Service
public class SubcategoryImpl implements SubCatService{
    
    @Autowired
    private SubcategoryRepositary subcategoryRepo;
    
    @Autowired
    private CategoriesRepositary categoryRapo;
    
    public List<SubCategory> getAllSubcategories() {
        return subcategoryRepo.findAll();
    }
    
    public SubCategory saveSubcategory(SubCategory subcategory) {
        return subcategoryRepo.save(subcategory);
    }
    
    public List<Category> getAllCategories() {
        return categoryRapo.findAll();
    }

    public List<SubCategory> getSubcategoriesByCategoryId(Long categoryId) {
        return subcategoryRepo.findByCategoryId(categoryId);
    }

    public SubCategory getSubcategoryById(Long subcategoryId) {
        // Find subcategory by ID and return it, or throw an exception if not found
        return subcategoryRepo.findById(subcategoryId).orElse(null);
    }

	@Override
	public void save(SubCategory subCategory) {
		// TODO Auto-generated method stub
		
	}
}

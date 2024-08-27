package com.ncm.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ncm.crud.entity.SubCategory;
import com.ncm.crud.service.CategoryImpl;
import com.ncm.crud.service.SubCatService;
import com.ncm.crud.service.SubcategoryImpl;

import java.util.List;

@Controller
public class Subcontroller {

    @Autowired
    private SubcategoryImpl subcategoryService;

    @Autowired
    private CategoryImpl categoryService;

    @GetMapping("/subcat")
    public String showSubcategoryForm(Model model) {
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategory", new SubCategory());

        return "subcategory";
    }

    @PostMapping("/subcategory")
    public String addSubcategory(@ModelAttribute SubCategory subcategory) {
        subcategoryService.saveSubcategory(subcategory);
        return "dashboard";
    }
}

@RestController
class SubcategoryAjaxController {

    @Autowired
    private SubCatService subcategoryService;

    @GetMapping("/fetchSubcategories")
    @ResponseBody
    public List<SubCategory> fetchSubcategories(@RequestParam("categoryId") Long categoryId) {
        return subcategoryService.getSubcategoriesByCategoryId(categoryId);
    }
}

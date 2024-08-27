package com.ncm.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncm.crud.entity.Assets;
import com.ncm.crud.entity.Category;
import com.ncm.crud.entity.SubCategory;
import com.ncm.crud.service.AssetServiceImpl;
import com.ncm.crud.service.CategoryImpl;
import com.ncm.crud.service.SubcategoryImpl;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AssestsController {

    @Autowired
    private AssetServiceImpl assetsService;

    @Autowired
    private CategoryImpl categoryService;

    @Autowired
    private SubcategoryImpl subcategoryService;

    // GET endpoint to show the form
    @GetMapping("/Assets")
    public String showForm(@RequestParam(value = "categoryId", required = false) Long categoryId, Model model) {
        // Fetch all categories and add them to the model
    	System.out.println("categoryId "+categoryId);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        if (categoryId != null) {
            List<SubCategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);
            model.addAttribute("subcategories", subcategories);
        } else {
            model.addAttribute("subcategories", List.of()); // Initialize empty list if no category is selected
        }

        model.addAttribute("selectedCategoryId", categoryId);
        return "assets"; // Name of the Thymeleaf template
    }

    // POST endpoint to handle form submission
    @PostMapping("/assets")
    public String addItem(@RequestParam("category.id") Long categoryId,
                          @RequestParam("subcategory.id") Long subcategoryId,
                          @RequestParam("subcategory.id") SubCategory subcategory2,
                          @RequestParam("companyname") String companyname,
                          @RequestParam("slNo") String slNo,
                          @RequestParam("quantity") Integer quantity,
                          @RequestParam("date") LocalDate date,
                          Model model) {

    	System.out.println("category€Id "+categoryId);
        // Fetch category and subcategory entities from the database
        Category category = categoryService.getCategoryById(categoryId);
        SubCategory subcategory = subcategoryService.getSubcategoryById(subcategoryId);

        // Check if category and subcategory are valid
        if (category == null || subcategory == null) {
            model.addAttribute("error", "Invalid category or subcategory.");
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("subcategories", subcategoryService.getSubcategoriesByCategoryId(categoryId));
            model.addAttribute("selectedCategoryId", categoryId);
            return "assets";
        }

        // Create and populate the asset entity
        Assets asset = new Assets();
        asset.setCategoryName(category.getName()); // Store category name
        asset.setSubcatgoryName(subcategory.getSub_name()); // Store subcategory name
        asset.setCompanyname(companyname);
        asset.setSlNo(slNo);
        asset.setQuantity(quantity.toString()); // Assuming quantity is stored as a String
        asset.setCreatedDate(date);
        asset.setSubcategory(subcategory2);

        // Save the asset
        assetsService.saveAsset(asset);

        model.addAttribute("message", "Asset added successfully!");
        return "dashboard";
    }
    
    
	/*
	 * @GetMapping("/fetchSerialNumbers")
	 * 
	 * @ResponseBody public List<String> fetchSerialNumbers(@RequestParam(value =
	 * "subcategoryId", required = false) Long subcategoryId) { return
	 * assetsService.getSerialNumbersBySubcategory(subcategoryId); }
	 * 
	 * @GetMapping("/fetchCompanies")
	 * 
	 * @ResponseBody public List<String> fetchCompanies(@RequestParam(value =
	 * "subcategoryId", required = false) Long subcategoryId) { return
	 * assetsService.getCompaniesBySubcategory(subcategoryId); }
	 */
}
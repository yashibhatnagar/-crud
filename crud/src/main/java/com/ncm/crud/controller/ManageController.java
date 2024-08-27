package com.ncm.crud.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncm.crud.entity.Category;
import com.ncm.crud.entity.Manage;
import com.ncm.crud.entity.SubCategory;
import com.ncm.crud.service.AssetServiceImpl;
import com.ncm.crud.service.CategoryImpl;
import com.ncm.crud.service.ManageService;
import com.ncm.crud.service.StudentSeviceImpl;
import com.ncm.crud.service.SubcategoryImpl;





@Controller
public class ManageController {

    @Autowired
    private AssetServiceImpl assetsService;

    @Autowired
    private CategoryImpl categoryService;

    @Autowired
    private SubcategoryImpl subcategoryService;
    
    @Autowired
    private ManageService manageService;

    @Autowired
    private StudentSeviceImpl empService;

    @GetMapping("/manage")
    public String showForm(@RequestParam(value = "category", required = false) Long categoryId, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        if (categoryId != null) {
            model.addAttribute("subcategories", subcategoryService.getSubcategoriesByCategoryId(categoryId));
        }
        model.addAttribute("selectedCategoryId", categoryId);
        return "ManageAssests";
    }
    
    
    
    @PostMapping("/management")
    public String saveAsset(@RequestParam("category") Long categoryId,
            @RequestParam("subcategory") Long subcategoryId,
            @RequestParam("companyName") String companyname,
            @RequestParam("serialNumber") String slNo,
            @RequestParam("employeeName") String employeename,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("date") LocalDate date,
            Model model, RedirectAttributes redirectAttributes) {

        try {
            
            Category category = categoryService.getCategoryById(categoryId);
            SubCategory subcategory = subcategoryService.getSubcategoryById(subcategoryId);

            
            if (category == null || subcategory == null) {
                redirectAttributes.addFlashAttribute("error", "Invalid category or subcategory.");
                redirectAttributes.addFlashAttribute("categories", categoryService.getAllCategories());
                redirectAttributes.addFlashAttribute("subcategories", subcategoryService.getSubcategoriesByCategoryId(categoryId));
                redirectAttributes.addFlashAttribute("selectedCategoryId", categoryId);
                return "redirect:/assets";
            }

           
            Manage asset = new Manage();
            asset.setCategory(category.getName()); // Store category name
            asset.setSubcategory(subcategory.getSub_name()); // Store subcategory name
            asset.setCompanyName(companyname);
            asset.setSerialNumber(slNo);
            asset.setQuantity(quantity);
            asset.setDate(date);
            asset.setEmployeeName(employeename);

            
            manageService.save(asset);

           
            redirectAttributes.addFlashAttribute("message", "Asset added successfully!");
            return "redirect:/dashboard";
            
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace(); // Consider using a logger in a real application

            // Add error message
            redirectAttributes.addFlashAttribute("error", "An error occurred while adding the asset. Please try again.");

            // Redirect to the form page with error details
            return "redirect:/assets";
        }
    }
    
    @RestController
    class EmployeeController {
        @GetMapping("/fetchEmployees")
        @ResponseBody
        public List<String> fetchEmployees() {
            List<String> employees = empService.getAllEmployees();
            if (employees == null) {
                return Collections.emptyList();
            }
            return employees;
        }
    }
    
    @RestController
    class AssetController {

       

        @GetMapping("/fetchSerialNumbers")
        @ResponseBody
        public List<String> fetchSerialNumbers(@RequestParam(value = "subcategoryId", required = false) Long subcategoryId) {
            return assetsService.getSerialNumbersBySubcategory(subcategoryId);
        }

        @GetMapping("/fetchCompanies")
        @ResponseBody
        public List<String> fetchCompanies(@RequestParam(value = "subcategoryId", required = false) Long subcategoryId) {
            return assetsService.getCompaniesBySubcategory(subcategoryId);
        }
    }
    
  //for view mangement
    @GetMapping("/viewmanage")
    public String viewManagement(Model model) {
        model.addAttribute("managementItems", manageService.getAllItems());
        return "manageView";
    }
	
	  @PostMapping("/items/delete/{id}") public String
	  deleteItem(@PathVariable("id") Long id, RedirectAttributes
	  redirectAttributes) { manageService.deleteItem(id); return "manageView"; }
	 
}
package com.ncm.crud.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncm.crud.entity.Assets;
import com.ncm.crud.entity.Manage;
import com.ncm.crud.service.AssetServiceImpl;
import com.ncm.crud.service.ManageService;



@Controller
public class FilrController {

    @Autowired
    private ManageService manageService;

    @Autowired
    private AssetServiceImpl assetsService;

    // Define the date formatter for the 'dd-MM-yyyy' format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @PostMapping("/uploadCsv")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "No file selected.");
            return "redirect:/managment";
        }

        if (!file.getOriginalFilename().endsWith(".csv")) {
            redirectAttributes.addFlashAttribute("message", "Invalid file type. Please upload a CSV file.");
            return "redirect:/managment";
        }

        List<Manage> validManages = new ArrayList<>();
        List<String> invalidRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            reader.readLine(); // Skip header line if needed

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 6) {
                    invalidRecords.add(line); // Collect invalid records
                    continue; // Skip this line and proceed to the next
                }

                try {
                    String category = values[0];
                    String companyName = values[1];
                    LocalDate date = LocalDate.parse(values[2].trim(), DATE_FORMATTER);
                    String employeeName = values[3];
                    int quantity = Integer.parseInt(values[4].trim());
                    String serialNumber = values[5];
                    String subcategory = values.length > 6 ? values[6] : "";

                    // Validate if the data exists in the Assets table
                    Optional<Assets> asset = assetsService.findByCategoryAndSubcategoryAndCompanyNameAndSerialNumber(category, subcategory, companyName, serialNumber);

                    if (asset.isPresent()) {
                        Manage manage = new Manage();
                        manage.setCategory(category);
                        manage.setSubcategory(subcategory);
                        manage.setCompanyName(companyName);
                        manage.setSerialNumber(serialNumber);
                        manage.setEmployeeName(employeeName);
                        manage.setQuantity(quantity);
                        manage.setDate(date);
                        validManages.add(manage);
                    } else {
                    	invalidRecords.add(line);
                    	return "redirect:/managment";
                         // Collect invalid records
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    invalidRecords.add(line); // Collect records with parsing errors
                }
            }

            manageService.saveAll(validManages);
            redirectAttributes.addFlashAttribute("message", "File is not uploaded and  processed unsuccessfully.");
            if (!invalidRecords.isEmpty()) {
                redirectAttributes.addFlashAttribute("invalidRecords", invalidRecords);
                return "redirect:/managment";
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to process the file.");
        }

        return "redirect:/dashboard";
    }
}
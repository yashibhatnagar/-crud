package com.ncm.crud.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ncm.crud.entity.CreateUser;
import com.ncm.crud.entity.Student;
import com.ncm.crud.service.StudentSeviceImpl;
import com.ncm.crud.service.UserServiceimpl;

import com.ncm.crud.vo.UserVo;





@Controller
public class StudentController {
	@Autowired
	private StudentSeviceImpl studentService;
	@Autowired
	private UserServiceimpl CreateUserService;
	
	@Value("${upload.dir}")
    private String uploadDir; 
	
	@GetMapping("/")
	public String home() {
		return "create";
	}
	@PostMapping("/create")
	public String stuRegister(@ModelAttribute UserVo user ) {
		
		try {
			System.out.println(user);
			CreateUser createuser=new CreateUser();
			createuser=	CreateUserService.save1(user);
			
			if(createuser!=null)
			{
				 return "redirect:/login";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		 return "redirect:/login";
	}
	
	@PostMapping("/users")
	public String empRegister(@ModelAttribute Student student ) {
		System.out.println(student);
		studentService.save(student);
		
		 return "redirect:/view";
	}
	@GetMapping("/view")
	public String view(Model model) {
		model.addAttribute("student",studentService.getAll());
		return "view";
	}
	
	
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	@PostMapping("/logincheck")
    public String userlogin(@ModelAttribute CreateUser user, Model model) {
        try {
            System.out.println(user);

            
            boolean isAuthenticated = CreateUserService.authenticate(user);

            if (isAuthenticated) {
                return "dashboard"; 
            } 
            else {
                model.addAttribute("error", "Invalid credentials. Please try again.");
                return "login"; 
            }
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "login";
        }
    }

@PostMapping("/delete/{id}")
public String deleteStudent(@PathVariable("id") Integer id) {
    studentService.delete(id);
    return "redirect:/view";
}

@GetMapping("/edit/{id}")
public String editEmployee(@PathVariable("id") Integer id, Model model) {  
    Optional<Student> employeeOpt = studentService.getById(id);
    if (employeeOpt.isPresent()) {
        model.addAttribute("student", employeeOpt.get());
        return "edit";
    } else {
       
        return "redirect:/view";
    }
}

@PostMapping("/update/{id}")
public String updateEmployee(@PathVariable("id") Integer id, @ModelAttribute Student student) {
    student.setId(id);  
    studentService.save(student);
    return "redirect:/view";

}


@GetMapping("/index")
public String mainpage() {
	return "index";
	
	
}

@PostMapping("/user")
public String empRegister(@ModelAttribute Student u, @RequestParam("image") MultipartFile image) {
    try {
        if (!image.isEmpty()) {
            // Ensure directory exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); 
            }

            // here i generated a unique file name
            String originalFilename = image.getOriginalFilename();
            String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
            String imagePath = Paths.get(uploadDir, uniqueFilename).toString();
            
            // Saving the images in image directory
            Path path = Paths.get(imagePath);
            Files.write(path, image.getBytes());
            
            // Store the relative path or URL in the employee record
            u.setImagePath(uniqueFilename);
        }


        studentService.save(u);

    } catch (IOException e) {
        e.printStackTrace(); // Consider logging the error and adding user feedback
        return "error"; // Redirect or forward to an error page
    }

    return "redirect:/view"; // Redirect to a view page or wherever you want
}
}
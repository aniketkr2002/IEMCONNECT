package com.iemconnect.iemconnect;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iemconnect.iemconnect.model.College;
import com.iemconnect.iemconnect.model.Product;
import com.iemconnect.iemconnect.model.Student;
import com.iemconnect.iemconnect.service.CollegeService;
import com.iemconnect.iemconnect.service.ProductService;
import com.iemconnect.iemconnect.service.StudentService;
@EnableAutoConfiguration
@SpringBootApplication
public class StudentLogApplication implements CommandLineRunner {
	
	@Autowired
	private CollegeService collegeService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private StudentService studentService;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentLogApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		

		for (int i = 1; i <= 20; i++) {
		    College college = new College();
		    college.setName("Student " + i);
		    college.setEnrollment("Enrollment" + i); // Using a consistent format for enrollment
		    college.setPhone("Phone " + i);
		    college.setEmail("email" + i + "@gmail.com");
		    
		    collegeService.createCollegeStudent(college);
		}

		for (int i = 1; i <= 10; i++) {
		    Student student = new Student();
		    student.setName("Name" + i);
		    student.setUserName("user" + i);
		    student.setEnrollment("Enrollment" + i); // Using a consistent format for enrollment
		    student.setPassword("Password" + i);
		    student.setBranch("Branch" + i);
		    student.setCurryear("Year" + i);
		    student.setPhone("Phone" + i);
		    student.setEmail("email" + i + "@gmail.com");
		    student.setSemester("Semester" + i);

		    studentService.createStudent(student);
		}

		   
		   for (int i = 1; i <= 20; i++) {
	            Product product = new Product();
	            product.setUserName("user" + i);
	            product.setContactNo("ContactNo" + i);
	            product.setProductName("Product " + i);
	            product.setDescription("Description for Product " + i);
	            product.setPrice("Price " + i);
	            product.setImages(new byte[] {1,2,2});
	            productService.addProduct(product);
	        }		 
		   
		   for (int i = 1; i <= 20; i++) {
	            Product product = new Product();
	            product.setUserName("user" + i);
	            product.setContactNo("ContactNo" + i);
	            product.setProductName("Product " + i);
	            product.setDescription("Description for Product " + i);
	            product.setPrice("Price " + i);
	            product.setImages(new byte[] {});
	            productService.addProduct(product);
	        }	
			 
	}

	
}

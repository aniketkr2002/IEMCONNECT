package com.iemconnect.studentlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iemconnect.studentlog.model.College;
import com.iemconnect.studentlog.model.Product;
import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.service.CollegeService;
import com.iemconnect.studentlog.service.ProductService;
import com.iemconnect.studentlog.service.StudentService;

@SpringBootApplication
public class StudentLogApplication implements CommandLineRunner {
	/* git token == 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 */
//	@Autowired
//	private StudentService studentService; 
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
		
//		   Student s= new Student();s.setName("anshu"); s.setUserName("rajputanshu");
//		   s.setEnrollment("100"); s.setPassword("anshu"); s.setBranch("CSE");
//		   s.setCurryear("3"); s.setPhone("9262919230");
//		   s.setEmail("rajxcbzputaniket@gmail.com");s.setSemester("6");
//		   
		   
		 //  studentService.createStudent(s);
		  /* College c1 = new College(); c1.setName("rohit"); c1.setEnrollment("1");
		   c1.setPhone("111111111");   c1.setEmail("a@gmail.com");
		   
		   College c2 = new College(); c2.setName("ankit"); c2.setEnrollment("2");
		   c2.setPhone("222222222");   c2.setEmail("b@gmail.com");
		   
		   College c3 = new College(); c3.setName("samridh"); c3.setEnrollment("3");
		   c3.setPhone("333333333");   c3.setEmail("c@gmail.com");
		   
		   College c4 = new College(); c4.setName("anshu"); c4.setEnrollment("4");
		   c4.setPhone("4444444444");  c4.setEmail("d@gmail.com");
		   
		   College c5 = new College(); c5.setName("komal"); c5.setEnrollment("5");
		   c5.setPhone("5555555555");  c5.setEmail("e@gmail.com");
		   
		   collegeService.createCollegeStudent(c1);
		   collegeService.createCollegeStudent(c2);
		   collegeService.createCollegeStudent(c3);
		   collegeService.createCollegeStudent(c4);
		   collegeService.createCollegeStudent(c5);*/
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
		    student.setUserName("User" + i);
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
	            //set image data 
	            // product.setImages(images);

	            productService.addProduct(product);
	        }
		   
			 
	}

	
}

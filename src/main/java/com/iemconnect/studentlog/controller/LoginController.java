package com.iemconnect.studentlog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iemconnect.studentlog.model.Product;
import com.iemconnect.studentlog.service.ProductService;



@RestController
@RequestMapping(value = "/product")
public class LoginController {
	@Autowired
	ProductService productService;
	
//	@PostMapping(value = "/addProduct")
//	public ResponseEntity<Object> addProduct(@RequestBody Product product){
//		productService.addProduct(product);
//		return ResponseEntity.ok("saved");
//	}

	    @PostMapping("/addProduct")
	    public ResponseEntity<String> addProduct(
	            @RequestParam("userName") String userName,
	            @RequestParam("contactNo") String contactNo,
	            @RequestParam("productName") String productName,
	            @RequestParam("description") String description,
	            @RequestParam("price") String price,
	            @RequestParam("file") MultipartFile images) {

	    	
	        byte[] imageBytes = null;
	        try {
	            imageBytes = images.getBytes();
	        }catch (IOException e) {
	            return new ResponseEntity<>("Failed to convert image", HttpStatus.INTERNAL_SERVER_ERROR);
	        }


	        
	        Product product = new Product(
	                userName,
	                contactNo,
	                productName,
	                description,
	                price,
	                imageBytes
	        );
	        
	        productService.addProduct(product);
	        return ResponseEntity.ok("saved");
	    }
	
	@GetMapping /// this will call automatically
	public List<Product> getProduct(){
		
		return productService.getAllProduct();
		
	}
	
	@GetMapping(value = "/get/{userName}")
	public List<Product> getMyProduct(@PathVariable("userName") String userName){
		return productService.getMyProduct(userName);
		
	}
	@PutMapping(value="/update/{productid}/{newUpdate}")
	public ResponseEntity<Object> updateMyProduct(@PathVariable("newUpdate") Product newUpdate ,@PathVariable("productId") Integer productId){
		productService.updateMyProduct(newUpdate,productId);
		return ResponseEntity.ok("saved");
	}
	
	@DeleteMapping(value = "/delete/{productid}")
	public ResponseEntity<Object> deleteMyProduct(@PathVariable Integer productid){
		productService.deleteMyProduct(productid);
		return ResponseEntity.ok("saved");
	}
	
}

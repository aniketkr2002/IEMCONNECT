package com.iemconnect.studentlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemconnect.studentlog.model.Product;
import com.iemconnect.studentlog.service.ProductService;



@RestController
@RequestMapping(value = "/product")
public class LoginController {
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/addProduct")
	public ResponseEntity<Object> addProduct(@RequestBody Product product){
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

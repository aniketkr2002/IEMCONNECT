package com.iemconnect.iemconnect.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iemconnect.iemconnect.dao.ProductDao;
import com.iemconnect.iemconnect.model.Product;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	
	public Product addProduct(Product product) {
		return productDao.save(product);
	}
	
	public List<Product> getAllProduct() {
		return   productDao.findAll();
	}



	public List<Product> getMyProduct(String userName) {
		return productDao.findByUserName(userName);
	}
	
	private Product getOneTicket(Integer productid) {
		return productDao.findByProductId(productid);
	}



	public ResponseEntity<Object> updateMyProduct(Product newUpdate ,Integer productId) {
		Product dbProduct = getOneTicket(productId);
		if(dbProduct==null)
		{
			return ResponseEntity.notFound().build();
		}
		dbProduct.setPrice(newUpdate.getPrice());
		dbProduct.setDescription(newUpdate.getDescription());
		dbProduct.setContactNo(newUpdate.getContactNo());
		dbProduct.setImages(newUpdate.getImages());
		dbProduct.setProductName(newUpdate.getProductName());
		productDao.save(dbProduct);
		
		return ResponseEntity.ok("updated");
		
	}

	public void deleteMyProduct(Integer productid) {
		productDao.deleteById(productid);	
	}



	

}

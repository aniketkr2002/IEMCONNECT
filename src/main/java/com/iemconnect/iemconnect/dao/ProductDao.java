package com.iemconnect.iemconnect.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.iemconnect.model.Product;

@Repository
public interface ProductDao extends ListCrudRepository<Product,Integer>{

	List<Product> findByUserName(String userName);

	Product findByProductId(Integer productid);

}

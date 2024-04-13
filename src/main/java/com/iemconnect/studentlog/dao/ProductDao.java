package com.iemconnect.studentlog.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.studentlog.model.Product;

@Repository
public interface ProductDao extends ListCrudRepository<Product,Integer>{

	List<Product> findByUserName(String userName);

	Product findByProductId(Integer productid);

}

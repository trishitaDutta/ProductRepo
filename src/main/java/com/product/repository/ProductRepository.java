package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.model.Product;


public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	
	 @Query(value = "SELECT p FROM product p WHERE p.productname = ?1", nativeQuery = true) List<Product>
	 findByName(String name);
	 
}



package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.model.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {
	
	/*
	 * @Query("SELECT p FROM products p WHERE p.productname = ?1") List<Product>
	 * findByName(String name);
	 */
}



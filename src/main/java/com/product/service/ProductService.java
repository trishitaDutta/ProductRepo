package com.product.service;

import java.util.List;
import java.util.Optional;

import com.product.model.Product;
import com.product.model.ProductRequestDto;
import com.product.model.ProductResponseDto;

public interface ProductService {
	
	Product saveProduct(ProductRequestDto product);
	List<ProductResponseDto> getAllProducts();
    Optional<Product> getProductById(Integer id);
    //List<Product> getProductByName(String name);
    Product updateProduct(Product product);
    String deleteProduct(Integer id);

}




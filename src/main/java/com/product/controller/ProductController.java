package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.model.ProductRequestDto;
import com.product.model.ProductResponseDto;
import com.product.service.ProductServiceImpl;

@RestController
public class ProductController {
	
	@Autowired
	private ProductServiceImpl service;
	
	
	//1. POST - add a new product
	@PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDto product) {
		
			if(product != null) {
				service.saveProduct(product);
				return new ResponseEntity<>(product, HttpStatus.CREATED); 
			}else {
				return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
			}
    }
	
	//2. GET - get all products
	@GetMapping("/getProducts")
    public ResponseEntity<?> getProducts() {
		
		List<ProductResponseDto> products = service.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK); 
		
    }
	
	/*
	 * //GET - get product by Id
	 * 
	 * @GetMapping(value = "/getProductsById/{id}") public ResponseEntity<?>
	 * getProductsById(@PathVariable("id") Long productId) {
	 * 
	 * Optional<Product> product =
	 * Optional.ofNullable(service.getProductById(productId));
	 * 
	 * if(product.isPresent()) { return new ResponseEntity<>(product,
	 * HttpStatus.OK); }else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * }
	 * 
	 * @GetMapping("/getProductByName/{name}") public ResponseEntity<?>
	 * getProductByName(@PathVariable("name") String name) {
	 * 
	 * 
	 * Optional< List<Product>> productList =
	 * Optional.ofNullable(service.getProductByName(name));
	 * 
	 * if(productList.isPresent()) { return new ResponseEntity<>(productList,
	 * HttpStatus.OK); }else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * }
	 * 
	 * @PutMapping("/updateProduct") public ResponseEntity<?>
	 * updateProduct(@RequestBody Product product) {
	 * 
	 * if(product.getId().toString() != null) { service.updateProduct(product);
	 * return new ResponseEntity<>(product, HttpStatus.OK); }else { return new
	 * ResponseEntity<>(product, HttpStatus.NOT_FOUND); } }
	 * 
	 * @DeleteMapping("/deleteProductById/{id}") public ResponseEntity<?>
	 * deleteProductById(@PathVariable("id") Long productId) {
	 * 
	 * if(productId != null) { String result = service.deleteProduct(productId);
	 * return new ResponseEntity<>(result, HttpStatus.OK); }else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */
	

}

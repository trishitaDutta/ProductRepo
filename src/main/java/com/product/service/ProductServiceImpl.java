package com.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.model.Product;
import com.product.model.ProductRequestDto;
import com.product.model.ProductResponseDto;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository repository;
	
	//method to save or add a product into database
	@Override
    public Product saveProduct(ProductRequestDto productRequest) {
		
		Product product = Product.builder()
						  .name(productRequest.getName())
						  .code(productRequest.getCode())
						  .availibility(productRequest.getAvailibility())
						  .price(productRequest.getPrice())
						  .build();
		repository.save(product);
        return product; 
    }

    //method to get all products
    @Override
    public List<ProductResponseDto> getAllProducts() {
    	List<Product> products = repository.findAll();
    	    	return products.stream().map(this::maoToProductResponse).collect(Collectors.toList());
    }

    private ProductResponseDto maoToProductResponse(Product product) {
		return ProductResponseDto.builder()
				.id(product.getId())
				.name(product.getName())
				.code(product.getCode())
				.availibility(product.getAvailibility())
				.price(product.getPrice())
				.build();
    		
    }
    //method to get a product by its Id
    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

  //method to get a product by its name
	/*
	 * @Override public List<Product> getProductByName(String name) { return
	 * repository.findByName(name); }
	 */
    
  //method to delete a product by its Id
    @Override
    public String deleteProduct(Long id) {
    	
    	repository.deleteById(id);
        return "product removed !! " + id;
    }
    
    //method to update an existing product
	/*
	 * @Override public Product updateProduct(Product product) {
	 * 
	 * Product existingProduct = repository.findById(product.getId()).orElse(null);
	 * existingProduct.setAvailibility(product.getAvailibility());
	 * existingProduct.setName(product.getName());
	 * existingProduct.setCode(product.getCode());
	 * existingProduct.setPrice(product.getPrice());
	 * 
	 * return repository.save(existingProduct);
	 * 
	 * }
	 */

}
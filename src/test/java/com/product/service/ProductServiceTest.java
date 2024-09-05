package com.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.model.ProductRequestDto;
import com.product.model.ProductResponseDto;
import com.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	// Creating a Mock to mimic productRepository.
    @Mock
    private ProductRepository productRepository;

    // An instance of ProductServiceImpl, injected with the Mock created with @Mock.
    @InjectMocks
    private ProductServiceImpl productService;

    
    
    // A test method to test the save method of the ProductServiceImpl class.
    @Test
    public void productService_saveProduct_returnSavedProduct(){
    	
        // Creating an instance of Product to be used for the test.
        Product product = new Product();

        // Creating an instance of EmployeeDto to be used for the test.
        ProductRequestDto productDto = ProductRequestDto.builder()
                .name("Iphone13")
                .availibility(true)
                .code("iphn13")
                .price("32000")
                .build();

        // Using BeanUtils.copyProperties to copy properties from ProductRequestDto to Product for the test.
        BeanUtils.copyProperties(productDto, product);

        // Mocking the productRepository.save method to return this instance of Product whenever any instance of Product is passed.
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Calling the create method of ProductService and checking if it returns the expected instance of ProductDto.
        Product savedProduct = productService.saveProduct(productDto);

        // Using AssertJ to check the correctness of the expected values.
        assertThat(savedProduct).isNotNull();
    }
    
    @Test
    public void productService_getProductById_WhenProductExists_returnProductOptional() {
        
    	// Arrange
        Product product =Product.builder()						  
				  .availibility(true)
				  .code("iphn0013")
				  .name("Iphone13")
				  .price("50000")
				  .build();
		
		productRepository.save(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // Act
        Optional<Product> productFound = productService.getProductById(product.getId());

        // Assert
        assertTrue(productFound.isPresent()); // assert that the result is present
        assertSame(product, productFound.get()); // assert that the result is the same instance as the product
    }
    
    
    @Test
    public void checkProductExists(){
       
     	Integer productId = 2;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Assert
        assertThrows(ProductNotFoundException.class,
				() -> productService.getProductById(productId));
        
    }

    @Test
    public void productService_getAllProducts_returnAllProducts() {
    	
    	Product product1 = Product.builder()						  
				  .availibility(true)
				  .code("iphn0015")
				  .name("Iphone15")
				  .price("100000")
				  .build();
		
    	Product product2 = Product.builder()						  
				  .availibility(true)
				  .code("iphn0013")
				  .name("Iphone13")
				  .price("50000")
				  .build();
    	
    	productRepository.saveAll(List.of(product1,product2));
    	
    	when(productRepository.findAll()).thenReturn(List.of(product1,product2));

        List<ProductResponseDto> expectedListOfProducts = productService.getAllProducts();

        assertThat(expectedListOfProducts).isNotNull();
        assertFalse(expectedListOfProducts.isEmpty());
        assertThat(expectedListOfProducts.size()).isEqualTo(2);
        verify(productRepository).findAll();
    	
    }
    
}

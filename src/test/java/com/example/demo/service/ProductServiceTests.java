package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.product.model.Product;
import com.product.model.ProductRequestDto;
import com.product.repository.ProductRepository;
import com.product.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
	
	// Creating a Mock to mimic EmployeeRepository.
    @Mock
    private ProductRepository productRepository;

    // An instance of ProductServiceImpl, injected with the Mock created with @Mock.
    @InjectMocks
    private ProductServiceImpl productService;

    // A test method to test the create method of the ProductServiceImpl class.
    @Test
    public void createProduct(){
    	
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
        Assertions.assertThat(savedProduct).isNotNull();
    }
    
    
    @Test
    public void checkProductExists(){
        // Arrange
        Long productId = 2L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        Optional<Product> result = Optional.ofNullable(productService.getProductById(productId));

        // Assert
        assertFalse(result.isPresent()); // assert that the result is not present
    }

    
    
}

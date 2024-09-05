package com.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.product.model.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {
	
	@Autowired
    private ProductRepository repository;
		
	@Test
	public void productRepository_save_Savedproduct() {
		
		//Arrange
		Product product = Product.builder()						  
						  .availibility(true)
						  .code("laptop001")
						  .name("laptop")
						  .price("60000")
						  .build();
		
		//Act
		Product savedProduct = repository.save(product);

		//Assert
		assertNotNull(savedProduct);
		 Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void productRepository_findById_findProduct() {
		
		Product product = Product.builder()						  
				  .availibility(true)
				  .code("iphn0014")
				  .name("Iphone14")
				  .price("100000")
				  .build();

		repository.save(product);
		Product fetchedProduct = repository.findById(product.getId()).orElse(null);
        assertNotNull(fetchedProduct);
        Assertions.assertThat(product.getId()).isGreaterThan(0);
		
	}
	
	
	@Test
	public void productRepository_findAll_findAllProduct() {
		
		repository.save(Product.builder()						  
				  .availibility(true)
				  .code("iphn0015")
				  .name("Iphone15")
				  .price("100000")
				  .build());
		
		repository.save(Product.builder()						  
				  .availibility(true)
				  .code("iphn0013")
				  .name("Iphone13")
				  .price("50000")
				  .build());
		
		List<Product> products = repository.findAll();
        assertNotNull(products);
        assertEquals(2, products.size());
	}
	
	

	@Test
    public void productRepository_update_updateProduct() {
        
		Product product =Product.builder()						  
				  .availibility(true)
				  .code("iphn0013")
				  .name("Iphone13")
				  .price("50000")
				  .build();
		
		repository.save(product);
		product.setName("samsung");
        repository.save(product);
        Product updatedProduct = repository.findById(product.getId()).orElse(null);
        assertNotNull(updatedProduct);
        assertEquals("samsung", updatedProduct.getName());
    }

	
    @Test
    public void productRepository_delete_deleteProduct() {
        
    	Product product =Product.builder()						  
				  .availibility(true)
				  .code("iphn0013")
				  .name("Iphone13")
				  .price("50000")
				  .build();
		
		repository.save(product);
		repository.deleteById(product.getId());
        Product deletedProduct = repository.findById(product.getId()).orElse(null);
        assertNull(deletedProduct);
    }
}

package com.product.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
		name = "product",
		schema = "ecommerce",
		uniqueConstraints = {
				@UniqueConstraint(columnNames={"productcode"})
})
public class Product {

    @Id
    @GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
    		generator = "product_generator"
    )
    @SequenceGenerator(
    		name = "product_generator",
    		sequenceName = "product_sequence",
    		schema = "ecommerce"
    )
    @Column(name = "productid")
    private Integer id;
    
    @Column(nullable = false)
    private Boolean availibility;
    
    @Column(name = "productcode", nullable = false)
    private String code;
        
    @Column(name = "productname", nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String price;
    
   
}





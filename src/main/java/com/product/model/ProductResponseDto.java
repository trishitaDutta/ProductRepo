package com.product.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

	private Integer id;
	private Boolean availibility;
	private String code;
    private String name;
    private String price;
}

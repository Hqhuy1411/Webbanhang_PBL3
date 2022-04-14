package com.DemoSpring.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.Entity.Product;
import com.DemoSpring.Repository.ProductResponsitory;

@Component
public class ConvertProduct {
	@Autowired
	ProductResponsitory productResponsitory;
	
	public ProductDTO toDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setCode(product.getCode());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setImage(product.getImage());
		dto.setPrice(product.getPrice());
		dto.setCategoryName(product.getCategory().getName());
		return dto;
	}
	
	public List<ProductDTO> tolistDto(List<Product> list){
		List<ProductDTO> listDTO = new ArrayList<ProductDTO>();
		for(Product product : list) {
			listDTO.add(toDTO(product));
		}
		return listDTO;
	}
	public Product toEntity(ProductDTO dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setCode(dto.getCode());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setImage(dto.getImage());
		product.setPrice(dto.getPrice());
		//dto.setCategoryName(product.getCategory().getName());
		return product;
	}
}

package com.DemoSpring.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.Entity.Product;

public interface ProductService {
//	List<ProductDTO> findAll();
	
	Page<Product> findAll(String text,String catelogy,int indexPage);
	
	List<ProductDTO> findAll(String username,String catelogy,String text);
	
	List<ProductDTO> findAll(List<Product> products);
	
	List<String> getAllName();
	
	void Delete(int id);

	void Save(ProductDTO dto);

	ProductDTO getDto(int id);
}

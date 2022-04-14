package com.DemoSpring.Service;

import java.util.List;

import com.DemoSpring.DTO.ProductDTO;

public interface ProductService {
	List<ProductDTO> findAll();

	void Delete(int id);

	void Save(ProductDTO dto);

	ProductDTO getDto(int id);
}

package com.DemoSpring.Service;

import java.util.List;

import com.DemoSpring.DTO.CategoryDTO;

public interface CategoryService {
	List<CategoryDTO> findAll();
	
	void Delete(int id);
	
	void Save(CategoryDTO dto);
	
	CategoryDTO getDto(int id);
}

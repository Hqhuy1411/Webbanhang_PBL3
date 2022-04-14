package com.DemoSpring.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.DemoSpring.DTO.CategoryDTO;
import com.DemoSpring.Entity.Category;

@Component
public class ConvertCategory {
	public CategoryDTO ToDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());
		return dto;
	}
	public List<CategoryDTO> toListDTO(List<Category> categories){
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			list.add(ToDTO(category));
		}
		return list;
	}
	public Category toEntity(CategoryDTO dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		return category;
	}
}

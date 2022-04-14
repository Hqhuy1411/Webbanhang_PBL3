package com.DemoSpring.Service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpring.Convert.ConvertCategory;
import com.DemoSpring.DTO.CategoryDTO;
import com.DemoSpring.Entity.Category;
import com.DemoSpring.Repository.CategoryResponsitory;
import com.DemoSpring.Service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	CategoryResponsitory categoryResponsitory;
	@Autowired
	ConvertCategory convertCategory;
	@Override
	public List<CategoryDTO> findAll() {
		// TODO Auto-generated method stub
		List<CategoryDTO> list = convertCategory.toListDTO(categoryResponsitory.findAll());
		return list;
	}
	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		Category category = categoryResponsitory.findById(id).get();
		categoryResponsitory.delete(category);
	}
	@Override
	public void Save(CategoryDTO dto) {
		// TODO Auto-generated method stub
		categoryResponsitory.save(convertCategory.toEntity(dto));
	}
	@Override
	public CategoryDTO getDto(int id) {
		// TODO Auto-generated method stub
		Category category = categoryResponsitory.findById(id).get();
		CategoryDTO dto = convertCategory.ToDTO(category);
		
		return dto;
	}
	

}

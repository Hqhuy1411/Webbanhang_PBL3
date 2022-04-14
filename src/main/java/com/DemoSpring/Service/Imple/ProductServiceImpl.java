package com.DemoSpring.Service.Imple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpring.Convert.ConvertProduct;
import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.Entity.Product;
import com.DemoSpring.Repository.ProductResponsitory;
import com.DemoSpring.Service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductResponsitory productResponsitory;
	@Autowired
	ConvertProduct convertProduct;
	
	@Override
	public List<ProductDTO> findAll() {
		// TODO Auto-generated method stub
		List<Product> list = productResponsitory.findAll();
		return convertProduct.tolistDto(list);
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Save(ProductDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductDTO getDto(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

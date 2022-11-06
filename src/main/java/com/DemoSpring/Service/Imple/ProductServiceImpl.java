package com.DemoSpring.Service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DemoSpring.Convert.ConvertProduct;
import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.Entity.Product;
import com.DemoSpring.Repository.CategoryResponsitory;
import com.DemoSpring.Repository.PagingAndSorting;
import com.DemoSpring.Repository.ProductResponsitory;
import com.DemoSpring.Security.UserCrudRespon;
import com.DemoSpring.Service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductResponsitory productResponsitory;
	@Autowired
	ConvertProduct convertProduct;
	@Autowired
	CategoryResponsitory categoryResponsitory;
	@Autowired
	PagingAndSorting pagingAndSorting;
	@Autowired
	UserCrudRespon userCrudRespon;
//	@Override
//	public List<ProductDTO> findAll() {
//		// TODO Auto-generated method stub
//		List<Product> list = productResponsitory.findAll();
//		return convertProduct.tolistDto(list);
//	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		Product product = productResponsitory.findById(id).get();
		productResponsitory.delete(product);
	}

	@Override
	public void Save(ProductDTO dto) {
		// TODO Auto-generated method stub
		Product  entity = convertProduct.toEntity(dto);
		productResponsitory.save(entity);
	}

	@Override
	public ProductDTO getDto(int id) {
		// TODO Auto-generated method stub
		return convertProduct.toDTO(productResponsitory.findById(id).get());
	}

	@Override
	public List<String> getAllName() {
		// TODO Auto-generated method stub
		return categoryResponsitory.findAllName();
	}

	@Override
	public List<ProductDTO> findAll(List<Product> products) {
		// TODO Auto-generated method stub
		return convertProduct.tolistDto(products);
	}

	@Override
	public Page<Product> findAll(String text,String catelogy,int indexPage) {
		// TODO Auto-generated method stub
		System.out.println(catelogy);
		Pageable pageable = PageRequest.of(indexPage -1, 4);
		if(text !="" && catelogy=="") {
			return pagingAndSorting.findCharacter(text ,pageable);
		}
		if(text =="" && catelogy!="") {
			return pagingAndSorting.findCategory(catelogy ,pageable);
		}
		if(text !=null && catelogy!=null) {
			return pagingAndSorting.findCharacter(text,catelogy ,pageable);
		}
		return pagingAndSorting.findAll(pageable);
	}

	@Override
	public List<ProductDTO> findAll(String username, String catelogy,String text) {
		// TODO Auto-generated method stub
		if(text !="" && catelogy=="") {
			return convertProduct.tolistDto(productResponsitory.findAllbySeller2(username, text));
		}
		if(text =="" && catelogy!="") {
			return convertProduct.tolistDto(productResponsitory.findAllbySeller(username, catelogy));
		}
		if(text !=null && catelogy!=null) {
			return convertProduct.tolistDto(productResponsitory.findCharacter(username, text, catelogy));
		}
		return convertProduct.tolistDto(userCrudRespon.getAllProduct(username));
	}

}

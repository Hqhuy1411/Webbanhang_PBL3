package com.DemoSpring.Service;

import java.util.List;

import com.DemoSpring.DTO.CartItemDTO;

public interface CartItemService {
	List<CartItemDTO> findAll(int id,String theloai);
	
	List<CartItemDTO> findAllByCustomer(int id);
	
	void Delete(int id);
	
	void Save(CartItemDTO dto);
	
	CartItemDTO getDto(int id);
}

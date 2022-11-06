package com.DemoSpring.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DemoSpring.DTO.CartItemDTO;
import com.DemoSpring.Entity.CartItem;
import com.DemoSpring.Entity.Product;
import com.DemoSpring.Entity.User;
import com.DemoSpring.Repository.ProductResponsitory;
import com.DemoSpring.Security.UserCrudRespon;
@Component
public class ConvertCartItem {
	@Autowired
	ProductResponsitory productResponsitory;
	@Autowired
	UserCrudRespon userCrudRespon;
	
	public CartItemDTO ToDTO(CartItem category) {
		CartItemDTO dto = new CartItemDTO();
		dto.setId(category.getId());
		dto.setQuatity(category.getQuantily());
		dto.setProduct_id(category.getProduct().getId());
		dto.setUser_id(category.getUser().getId());
		return dto;
	}
	public List<CartItemDTO> toListDTO(List<CartItem> categories){
		List<CartItemDTO> list = new ArrayList<CartItemDTO>();
		for (CartItem category : categories) {
			list.add(ToDTO(category));
		}
		return list;
	}
	public CartItem toEntity(CartItemDTO dto) {
		CartItem cartItem = new CartItem();
		Product product = productResponsitory.findById(dto.getProduct_id()).get();
		User user = userCrudRespon.findById(dto.getUser_id()).get();
		cartItem.setProduct(product);
		cartItem.setUser(user);
		cartItem.setQuantily(dto.getQuatity());
//		category.setId(dto.getId());
//		category.setName(dto.getName());
//		category.setDescription(dto.getDescription());
		return cartItem;
		}
}

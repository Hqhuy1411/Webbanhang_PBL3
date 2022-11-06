package com.DemoSpring.Service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpring.Convert.ConvertCartItem;
import com.DemoSpring.DTO.CartItemDTO;
import com.DemoSpring.Repository.CartItems;
import com.DemoSpring.Service.CartItemService;

@Service
public class CartItemsServiceImple implements CartItemService {
	@Autowired
	ConvertCartItem convertCartItem;
	@Autowired
	CartItems cartItems;
	


	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		cartItems.deletebyUserandProduct(id);
	}

	@Override
	public void Save(CartItemDTO dto) {
		// TODO Auto-generated method stub
		cartItems.save(convertCartItem.toEntity(dto));
	}

	@Override
	public CartItemDTO getDto(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartItemDTO> findAll(int id, String theloai) {
		// TODO Auto-generated method stub
		if(theloai=="") {
			return convertCartItem.toListDTO(cartItems.getCartItems(id));
		}else {
			return convertCartItem.toListDTO(cartItems.getCartItemsByCategory(id,theloai));
		}
	}

	@Override
	public List<CartItemDTO> findAllByCustomer(int id) {
		// TODO Auto-generated method stub
		return convertCartItem.toListDTO(cartItems.getCartItemsByCustomer(id));
	}
	
}

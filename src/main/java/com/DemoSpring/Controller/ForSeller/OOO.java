package com.DemoSpring.Controller.ForSeller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DemoSpring.DTO.CartItemDTO;
import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Repository.CartItems;
import com.DemoSpring.Security.UserCrudRespon;
import com.DemoSpring.Service.CartItemService;
import com.DemoSpring.Service.ProductService;
import com.DemoSpring.Service.UserService;

@RestController
@RequestMapping("/seller")
public class OOO {
	@Autowired
	UserService userService;
	
	@Autowired
	UserCrudRespon userCrudRespon;
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	CartItems cartItems;

	@GetMapping("/doanh-thu")
	public String Get(Model model,Authentication authentication,String text) {
		List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
		text ="Tablet";
		List<UserDTO> listUser = new ArrayList<UserDTO>();
		List<Integer> listQuantity = new ArrayList<Integer>();
		int id = userCrudRespon.getUserbyUsername(authentication.getName()).getId();
		for (CartItemDTO cartItem : cartItemService.findAll(id,text)) {
			int g = cartItem.getProduct_id();
			listProduct.add(productService.getDto(g));
			listQuantity.add(cartItem.getQuatity());
			listUser.add(userService.getDto(cartItem.getUser_id()));
		}
		for (ProductDTO productDTO : listProduct) {
			System.out.println(productDTO.getId() +"");
		}
		for (Integer in : listQuantity) {
			System.out.println(in +"");
		}
		for (UserDTO in : listUser) {
			System.out.println(in.getFullname() +"");
		}
//		List<CartItem> list = cartItems.getCartItems(userCrudRespon.getUserbyUsername(authentication.getName()).getId());
//		for(CartItem cartItem : list) {
//			System.out.println(cartItem.getQuantily());
//		}
		return "cc";
	}
}

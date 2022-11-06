package com.DemoSpring.Controller.ForCustomer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DemoSpring.DTO.CartItemDTO;
import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Repository.CartItems;
import com.DemoSpring.Security.UserCrudRespon;
import com.DemoSpring.Service.CartItemService;
import com.DemoSpring.Service.ProductService;
import com.DemoSpring.Service.UserService;
// Add delete update cartitems
@Controller
@RequestMapping("/customer")
public class MyItems {
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
	
	@GetMapping("")
	public String MyItems(Model model,Authentication authentication) {
		List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
		List<Integer> listQuantity = new ArrayList<Integer>();
		List<Integer> listID = new ArrayList<Integer>();
		int id = userCrudRespon.getUserbyUsername(authentication.getName()).getId();
		for (CartItemDTO cartItem : cartItemService.findAllByCustomer(id)) {
			int g = cartItem.getProduct_id();
			listProduct.add(productService.getDto(g));
			listQuantity.add(cartItem.getQuatity());
			listID.add(cartItem.getId());
		}
		model.addAttribute("list", listProduct);
		model.addAttribute("quantilys", listQuantity);
		model.addAttribute("listID", listID);
//		for (ProductDTO productDTO : listProduct) {
//			System.out.println(productDTO.getId() +"");
//		}
//		for (Integer in : listQuantity) {
//			System.out.println(in +"");
//		}
//		for (ProductDTO productDTO : listProduct) {
//			System.out.println(productDTO.getId() +"");
//		}
//		for (Integer in : listQuantity) {
//			System.out.println(in +"");
//		}
		return "view-user";
	}
	@GetMapping("/add")
	public String Add(Authentication authentication,@RequestParam("quantity") int quantity,@RequestParam("id") int id) {
		System.out.println();
		int idUser = userCrudRespon.getUserbyUsername(authentication.getName()).getId();
		System.out.println(idUser +" " + quantity +" " + id);
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setProduct_id(id);
		cartItemDTO.setQuatity(quantity);
		cartItemDTO.setUser_id(idUser);
		cartItemService.Save(cartItemDTO);
		return "redirect:/customer";
	}
	@GetMapping("/delete/{_id}")
	public String Delete(@PathVariable("_id") int product ,Authentication authentication) {
	//	int idUser = userCrudRespon.getUserbyUsername(authentication.getName()).getId();
		cartItemService.Delete(product);
		return "redirect:/customer";
	}
	@GetMapping("/update/{_id}")
	public String Update(@PathVariable("_id") int product ) {
		//	int idUser = userCrudRespon.getUserbyUsername(authentication.getName()).getId();
			
			return "redirect:/customer";
		}
	@PostMapping("/confirm")
	public String confirm(Authentication authentication,Model model) {
		UserDTO dto = userService.getDto(authentication.getName());
		if(dto.getAddress() =="" || dto.getPhone() =="") {
			model.addAttribute("messages", "Vui long dien day du thong tin");
			model.addAttribute("dto", dto);
			return "profile";
		}
		model.addAttribute("messages", "Dang duoc xac nhan");
		// purchase cho xac nhan
		return "purchase";
	}
	
	
}

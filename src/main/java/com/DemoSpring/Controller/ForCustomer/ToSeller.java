package com.DemoSpring.Controller.ForCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Service.UserService;

@Controller
public class ToSeller {
	@Autowired
	UserService userService;
	@GetMapping("/customer_seller")
	public RedirectView  toSeller(Model model,Authentication authentication,RedirectAttributes attributes) {
		if(authentication ==null) {
			attributes.addFlashAttribute("message", "Vui long dang nhap");
			return new RedirectView("login");
		}
		UserDTO dto = userService.getDto(authentication.getName());
		if(dto.getPhone() ==null || dto.getAddress()==null) {
			attributes.addFlashAttribute("message", "Nhap day du thong tin");
			return new RedirectView("user/profile");
		}
		System.out.println(dto.toString());
		userService.AddRole(dto);
		return new RedirectView("/seller/add");
		
	}
}

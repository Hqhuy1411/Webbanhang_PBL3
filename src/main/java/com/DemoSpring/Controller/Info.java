package com.DemoSpring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Service.CityService;
import com.DemoSpring.Service.UserService;
import com.DemoSpring.Validator.PasswWordValidator;

@Controller
public class Info {
	@Autowired
	PasswWordValidator passwWordValidator;
	@Autowired
	UserService userService;
	@Autowired
	CityService cityService;

	@GetMapping("/user/profile")
	public String user(Authentication authentication, Model model) {
		model.addAttribute("citys", cityService.findAll());
		UserDTO dto = userService.getDto(authentication.getName());
		model.addAttribute("dto", dto);
		System.out.println(dto.getUsername());
		return "profile";
	}

	@PostMapping("/update/profile")
	public String update(@ModelAttribute("dto") UserDTO dto, Model model) {
		model.addAttribute("citys", cityService.findAll());
		System.out.println(dto.getPhone());
		System.out.println(dto.isGender());
		userService.Save(dto);
		return "profile";
	}

	@GetMapping("/user/password")
	public String password(Authentication authentication, Model model) {

		return "password";
	}

	@PostMapping("/user/change_password")
	public RedirectView dopassword(Authentication authentication,HttpServletRequest request,RedirectAttributes redirectAttrs) {
		RedirectView redirectView = new RedirectView();
		UserDTO userDTO = userService.getDto(authentication.getName());
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		if (!userService.check(oldPassword, userDTO.getPassword())) {
		//	model.addAttribute("message", "Your old password is incorrect.");
			redirectAttrs.addFlashAttribute("message", "Your old password is incorrect.");
			redirectView.setContextRelative(true);
			redirectView.setUrl("/user/password");
		    return redirectView;
		}
		if(!PasswWordValidator.isValid(newPassword)) {
			redirectAttrs.addFlashAttribute("message", "ABC");
			redirectView.setContextRelative(true);
			redirectView.setUrl("/user/password");
		    return redirectView;
		}
		userService.ChangePassword (userDTO.getUsername(),newPassword);
		redirectAttrs.addFlashAttribute("message", "mk dang dc doi");
		redirectView.setContextRelative(true);
		redirectView.setUrl("/login");
	    return redirectView;
	}
}

package com.DemoSpring.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.DemoSpring.Convert.ConvertUser;
import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Service.CityService;
import com.DemoSpring.Service.UserService;
import com.DemoSpring.Validator.RegisterValidator;

@Controller
public class HelloController {
	@Autowired
	RegisterValidator registerValidator;
	@Autowired
	UserService userService;
	@Autowired
	ConvertUser convertUser;
	@Autowired
	CityService cityService;
//	@GetMapping("/")
//	public String hello() {
//		
//		return "index";
//	}s
//	@GetMapping("/admin")
//	public String admin() {
//		
//		return "index";
//	}
//	@GetMapping("/user")
//	public String user() {
//		
//		return "user";
//	}
//	@GetMapping("/editor")
//	public String editor() {
//		
//		return "index";
//	}
	@GetMapping("/login")
	public  String login(Model model) {
		System.out.print("vo login");
		return "login";
	}
	@GetMapping("/resetpass")
	public  String reset(Model model) {
		System.out.print("vo login");
		return "resetpass";
	}
	@PostMapping("/resetpass")
	public String resString(HttpServletRequest request,Model model) {
		String name_reset = request.getParameter("name_reset");
		UserDTO dto = userService.getDtoByPhone_Email(name_reset);
		if(dto!=null) {
			model.addAttribute("message", "Password was reseted");
			dto.setPassword("admin");
			userService.Save(dto);
			return "login";
		}
		model.addAttribute("message", "cc");
		return"resetpass";
	}
	@GetMapping("/register")
	public  String Register(Model model) {
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		model.addAttribute("list", list);
		UserDTO dtpDto = new UserDTO();
		model.addAttribute("user", dtpDto);
		model.addAttribute("citys", cityService.findAll());
		return "register";
	}
	@PostMapping("/register")
	public  String Register_Post(@ModelAttribute("user") @Valid  UserDTO dto,Model model,BindingResult bindingResult) {
		System.out.println(dto.toString());
		registerValidator.validate(dto, bindingResult);
		if(bindingResult.hasErrors()) {
			System.out.println("Loi loi lon");
			return "register";
		}
		
		model.addAttribute("mess", "Ban da dang ki thanh cong");
		userService.Save(dto);
		return "redirect:/product";
	}
//	
}

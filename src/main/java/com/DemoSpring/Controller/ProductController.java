package com.DemoSpring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DemoSpring.Service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("list", productService.findAll());
		return "product/list";
	}
}

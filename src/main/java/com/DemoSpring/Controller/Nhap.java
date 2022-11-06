package com.DemoSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DemoSpring.Entity.Product;
import com.DemoSpring.Repository.ProductResponsitory;

@RestController
public class Nhap {
	@Autowired
	ProductResponsitory productResponsitory;
	
	@GetMapping("cc")
	public String cc() {
		List<Product> list = productResponsitory.findAllbySeller("user2","Tablet");
		for (Product product : list) {
			System.out.println(product.getId());
		}
		return "cc";
	}
}

package com.DemoSpring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
	public String hello() {
		
		return "index";
	}
	@GetMapping("/admin")
	public String admin() {
		
		return "admin";
	}
	@GetMapping("/user")
	public String user() {
		
		return "user";
	}
	@GetMapping("/editor")
	public String editor() {
		
		return "editor";
	}
}

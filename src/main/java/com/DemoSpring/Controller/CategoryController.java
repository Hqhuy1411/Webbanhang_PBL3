package com.DemoSpring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DemoSpring.DTO.CategoryDTO;
import com.DemoSpring.Service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("list", categoryService.findAll());
		return "category/list";
	}
	@GetMapping("/add")
	public String add() {
		return "category/add";
	}
	@PostMapping("/add")
	public String postadd(@ModelAttribute("category") CategoryDTO dto) {
		categoryService.Save(dto);
		return "redirect:/category";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int id,Model model) {
		CategoryDTO dto = categoryService.getDto(id);
		model.addAttribute("category", dto);
		return "category/update";
	}
	@PostMapping("/update")
	public String postupdate(@ModelAttribute("category") CategoryDTO dto) {
		categoryService.Save(dto);
		return "redirect:/category";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		categoryService.Delete(id);
		return "redirect:/category";

	}
}

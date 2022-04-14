package com.DemoSpring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DemoSpring.DTO.CityDTO;
import com.DemoSpring.Service.CityService;

@Controller
@RequestMapping("/city")
public class CityController {
	@Autowired
	CityService cityService;
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("list", cityService.findAll());
		return "city/list";
	}
	@GetMapping("/add")
	public String add() {
		return "city/add";
	}
	@PostMapping("/add")
	public String postadd(@ModelAttribute("city") CityDTO dto) {
		cityService.Save(dto);
		return "redirect:/city";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int id,Model model) {
		CityDTO dto = cityService.getDto(id);
		model.addAttribute("city", dto);
		return "city/update";
	}
	@PostMapping("/update")
	public String postupdate(@ModelAttribute("city") CityDTO dto) {
		cityService.Save(dto);
		return "redirect:/city";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		cityService.Delete(id);
		return "redirect:/city";

	}
}

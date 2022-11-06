package com.DemoSpring.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DemoSpring.DTO.ProductDTO;
import com.DemoSpring.Entity.Product;
import com.DemoSpring.Service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("")
	public String getAll(Model model, @RequestParam(name = "search", value = "", required = false) String keyword,
			@RequestParam(name = "CBBcategory", value = "", required = false) String category ) {
		Page<Product> page = productService.findAll(keyword, category,1);
		List<ProductDTO> list = productService.findAll(page.getContent());
		// int totalItems = page.getof..
		int currentPage = 1;
		int totalItems = (int) page.getTotalElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("categorys", productService.getAllName());
		System.out.println(category);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("list", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", category);
		return "product/list";
	}

	@GetMapping("/page/{indexPage}")
	public String page(Model model, @PathVariable("indexPage") int currentPage,
			@RequestParam(value = "search", required = false) String keyword,
			@RequestParam(name = "CBBcategory", value = "", required = false) String category) {
		Page<Product> page = productService.findAll(keyword, category,currentPage);
		List<ProductDTO> list = productService.findAll(page.getContent());
		// int totalItems = page.getof..
		int totalItems = (int) page.getTotalElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("categorys", productService.getAllName());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("list", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", category);
		
		return "product/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", productService.getAllName());
		return "product/add";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("product") ProductDTO dto, @ModelAttribute("file") MultipartFile file,
			Authentication authentication) throws IOException {
		String username = authentication.getName();
		System.out.println(username);
		String fileName = file.getOriginalFilename();
		dto.setImage(fileName);
		productService.Save(dto);
		String uploadDir = "/images";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		// Nên dùng try catch . Vì để bắt khi ta không truyền file vào -> file bằng
		// nulll
		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			// throw new IOException("Could not save upload file " + fileName);

		}
		return "redirect:/product";
	}

	@GetMapping("update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		ProductDTO dto = productService.getDto(id);
		model.addAttribute("product", dto);
		model.addAttribute("category", productService.getAllName());
		return "product/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("product") ProductDTO dto, @ModelAttribute("file") MultipartFile file)
			throws IOException {
		String fileName = file.getOriginalFilename();
		if (fileName != null) {
			dto.setImage(fileName);
		}
		productService.Save(dto);
		String uploadDir = "./images";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {

		}
		return "redirect:/product";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		productService.Delete(id);
		return "redirect:/product";

	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") int id, Model model) {
		ProductDTO dto = productService.getDto(id);
		model.addAttribute("product", dto);
		return "product/view";

	}
}

package com.DemoSpring.Controller.ForSeller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.DemoSpring.Security.UserCrudRespon;
import com.DemoSpring.Service.ProductService;
// Không thể xóa sản phẩn khi có một người khac đang có trong items vì đây là khóa ngoại.
@Controller
@RequestMapping("/seller")
public class Products {
	@Autowired
	UserCrudRespon userCrudRespon;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/all")
	public String All(Model model,Authentication authentication, @RequestParam(name = "search", value = "", required = false) String keyword,
			@RequestParam(name = "CBBcategory", value = "", required = false) String category ) {
		//model.addAttribute("list",productService.findAll(userCrudRespon.getAllProduct(authentication.getName())));
		model.addAttribute("list", productService.findAll(authentication.getName(), category, keyword));
		model.addAttribute("categorys", productService.getAllName());

		return "product/list2";
	}
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", productService.getAllName());
		return "product/add";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("product") ProductDTO dto, @ModelAttribute("file") MultipartFile file,Authentication authentication) throws IOException {
		String username = authentication.getName();
		dto.setCreateBy(userCrudRespon.getUserbyUsername(username).getId());
		String fileName = file.getOriginalFilename();
		dto.setImage(fileName);
		productService.Save(dto);
		String uploadDir = "./images";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		// Nên dùng try catch . Vì để bắt khi ta không truyền file vào -> file bằng nulll
		try(InputStream inputStream = file.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
		}catch (Exception e) {
			// TODO: handle exception
			//throw new IOException("Could not save upload file " + fileName);
		
		}
		return "redirect:/seller/all";
	}
	@GetMapping("update/{id}")
	public String update(@PathVariable("id") int id,Model model) {
		ProductDTO dto = productService.getDto(id);
		model.addAttribute("product", dto);
		model.addAttribute("category", productService.getAllName());
		return "product/update";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute("product") ProductDTO dto, @ModelAttribute("file") MultipartFile file,Authentication authentication) throws IOException {
		String username = authentication.getName();
		dto.setCreateBy(userCrudRespon.getUserbyUsername(username).getId());
		String fileName = file.getOriginalFilename();
		if(fileName!=null) {
			dto.setImage(fileName);
		}
		productService.Save(dto);
		String uploadDir = "./images";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream = file.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
		}catch (Exception e) {
			
		
		}
		return "redirect:/seller/all";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		productService.Delete(id);
		return "redirect:/seller/all";

	}
}

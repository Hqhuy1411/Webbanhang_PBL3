package com.DemoSpring.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Entity.User;
import com.DemoSpring.Repository.CityResponsitory;
import com.DemoSpring.Service.RoleService;
@Component
public class ConvertUser {
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	CityResponsitory cityResponsitory;
	@Autowired
	RoleService roleService;
	public UserDTO ToDTO(User city) {
		UserDTO dto = new UserDTO();
		dto.setId(city.getId());
		dto.setUsername(city.getUsername());
		dto.setPassword(city.getPassword());
		dto.setBirthday(city.getBirthday());
		dto.setEmail(city.getEmail());
		dto.setAddress(city.getAddress());
		dto.setFullname(city.getFullname());
		dto.setGender(city.isGender());
		dto.setPhone(city.getPhone());
		dto.setId_city(city.getCity().getId());
		return dto;
	}
	public List<UserDTO> toListDTO(List<User> cities){
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (User city : cities) {
			list.add(ToDTO(city));
		}
		return list;
	}
	public User toEntity(UserDTO city) {
		User dto = new User();
	//	dto.setId(city.getId());
		dto.setUsername(city.getUsername());
		String passwordString = passwordEncoder.encode(city.getPassword());
		dto.setPassword(passwordString);
//		dto.setBirthday(city.getBirthday());
//		dto.setEmail(city.getEmail());
//		dto.setAddress(city.getAddress());
		dto.setFullname(city.getFullname());
//		dto.setGender(city.isEnabled());
//		dto.setPhone(city.getPhone());
//		dto.setId_city(city.getCity().getId());

		return dto;
	}
	public User toEntity2(UserDTO city) {
		User dto = new User();
		dto.setId(city.getId());
		dto.setUsername(city.getUsername());
		if(city.getPassword().length() > 20) {
			dto.setPassword(city.getPassword());
		}else {
			String passwordString = passwordEncoder.encode(city.getPassword());
			dto.setPassword(passwordString);
			dto.setRole(roleService.getId(3));
		}
		dto.setBirthday(city.getBirthday());
		dto.setEmail(city.getEmail());
		dto.setAddress(city.getAddress());
		dto.setFullname(city.getFullname());
		dto.setGender(city.isGender());
		dto.setPhone(city.getPhone());
		dto.setCity(cityResponsitory.getById(city.getId_city()));
		return dto;
	}
}

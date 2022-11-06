package com.DemoSpring.Service;

import java.util.List;

import com.DemoSpring.DTO.UserDTO;

public interface UserService {
	List<UserDTO> findAll(int id);
	
	List<String> AllUsername();
	
	void Delete(int id);
	
	void AddRole(UserDTO dto);
	
	void Save(UserDTO dto);
	
	void ChangePassword(String username,String password);
	
	boolean check(String pass, String BRCTpass);
	
	UserDTO getDto(String name);
	
	UserDTO getDtoByPhone_Email(String name);
	
	UserDTO getDto(int id);
}

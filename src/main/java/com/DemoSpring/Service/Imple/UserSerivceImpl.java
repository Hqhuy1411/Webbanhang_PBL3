package com.DemoSpring.Service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.DemoSpring.Convert.ConvertUser;
import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Entity.User;
import com.DemoSpring.Security.UserCrudRespon;
import com.DemoSpring.Service.RoleService;
import com.DemoSpring.Service.UserService;
@Service
public class UserSerivceImpl implements UserService{
	@Autowired
	UserCrudRespon userCrudRespon;
	@Autowired
	ConvertUser convertUser;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	RoleService roleService;
	@Override
	public List<UserDTO> findAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Save(UserDTO dto) {
		// TODO Auto-generated method stub

			userCrudRespon.save(convertUser.toEntity2(dto));
		
	}

	@Override
	public UserDTO getDto(int id) {
		// TODO Auto-generated method stub
		User user = userCrudRespon.findById(id).get();
		return convertUser.ToDTO(user);
	}

	@Override
	public List<String> AllUsername() {
		// TODO Auto-generated method stub
		
		return userCrudRespon.getAllUsername();
	}

	@Override
	public UserDTO getDto(String name) {
		// TODO Auto-generated method stub
		User user = userCrudRespon.getUserbyUsername(name);
		return convertUser.ToDTO(user);	}

	@Override
	public void ChangePassword(String username, String password) {
		// TODO Auto-generated method stub
		String passwordString = passwordEncoder.encode(password);
		userCrudRespon.update(passwordString, username);
	}

	@Override
	public boolean check(String pass, String BRCTpass) {
		// TODO Auto-generated method stub
		if(passwordEncoder.matches(pass, BRCTpass))
			return true;
		return false;
	}

	@Override
	public void AddRole(UserDTO dto) {
		// TODO Auto-generated method stub
		User user = convertUser.toEntity2(dto);
		user.getRoles().add(roleService.getId(2));
		user.getRoles().add(roleService.getId(3));
		userCrudRespon.save(user);
	}

	@Override
	public UserDTO getDtoByPhone_Email(String name) {
		if(userCrudRespon.getUserByEmailorPhone(name) !=null) {
			return convertUser.ToDTO(userCrudRespon.getUserByEmailorPhone(name));
		}
		return null;
	}

}

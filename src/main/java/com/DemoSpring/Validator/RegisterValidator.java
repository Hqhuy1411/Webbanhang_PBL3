package com.DemoSpring.Validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.DemoSpring.DTO.UserDTO;
import com.DemoSpring.Service.UserService;

@Configuration

public class RegisterValidator implements Validator {
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDTO dto = (UserDTO) target;
		if (dto.getFullname() == null || dto.getFullname().length() == 1) {
			errors.rejectValue("fullname", "fullname.reqiured");
		}
		if (userService.AllUsername().contains(dto.getUsername())) {
			errors.rejectValue("username", "username.reqiured");
		}
		if(!pattern.matcher(dto.getPassword()).matches()) {
			errors.rejectValue("password", "password.reqiured");
		}
		
		if(!dto.getPassword().equals(dto.getConfirm_password())) {
			errors.rejectValue("confirm_password", "confirm_password.reqiured");
		}
	}

}

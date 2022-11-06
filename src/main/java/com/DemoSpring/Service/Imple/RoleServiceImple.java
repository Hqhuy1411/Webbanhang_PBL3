package com.DemoSpring.Service.Imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpring.Entity.Role;
import com.DemoSpring.Repository.RoleResponsitory;
import com.DemoSpring.Service.RoleService;

@Service
public class RoleServiceImple implements RoleService {
	@Autowired
	RoleResponsitory roleResponsitory;
	
	
	@Override
	public Role getId(int i) {
		
		return roleResponsitory.getById(i);
	}
	
}

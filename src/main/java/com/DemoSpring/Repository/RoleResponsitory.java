package com.DemoSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DemoSpring.Entity.Role;

public interface RoleResponsitory extends JpaRepository<Role, Integer> {
	
}

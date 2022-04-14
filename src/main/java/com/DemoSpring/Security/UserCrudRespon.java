package com.DemoSpring.Security;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.DemoSpring.Entity.User;

public interface UserCrudRespon extends CrudRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.username=:username ")
	public User getUserbyUsername(@Param("username") String username);
}

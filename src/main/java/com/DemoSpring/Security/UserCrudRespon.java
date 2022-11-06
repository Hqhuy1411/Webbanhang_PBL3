package com.DemoSpring.Security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.DemoSpring.Entity.Product;
import com.DemoSpring.Entity.User;

public interface UserCrudRespon extends CrudRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.username=:username ")
	public User getUserbyUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.phone=:username or u.email=:username ")
	public User getUserByEmailorPhone(@Param("username") String username);
	
	@Query("SELECT u.username FROM User u")
	public List<String> getAllUsername();
	
	@Query("SELECT u FROM User u WHERE u.id=:id ")
	public User getById(@Param("id") Integer id);
	
	
	@Query("SELECT u.list FROM User u WHERE u.username=:username")
	List<Product> getAllProduct(@Param("username") String username);
	@Transactional
	@Modifying
	@Query("Update User u SET u.password=:password WHERE u.username=:username")
	public void update(@Param("password") String password,@Param("username") String username);
}

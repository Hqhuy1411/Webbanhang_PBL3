package com.DemoSpring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DemoSpring.Entity.CartItem;

public interface CartItems extends JpaRepository<CartItem, Integer> {
	@Query("Select a from CartItem a where a.productEntity.Byuser.id =:id")
	List<CartItem> getCartItems(@Param("id") int id);
	@Query("Select a from CartItem a where a.productEntity.Byuser.id =:id and a.productEntity.category.name=:name")
	List<CartItem> getCartItemsByCategory(@Param("id") int id,@Param("name") String name);
	
	@Query("Select a from CartItem a where a.user.id=:id")
	List<CartItem> getCartItemsByCustomer(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CartItem a WHERE a.id=?1 ")
	void deletebyUserandProduct(int id);

}

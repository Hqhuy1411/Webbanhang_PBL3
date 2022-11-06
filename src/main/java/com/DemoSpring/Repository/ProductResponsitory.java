package com.DemoSpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DemoSpring.Entity.Product;

public interface ProductResponsitory extends JpaRepository<Product, Integer> {
	@Query("SELECT a FROM Product a Where CONCAT(a.name , a.description ) like %?1%  ")
	List<Product> findCharacter(String charaters);
	// Show all Product craete by User and Catelogry
	@Query("SELECT a FROM Product a INNER JOIN a.Byuser t WHERE t.username=:username and a.category.name=:category")
	List<Product> findAllbySeller(@Param("username") String username,@Param("category") String category);
	// Show all Product craete by User and search
	@Query("SELECT a FROM Product a INNER JOIN a.Byuser t WHERE t.username=?1 and CONCAT(a.name , a.description ) like %?2% ")
	List<Product> findAllbySeller2(String username,String charaters);
	@Query("SELECT a FROM Product a INNER JOIN a.Byuser t WHERE t.username=?1 and CONCAT(a.name , a.description ) like %?2% and a.category.name =?3")
	List<Product> findCharacter(String username,String charaters,String cate);
}

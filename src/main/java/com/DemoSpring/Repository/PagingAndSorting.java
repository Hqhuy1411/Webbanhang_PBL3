package com.DemoSpring.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.DemoSpring.Entity.Product;

public interface PagingAndSorting extends PagingAndSortingRepository<Product, Integer>{
	@Query("SELECT a FROM Product a Where CONCAT(a.name , a.description ) like %?1% ")
	Page<Product> findCharacter(String charaters,Pageable pageable);
	
	@Query("SELECT a FROM Product a Where a.category.name =?1 ")
	Page<Product> findCategory(String charaters,Pageable pageable);
	
	@Query("SELECT a FROM Product a Where CONCAT(a.name , a.description ) like %?1% and a.category.name =?2 ")
	Page<Product> findCharacter(String charaters,String cate,Pageable pageable);
}

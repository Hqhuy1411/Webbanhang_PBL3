package com.DemoSpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DemoSpring.Entity.Category;

public interface CategoryResponsitory extends JpaRepository<Category, Integer> {
	@Query("SELECT a.name FROM Category a ")
	List<String> findAllName();
	@Query("SELECT a FROM Category a WHERE a.name=:sp")
	Category findOneByName(@Param("sp") String name);
}

package com.DemoSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DemoSpring.Entity.Category;

public interface CategoryResponsitory extends JpaRepository<Category, Integer> {

}

package com.DemoSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DemoSpring.Entity.Product;

public interface ProductResponsitory extends JpaRepository<Product, Integer> {

}

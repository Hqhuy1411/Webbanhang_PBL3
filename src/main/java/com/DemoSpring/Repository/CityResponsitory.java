package com.DemoSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DemoSpring.Entity.City;

public interface CityResponsitory extends JpaRepository<City, Integer>{

}

package com.DemoSpring.Service;

import java.util.List;

import com.DemoSpring.DTO.CityDTO;

public interface CityService {
	List<CityDTO> findAll();
	
	void Delete(int id);
	
	void Save(CityDTO dto);
	
	CityDTO getDto(int id);
}

package com.DemoSpring.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.DemoSpring.DTO.CityDTO;
import com.DemoSpring.Entity.City;

@Component
public class ConvertCity {
	public CityDTO ToDTO(City city) {
		CityDTO dto = new CityDTO();
		dto.setId(city.getId());
		dto.setName(city.getName());
		return dto;
	}
	public List<CityDTO> toListDTO(List<City> cities){
		List<CityDTO> list = new ArrayList<CityDTO>();
		for (City city : cities) {
			list.add(ToDTO(city));
		}
		return list;
	}
	public City toEntity(CityDTO dto) {
		City city = new City();
		city.setId(dto.getId());
		city.setName(dto.getName());
		return city;
	}
}

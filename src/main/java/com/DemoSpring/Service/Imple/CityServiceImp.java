package com.DemoSpring.Service.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DemoSpring.Convert.ConvertCity;
import com.DemoSpring.DTO.CityDTO;
import com.DemoSpring.Entity.City;
import com.DemoSpring.Repository.CityResponsitory;
import com.DemoSpring.Service.CityService;

@Service
public class CityServiceImp implements CityService {
	@Autowired
	CityResponsitory cityResponsitory;
	@Autowired
	ConvertCity convertCity;
	
	@Override
	public List<CityDTO> findAll() {
		// TODO Auto-generated method stub
		List<CityDTO> list = convertCity.toListDTO(cityResponsitory.findAll());
		return list;
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		City city = cityResponsitory.findById(id).get();
		cityResponsitory.delete(city);
	}

	@Override
	public void Save(CityDTO dto) {
		// TODO Auto-generated method stub
		cityResponsitory.save(convertCity.toEntity(dto));
	}

	@Override
	public CityDTO getDto(int id) {
		// TODO Auto-generated method stub
		City city = cityResponsitory.findById(id).get();
		return convertCity.ToDTO(city);
	}

}

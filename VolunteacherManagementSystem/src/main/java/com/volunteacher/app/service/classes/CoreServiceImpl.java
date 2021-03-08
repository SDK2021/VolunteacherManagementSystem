package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Country;
import com.volunteacher.app.model.District;
import com.volunteacher.app.model.State;
import com.volunteacher.app.model.Taluka;
import com.volunteacher.app.model.Village;
import com.volunteacher.app.repository.CountryRepository;
import com.volunteacher.app.repository.DistrictRepository;
import com.volunteacher.app.repository.StateRepository;
import com.volunteacher.app.repository.TalukaRepository;
import com.volunteacher.app.repository.UserRepository;
import com.volunteacher.app.repository.VillageRepository;
import com.volunteacher.app.service.interfaces.CoreService;

@Service
public class CoreServiceImpl implements CoreService {
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	TalukaRepository talukaRepository;
	
	@Autowired
	VillageRepository villageRepository;
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public List<Country> countryList() {
		List<Country> countryList = (List<Country>) countryRepository.findAll();
		
		if(countryList.size() < 1)
			throw new ResourceNotFoundException("Country list not found");
		
		return countryList;
	}

	@Override
	public List<State> statesList() {
		List<State> stateList = (List<State>) stateRepository.findAll();
		System.out.println(stateList);
		if(stateList.size() < 1)
			throw new ResourceNotFoundException("State list not found");
		
		return stateList;
	}

	@Override
	public List<District> districtsList() {
		List<District> districtList = (List<District>) districtRepository.findAll();
		
		if(districtList.size() < 1)
			throw new ResourceNotFoundException("District list not found");
		
		return districtList;
	}

	@Override
	public List<Taluka> talukasList() {
		List<Taluka> talukaList = (List<Taluka>) talukaRepository.findAll();
		
		if(talukaList.size() < 1)
			throw new ResourceNotFoundException("Taluka list not found");
		
		return talukaList;
	}

	@Override
	public List<Village> villagesList() {
		List<Village> villageList = (List<Village>) villageRepository.findAll();
		
		if(villageList.size() < 1)
			throw new ResourceNotFoundException("Villages list not found");
		
		return villageList;
	}

	@Override
	public List<State> statesByCountry(int id) {
		List<State> stateList = stateRepository.findAllByCountryCountryId(id);
		return stateList;
	}

	@Override
	public List<District> districtsByState(int id) {
		List<District> districtList = districtRepository.findAllByStateStateId(id);
		return districtList;
	}

	@Override
	public List<Taluka> talukasByDistrict(int id) {
		List<Taluka> talukaList = talukaRepository.findAllByDistrictDistrictId(id);
		return talukaList;
	}

	@Override
	public List<Village> villagesByTaluka(int id) {
		List<Village> villageList = villageRepository.findAllByTalukaTalukaId(id);
		return villageList;
	}
}

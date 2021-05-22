package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Area;
import com.volunteacher.app.model.Country;
import com.volunteacher.app.model.District;
import com.volunteacher.app.model.State;
import com.volunteacher.app.model.Taluka;
import com.volunteacher.app.model.Village;
import com.volunteacher.app.repository.AreaRepository;
import com.volunteacher.app.repository.CountryRepository;
import com.volunteacher.app.repository.DistrictRepository;
import com.volunteacher.app.repository.StateRepository;
import com.volunteacher.app.repository.TalukaRepository;
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
	AreaRepository areaRepository;
	

	@Override
	public ResponseEntity<Object> countryList() 
	{
		try {
			List<Country> countryList = (List<Country>) countryRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(countryList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Country");
		}
	}

	@Override
	public ResponseEntity<Object> statesList() 
	{
		try {
			List<State> stateList = (List<State>) stateRepository.findAll();
			
			if(stateList.size() < 1)
				throw new ResourceNotFoundException("State list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(stateList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Sates");
		}
	}

	@Override
	public ResponseEntity<Object> districtsList() 
	{
		try {
			List<District> districtList = (List<District>) districtRepository.findAll();
			
			if(districtList.size() < 1)
				throw new ResourceNotFoundException("District list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(districtList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Districts");
		}
	}

	@Override
	public ResponseEntity<Object> talukasList() 
	{
		try {
			List<Taluka> talukaList = (List<Taluka>) talukaRepository.findAll();
			
			if(talukaList.size() < 1)
				throw new ResourceNotFoundException("Taluka list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(talukaList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Talukas");
		}
	}

	@Override
	public ResponseEntity<Object> villagesList() 
	{
		try {
			List<Village> villageList = (List<Village>) villageRepository.findAll();
			
			if(villageList.size() < 1)
				throw new ResourceNotFoundException("Villages list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(villageList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Villages");
		}
	}

	@Override
	public ResponseEntity<Object> statesByCountry(int id) 
	{
		try {
			List<State> stateList = stateRepository.findAllByCountryCountryId(id);
			return ResponseEntity.status(HttpStatus.OK).body(stateList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch States");
		}
	}

	@Override
	public ResponseEntity<Object> districtsByState(int id) 
	{
		try {
			List<District> districtList = districtRepository.findAllByStateStateId(id);
			return ResponseEntity.status(HttpStatus.OK).body(districtList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch districts");
		}
		
	}

	@Override
	public ResponseEntity<Object> talukasByDistrict(int id) 
	{
		try {
			List<Taluka> talukaList = talukaRepository.findAllByDistrictDistrictId(id);
			return ResponseEntity.status(HttpStatus.OK).body(talukaList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Talukas");
		}
	}

	@Override
	public ResponseEntity<Object> villagesBytaluka(int id) 
	{
		try {
			List<Village> villageList = villageRepository.findAllByTalukaTalukaId(id);
			return ResponseEntity.status(HttpStatus.OK).body(villageList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Villages");
		}
	}
	
	@Override
	public ResponseEntity<Object> addDistrict(District district) 
	{
		try {
			District saveDistrict = districtRepository.save(district);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveDistrict);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating District");
		}
	}

	@Override
	public ResponseEntity<Object> addTaluka(Taluka taluka) 
	{
		try {
			Taluka saveTaluka = talukaRepository.save(taluka);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveTaluka);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating taluka");
		}
	}

	@Override
	public ResponseEntity<Object> addVillage(Village village) 
	{
		try {
			Village saveVillage = villageRepository.save(village);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveVillage);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Village");
		}
	}
	
	@Override
	public ResponseEntity<Object>updateVillage(int villageId,Village village) 
	{
		try {
			System.out.println(village.getTaluka().getTalukaId());
			Village updatevillage = villageRepository.findById(villageId).orElseThrow(()-> new ResourceNotFoundException("Village not found"));
			updatevillage.setVillageName(village.getVillageName());
			updatevillage.setTaluka(village.getTaluka());
			villageRepository.save(updatevillage);
			return ResponseEntity.status(HttpStatus.CREATED).body(updatevillage);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on updating Village");
		}
	}
	
	@Override
	public ResponseEntity<Object>updateArea(int areaId,Area area) 
	{
		try {
			Area updateArea = areaRepository.findById(areaId).orElseThrow(()-> new ResourceNotFoundException("Area not found"));
			updateArea.setAreaName(area.getAreaName());		
			areaRepository.save(updateArea);
			return ResponseEntity.status(HttpStatus.CREATED).body(updateArea);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on updating Area");
		}
	}

	@Override
	public ResponseEntity<Object> areaList() {
		try {
			List<Area> areaList = (List<Area>) areaRepository.findAll();
			
			if(areaList.size() < 1)
				throw new ResourceNotFoundException("Area list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(areaList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Areas");
		}
	}

	@Override
	public ResponseEntity<Object> addArea(Area area) {
		try {
			Area saveArea = areaRepository.save(area);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveArea);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Area");
		}
	}

	@Override
	public ResponseEntity<Object> areaByvillages(int id) {
		try {
			List<Area> areaList = areaRepository.findAllByVillageVillageId(id);
			return ResponseEntity.status(HttpStatus.OK).body(areaList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Area");
		}
	}

	@Override
	public ResponseEntity<Object> areaById(int id) {
		try {
			Area area = areaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("areaid not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(area);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Area by id");
		}
	}

	@Override
	public ResponseEntity<Object> villageById(int id) {
		try {
			Village village = villageRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Village id not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(village);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Village by id");
		}
	}

	@Override
	public ResponseEntity<Object> talukaById(int id) {
		try {
			Taluka taluka = talukaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Taluka id not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(taluka);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch taluka by id");
		}
	}
	
	@Override
	public ResponseEntity<Object> districtById(int id) {
		try {
			District district = districtRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("District id not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(district);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch District by id");
		}
	}

	@Override
	public ResponseEntity<Object> deleteVillageById(int id) 
	{
		try {
			Village village = villageRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Village id not found for id: "+id));
			villageRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(village);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Village by id");
		}
	}

	@Override
	public ResponseEntity<Object> deleteAreaById(int id) {
		try {
			Area area = areaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("area id not found for id: "+id));
			areaRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(area);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch area by id");
		}
	}
}

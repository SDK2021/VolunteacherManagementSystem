package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Area;
import com.volunteacher.app.model.District;
import com.volunteacher.app.model.Taluka;
import com.volunteacher.app.model.Village;

public interface CoreService {
	
	public ResponseEntity<Object> countryList();
	
	public ResponseEntity<Object> statesList();
	
	public ResponseEntity<Object> districtsList();
	
	public ResponseEntity<Object> talukasList();
	
	public ResponseEntity<Object> villagesList();
	
	public ResponseEntity<Object> talukaById(int id);
	
	public ResponseEntity<Object> villageById(int id);
	
	public ResponseEntity<Object> areaList();
	
	public ResponseEntity<Object> areaById(int id);
	
	public ResponseEntity<Object> statesByCountry(int id);
	
	public ResponseEntity<Object> addDistrict(District district);
	
	public ResponseEntity<Object> districtsByState(int id);
	
	public ResponseEntity<Object> addTaluka(Taluka taluka);
	
	public ResponseEntity<Object> talukasByDistrict(int id);
	
	public ResponseEntity<Object> addVillage(Village village);
	
	public ResponseEntity<Object> villagesBytaluka(int id);
	
	public ResponseEntity<Object> addArea(Area area);
	
	public ResponseEntity<Object> areaByvillages(int id);
	
	
}

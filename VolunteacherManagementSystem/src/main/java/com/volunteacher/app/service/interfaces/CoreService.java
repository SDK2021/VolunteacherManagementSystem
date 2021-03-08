package com.volunteacher.app.service.interfaces;

import java.util.List;

import com.volunteacher.app.model.Country;
import com.volunteacher.app.model.District;
import com.volunteacher.app.model.State;
import com.volunteacher.app.model.Taluka;
import com.volunteacher.app.model.Village;

public interface CoreService {
	
	public List<Country> countryList();
	
	public List<State> statesList();
	
	public List<District> districtsList();
	
	public List<Taluka> talukasList();
	
	public List<Village> villagesList();
	
	public List<State> statesByCountry(int id);
	
	public List<District> districtsByState(int id);
	
	public List<Taluka> talukasByDistrict(int id);
	
	public List<Village> villagesByTaluka(int id);
	
}

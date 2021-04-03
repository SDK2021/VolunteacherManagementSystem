package com.volunteacher.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.District;
import com.volunteacher.app.model.Taluka;
import com.volunteacher.app.model.Village;
import com.volunteacher.app.service.interfaces.CoreService;

@RestController
@RequestMapping("/vms")
@CrossOrigin(origins="http://localhost:4200")  
public class CoreController {
		
		@Autowired
		CoreService coreService;
		
		@GetMapping("/countries")
		public ResponseEntity<Object> getCountryList()
		{
			return coreService.countryList();
		}
		
		@GetMapping("/states")
		public ResponseEntity<Object> getStateList()
		{
			return coreService.statesList();
		}
		
		@GetMapping("/districts")
		public ResponseEntity<Object> getDistrictList()
		{
			return coreService.districtsList();
		}
		
		@GetMapping("/talukas")
		public ResponseEntity<Object> getTalukaList()
		{
			return coreService.talukasList();
		}
		
		@GetMapping("/villages")
		public ResponseEntity<Object> getVillagesList()
		{
			return coreService.villagesList();
		}
		
		@GetMapping("/country/{id}/states")
		public ResponseEntity<Object> getStatesByCountry(@PathVariable int id)
		{
			return coreService.statesByCountry(id);
		}
		
		@GetMapping("/states/{id}/districts")
		public ResponseEntity<Object> getDistrictsByState(@PathVariable int id)
		{
			return coreService.districtsByState(id);
		}
		
		@GetMapping("/districts/{id}/talukas")
		public ResponseEntity<Object> getTalukasByDistrict(@PathVariable int id)
		{
			return coreService.talukasByDistrict(id);
		}
		
		@GetMapping("/talukas/{id}/villages")
		public ResponseEntity<Object> getVillagesByTaluka(@PathVariable int id)
		{
			return coreService.villagesByTaluka(id);
		}
		
		@PostMapping("/districts")
		public ResponseEntity<Object> addDistrict(@RequestBody District district)
		{
			return coreService.addDistrict(district);
		}
		
		@PostMapping("/talukas")
		public ResponseEntity<Object> addTalukas(@RequestBody Taluka taluka)
		{
			return coreService.addTaluka(taluka);
		}
		
		@PostMapping("/villages")
		public ResponseEntity<Object> addTalukas(@RequestBody Village village)
		{
			return coreService.addVillage(village);
		}
}


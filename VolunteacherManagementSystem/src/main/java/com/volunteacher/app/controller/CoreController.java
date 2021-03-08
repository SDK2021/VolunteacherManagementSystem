package com.volunteacher.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Country;
import com.volunteacher.app.model.District;
import com.volunteacher.app.model.State;
import com.volunteacher.app.model.Taluka;
import com.volunteacher.app.model.Village;
import com.volunteacher.app.service.interfaces.CoreService;

@RestController
@RequestMapping("/vms")
@CrossOrigin(origins="http://localhost:4200")  
public class CoreController {
		
		@Autowired
		CoreService coreService;
		
		@GetMapping("/countries/")
		public List<Country> countryList()
		{
			return coreService.countryList();
		}
		
		@GetMapping("/states/")
		public List<State> stateList()
		{
			return coreService.statesList();
		}
		
		@GetMapping("/districts/")
		public List<District> districtList()
		{
			return coreService.districtsList();
		}
		
		@GetMapping("/talukas/")
		public List<Taluka> talukaList()
		{
			return coreService.talukasList();
		}
		
		@GetMapping("/villages/")
		public List<Village> villagesList()
		{
			return coreService.villagesList();
		}
}


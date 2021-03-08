package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.KidsGroup;
import com.volunteacher.app.repository.KidRepository;
import com.volunteacher.app.repository.KidsGroupRepository;
import com.volunteacher.app.service.interfaces.KidService;

@Service
public class KidServiceImpl implements KidService {
	
	@Autowired
	KidRepository kidRepository;
	
	@Autowired
	KidsGroupRepository kidsGroupRepository;
	
	@Override
	public ResponseEntity<Object> addKid(Kid kid)
	{
		try {
			
			Kid addKid = kidRepository.save(kid);
			return ResponseEntity.status(HttpStatus.OK).body(addKid);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation kid");
		}
	}
	
	@Override
	public List<Kid> kidList() {
		
		List<Kid> kidList = (List<Kid>) kidRepository.findAll();
		
		if(kidList.size() < 0)
			throw new ResourceNotFoundException("Kids List not found");
		
		return kidList;	
	}
	
	@Override
	public Kid getKid(Long id)
	{
		Kid kid = kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
		return kid;
	}
	
	public List<Kid> kidsListByGroup(int group)
	{
		List<Kid> kidsList = kidRepository.findAllByGroupGroupId(group);
		if(kidsList.size() < 1)
			throw new ResourceNotFoundException("Kid List not found for group id: "+ group);
		return kidsList;
	}
	
	public ResponseEntity<Object> updateKid(Kid kid, Long id)
	{
		Kid updatekid = kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
		
		updatekid.setName(kid.getName());
		updatekid.setArea(kid.getArea());
		updatekid.setDob(kid.getDob());
		updatekid.setGender(kid.getGender());
		updatekid.setGroup(kid.getGroup());
		updatekid.setPhoto(kid.getPhoto());
		updatekid.setSchool(kid.getSchool());
		updatekid.setStandard(kid.getStandard());
		updatekid.setVillage(kid.getVillage());
		updatekid.setGroup(kid.getGroup());
	
		kidRepository.save(updatekid);
		return ResponseEntity.status(HttpStatus.OK).body(updatekid);
	}
	
	@Override
	public ResponseEntity<Object> deleteKid(Long id)
	{
		kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
		kidRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Kid deleted for id: " + id);
	}
	
	@Override
	public List<KidsGroup> kidGroupList()
	{
		List<KidsGroup> kidGroupList = (List<KidsGroup>) kidsGroupRepository.findAll();
		
		if(kidGroupList.size() < 1)
			throw new ResourceNotFoundException("Kids Group not found");
		
		return  kidGroupList;
	}

	@Override
	public ResponseEntity<Object> addKidsGroup(KidsGroup kidsGroup) {
		try {
			KidsGroup savekidsGroup = kidsGroupRepository.save(kidsGroup);
			return ResponseEntity.status(HttpStatus.CREATED).body(savekidsGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body("Error on creating KidsGroup");
		}
		
	}

	@Override
	public ResponseEntity<Object> updateKidsGroup(KidsGroup kidsGroup, int id) {
		KidsGroup updatekidsGroup = kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));
		
		updatekidsGroup.setGroupName(kidsGroup.getGroupName());
		updatekidsGroup.setCriteria(kidsGroup.getCriteria());
		
		kidsGroupRepository.save(updatekidsGroup);
		return ResponseEntity.status(HttpStatus.OK).body(updatekidsGroup);
	}

	@Override
	public ResponseEntity<Object> deleteKidsGroup(int id) {
		kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));
		kidsGroupRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Delete kids Group id: "+id);
	}
}

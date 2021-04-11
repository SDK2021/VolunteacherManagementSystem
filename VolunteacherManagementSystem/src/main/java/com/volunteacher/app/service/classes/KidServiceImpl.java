package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
			
			Kid saveKid = kidRepository.save(kid);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveKid);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation kid");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidList(int id) 
	{
		try {
			Pageable pageable = PageRequest.of(id, 5);
			Page<Kid> kidList = (Page<Kid>) kidRepository.findAll(pageable);
			
			return ResponseEntity.status(HttpStatus.OK).body(kidList.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidById(Long id)
	{
		try {
			Kid kid = kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
			return ResponseEntity.status(HttpStatus.OK).body(kid);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidsListByVillageAndGroup(int villageId, int groupId) 
	{
		try {
			List<Kid> kidsList = kidRepository.findAllByVillageVillageIdAndGroupGroupId(villageId, groupId);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidsListByGroup(int groupId)
	{
		try {
			List<Kid> kidsList = kidRepository.findAllByGroupGroupId(groupId);
			
			if(kidsList.size() < 1)
				throw new ResourceNotFoundException("Kid List not found for group id: "+ groupId);
			
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids");
		}
	}
	
	@Override
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
		updatekid.setLevel(kid.getLevel());
		
		try {
			kidRepository.save(updatekid);
			return ResponseEntity.status(HttpStatus.OK).body(updatekid);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Kid for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> deleteKid(Long id)
	{
		kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
		
		try {
			kidRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Kid deleted for id: " + id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Kid for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> kidGroupList()
	{
		try {
			List<KidsGroup> kidGroupList = (List<KidsGroup>) kidsGroupRepository.findAll();
			
			if(kidGroupList.size() < 1)
				throw new ResourceNotFoundException("Kids Group not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(kidGroupList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids Group");
		}
	}

	@Override
	public ResponseEntity<Object> addKidsGroup(KidsGroup kidsGroup) 
	{
		try {
			KidsGroup savekidsGroup = kidsGroupRepository.save(kidsGroup);
			return ResponseEntity.status(HttpStatus.CREATED).body(savekidsGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating KidsGroup");
		}
		
	}

	@Override
	public ResponseEntity<Object> updateKidsGroup(KidsGroup kidsGroup, int id) 
	{
		KidsGroup updatekidsGroup = kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));
		
		updatekidsGroup.setGroupName(kidsGroup.getGroupName());
		updatekidsGroup.setCriteria(kidsGroup.getCriteria());
		
		try {
			kidsGroupRepository.save(updatekidsGroup);
			return ResponseEntity.status(HttpStatus.OK).body(updatekidsGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Kids Group for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteKidsGroup(int id) 
	{
		kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));
		
		try {
			kidsGroupRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Delete kids Group id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Kids Group for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByArea(int areaId) {
		try {
			List<Kid> kidsList = kidRepository.findAllByAreaAreaId(areaId);
			
			if(kidsList.size() < 1)
				throw new ResourceNotFoundException("Kid List not found for area id: "+ areaId);
			
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching area wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByVillage(int villageId) {
		try {
			List<Kid> kidsList = kidRepository.findAllByVillageVillageId(villageId);
			
			if(kidsList.size() < 1)
				throw new ResourceNotFoundException("Kid List not found for village id: "+ villageId);
			
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Village wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByVillageAndArea(int villageId, int areaId) {
		try {
			List<Kid> kidsList = kidRepository.findAllByVillageVillageIdAndAreaAreaId(villageId,areaId);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching village and area wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByAreaAndGroup(int areaId, int groupId) {
		try {
			List<Kid> kidsList = kidRepository.findAllByAreaAreaIdAndGroupGroupId(areaId, groupId);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching area and groupwise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByAreaAndGroupAndVillage(int areaId, int groupId, int villageId) {
		try {
			List<Kid> kidsList = kidRepository.findAllByAreaAreaIdAndGroupGroupIdAndVillageVillageId(areaId, groupId, villageId);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching area and group and village wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByLevel(int level) {
		try {
			List<Kid> kidsList = kidRepository.findAllByLevel(level);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching level wise kids List");
		}
	}
}

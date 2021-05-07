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
	public ResponseEntity<Object> kidList(int page) 
	{
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidList = (Page<Kid>) kidRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidList);
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
	public ResponseEntity<Object> kidsListByVillageAndGroup(int page,int villageId, int groupId) 
	{
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>) kidRepository.findAllByVillageVillageIdAndGroupGroupId(villageId, groupId, pageable);
			 return ResponseEntity.status(HttpStatus.OK).body(kidsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidsListByGroup(int page,int groupId)
	{
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>) kidRepository.findAllByGroupGroupId(groupId,pageable);	
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids");
		}
	}
	
	@Override
	public ResponseEntity<Object> updateKid(Kid kid, Long id)
	{
		try {
			Kid updatekid = kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
			
			updatekid.setName(kid.getName());
			updatekid.setArea(kid.getArea());
			updatekid.setDob(kid.getDob());
			updatekid.setGender(kid.getGender());
			updatekid.setGroup(kid.getGroup());
			updatekid.setPhoto(kid.getPhoto());
			updatekid.setSchool(kid.getSchool());
			updatekid.setStandard(kid.getStandard());
			updatekid.setLevel(kid.getLevel());
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
		try {
			kidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kid not found for id: " + id));
			kidRepository.deleteKidsAttendance(id);
			kidRepository.deleteKidsEvents(id);
			kidRepository.deleteKidsProjects(id);
			kidRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
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
			return ResponseEntity.status(HttpStatus.OK).body(kidGroupList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids Group");
		}
	}

	@Override
	public ResponseEntity<Object> addKidsGroup(KidsGroup kidsGroup) 
	{
		if(kidsGroupRepository.findByGroupName(kidsGroup.getGroupName()) != null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kids Group Already Exist");
		}
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
		try {
			KidsGroup updatekidsGroup = kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));
			
			updatekidsGroup.setGroupName(kidsGroup.getGroupName());
			updatekidsGroup.setCriteria(kidsGroup.getCriteria());
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
		try {
			kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));
			kidsGroupRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Kids Group for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByArea(int page,int areaId) {
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>) kidRepository.findAllByAreaAreaId(areaId,pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching area wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByVillage(int page,int villageId) {
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>)kidRepository.findAllByVillageVillageId(villageId,pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Village wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByVillageAndArea(int page,int villageId, int areaId) {
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>)kidRepository.findAllByVillageVillageIdAndAreaAreaId(villageId,areaId,pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching village and area wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByAreaAndGroup(int page,int areaId, int groupId) {
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList =(Page<Kid>) kidRepository.findAllByAreaAreaIdAndGroupGroupId(areaId, groupId,pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching area and groupwise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByAreaAndGroupAndVillage(int page,int areaId, int groupId, int villageId) {
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>)kidRepository.findAllByAreaAreaIdAndGroupGroupIdAndVillageVillageId(areaId, groupId, villageId,pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching area and group and village wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsListByLevel(int page,int level) {
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Kid> kidsList = (Page<Kid>)kidRepository.findAllByLevel(level,pageable);
			return ResponseEntity.status(HttpStatus.OK).body(kidsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching level wise kids List");
		}
	}

	@Override
	public ResponseEntity<Object> kidsGroupById(int id) {
		try {
			KidsGroup kidsGroup = kidsGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kids Group not found for id: "+ id));;
			return ResponseEntity.status(HttpStatus.OK).body(kidsGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Kids Group");
		}
	}

	@Override
	public ResponseEntity<Object> getTotalKids() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(kidRepository.totalKids());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on get total kids");
		}
	}
	
}

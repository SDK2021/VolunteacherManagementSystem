package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.KidsGroup;

public interface KidService {
	
	public ResponseEntity<Object> addKid(Kid kid);
	
	public ResponseEntity<Object> kidList(int id);
	
	public ResponseEntity<Object> kidById(Long id);
	
	public ResponseEntity<Object> kidsListByGroup(int groupId);
	
	public ResponseEntity<Object> kidsListByArea(int areaId);
	
	public ResponseEntity<Object> kidsListByVillage(int villageId);
	
	public ResponseEntity<Object> kidsListByVillageAndArea(int villageId,int areaId);
	
	public ResponseEntity<Object> kidsListByVillageAndGroup(int villageId, int groupId);
	
	public ResponseEntity<Object> kidsListByAreaAndGroup(int areaId,int groupId);
	
	public ResponseEntity<Object> kidsListByAreaAndGroupAndVillage(int areaId, int groupId,int villageId);
	
	public ResponseEntity<Object> kidsListByLevel(int level);
	
	public ResponseEntity<Object> updateKid(Kid kid, Long id);
	
	public ResponseEntity<Object> deleteKid(Long id);
	
	public ResponseEntity<Object> kidGroupList();
	
	public ResponseEntity<Object> addKidsGroup(KidsGroup kidsGroup);
	
	public ResponseEntity<Object> kidsGroupById(int id);
	
	public ResponseEntity<Object> updateKidsGroup(KidsGroup kidsGroup, int id);
	
	public ResponseEntity<Object> deleteKidsGroup(int id);
	
}

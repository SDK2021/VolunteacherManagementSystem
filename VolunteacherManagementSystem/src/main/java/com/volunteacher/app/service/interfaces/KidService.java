package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.KidsGroup;

public interface KidService {
	
	public ResponseEntity<Object> addKid(Kid kid);
	
	public ResponseEntity<Object> kidList();
	
	public ResponseEntity<Object> kidById(Long id);
	
	public ResponseEntity<Object> kidsListByGroup(int groupId);
	
	public ResponseEntity<Object> kidsListByGroupAndVillage(int villageId, int groupId);
	
	public ResponseEntity<Object> updateKid(Kid kid, Long id);
	
	public ResponseEntity<Object> deleteKid(Long id);
	
	public ResponseEntity<Object> kidGroupList();
	
	public ResponseEntity<Object> addKidsGroup(KidsGroup kidsGroup);
	
	public ResponseEntity<Object> updateKidsGroup(KidsGroup kidsGroup, int id);
	
	public ResponseEntity<Object> deleteKidsGroup(int id);
	
}

package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.KidsGroup;

public interface KidService {
	
public ResponseEntity<Object> addKid(Kid kid);
	
	public List<Kid> kidList();
	
	public Kid getKid(Long id);
	
	public List<Kid> kidsListByGroup(int group);
	
	public ResponseEntity<Object> updateKid(Kid kid, Long id);
	
	public ResponseEntity<Object> deleteKid(Long id);
	
	public List<KidsGroup> kidGroupList();
	
	public ResponseEntity<Object> addKidsGroup(KidsGroup kidsGroup);
	
	public ResponseEntity<Object> updateKidsGroup(KidsGroup kidsGroup, int id);
	
	public ResponseEntity<Object> deleteKidsGroup(int id);
	
}

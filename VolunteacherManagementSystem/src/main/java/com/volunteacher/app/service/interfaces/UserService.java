package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.User;

public interface UserService {
	
	public ResponseEntity<Object> addUser(User user);
	
	public ResponseEntity<Object> userList();
	
	public ResponseEntity<Object> userById(Long id);
	
	public ResponseEntity<Object> updateUser(User user, Long id);
	
	public ResponseEntity<Object> deleteUser(Long id);
	
	public ResponseEntity<Object> userByEmail(String email);
	
	public List<User> userByType(int type);
	
	public ResponseEntity<Object> userByPhoneNumber(String email);
	
	public ResponseEntity<Object> userTypeList();
	
	public ResponseEntity<Object> userTypeById(int id);
	
	public ResponseEntity<Object> usersByBirthday();
	
	public boolean setProfile(String url,String userId);
	
	public ResponseEntity<Object> totalVolunteacherBySessionVillage(int villageId);
	
	public ResponseEntity<Object> usersByUserType(int page,int id);
	
}

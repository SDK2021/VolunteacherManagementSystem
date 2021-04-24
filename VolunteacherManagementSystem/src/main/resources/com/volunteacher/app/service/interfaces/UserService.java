package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.User;

public interface UserService {
	
	public ResponseEntity<Object> addUser(User user);
	
	public ResponseEntity<Object> userList();
	
	public ResponseEntity<Object> userById(Long id);
	
	public ResponseEntity<Object> updateUser(User user, Long id);
	
	public ResponseEntity<Object> deleteUser(Long id);
	
	public ResponseEntity<Object> userByEmail(String email);
	
	public ResponseEntity<Object> userTypeList();
	
	public ResponseEntity<Object> userTypeById(int id);
	
	public ResponseEntity<Object> usersByBirthday();
	
}

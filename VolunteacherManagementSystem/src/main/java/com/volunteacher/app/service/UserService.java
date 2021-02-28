package com.volunteacher.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;

public interface UserService {
	
	public ResponseEntity<Object> addUser(User user);
	
	public List<User> userList();
	
	public List<Kid> kidList();
	
	public UserType userType(int id);
}

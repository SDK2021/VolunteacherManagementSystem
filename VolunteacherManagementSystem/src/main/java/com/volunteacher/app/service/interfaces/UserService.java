package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;

public interface UserService {
	
	public ResponseEntity<Object> addUser(User user);
	
	public List<User> userList();
	
	public User getUser(Long id);
	
	public ResponseEntity<Object> updateUser(User user, Long id);
	
	public ResponseEntity<Object> deleteUser(Long id);
	
	public List<UserType> userTypeList();
	
}

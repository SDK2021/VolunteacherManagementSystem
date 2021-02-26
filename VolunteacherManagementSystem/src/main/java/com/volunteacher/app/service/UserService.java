package com.volunteacher.app.service;

import java.util.List;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.User;

public interface UserService {
	
	public List<User> userList();
	
	public List<Kid> kidList();
	
}

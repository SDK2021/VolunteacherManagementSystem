package com.volunteacher.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;
import com.volunteacher.app.repository.KidRepository;
import com.volunteacher.app.repository.UserRepository;
import com.volunteacher.app.repository.UserTypeRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	KidRepository kidrepository;
	
	@Autowired
	UserRepository UserRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Override
	public ResponseEntity<Object> addUser(User user)
	{
		try {
			User addUser = UserRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(addUser);
		} catch (Exception e) {
		//	e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public List<User> userList() {
		Iterable<User> users = UserRepository.findAll();
		
		if(users == null)
		{
			throw new ResourceNotFoundException("User List is empty");
		}
		return (List<User>)users;
	}
	

	@Override
	public List<Kid> kidList() {
		Iterable<Kid> kids = kidrepository.findAll();
		return (List<Kid>) kids;	
	}

	@Override
	public UserType userType(int id) {
		UserType usertype = userTypeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("user type not found"));
		return usertype;
	}
}

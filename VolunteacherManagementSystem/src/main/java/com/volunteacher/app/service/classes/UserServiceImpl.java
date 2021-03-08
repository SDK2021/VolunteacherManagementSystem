package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;
import com.volunteacher.app.repository.UserRepository;
import com.volunteacher.app.repository.UserTypeRepository;
import com.volunteacher.app.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Override
	public ResponseEntity<Object> addUser(User user)
	{
		try {
			
			User addUser = userRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(addUser);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation user");
			
		}
	}
	
	@Override
	public User getUser(Long id)
	{
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for id: " + id));
		return user;
	}
	
	@Override
	public ResponseEntity<Object> updateUser(User user, Long id)
	{
		User userById = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for id: "+ id));
		userById.setEmail(user.getEmail());
		userById.setGender(user.getGender());
		userById.setPassword(user.getPassword());
		userById.setPhoneNumber(user.getPhoneNumber());
		userById.setUserName(user.getUserName());
		userById.setDob(user.getDob());
		
		userRepository.save(userById);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userById);
	}

	@Override
	public ResponseEntity<Object> deleteUser(Long id)
	{
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
		userRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User deleted for id: " + id);
	}
	
	@Override
	public List<User> userList() {
		List<User> users = (List<User>) userRepository.findAll();
		
		if(users.size() < 1)
			throw new ResourceNotFoundException("User List is empty");
		
		return users;
	}
	
	@Override
	public List<UserType> userTypeList()
	{
		List<UserType> userTypeList = (List<UserType>) userTypeRepository.findAll();
		
		if(userTypeList.size() < 1)
			throw new ResourceNotFoundException("User type List not found");
		
		return userTypeList;
	}
	
}

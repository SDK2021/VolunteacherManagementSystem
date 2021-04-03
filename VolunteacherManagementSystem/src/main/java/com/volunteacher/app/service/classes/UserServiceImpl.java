package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	public ResponseEntity<Object> addUser(User user)
	{
		try {
			String encodePasswordString = passwordEncoder.encode(user.getPassword()); 
			user.setPassword(encodePasswordString);
			User saveUser = userRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation user");
		}
	}
	
	@Override
	public ResponseEntity<Object> userById(Long id)
	{
		try {
			User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for id: " + id));
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch User for id: "+ id);
		}
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
		
		try {
			userRepository.save(userById);
			return ResponseEntity.status(HttpStatus.OK).body(userById);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating User for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteUser(Long id)
	{
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));

		try {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("User deleted for id: " + id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting user for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> userList() 
	{
		try {
			List<User> users = (List<User>) userRepository.findAll();
			
			if(users.size() < 1)
				throw new ResourceNotFoundException("User List is empty");
			
			return ResponseEntity.status(HttpStatus.OK).body(users);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Userlist");
		}
	}
	
	@Override
	public ResponseEntity<Object> userTypeList()
	{
		try {
			List<UserType> userTypeList = (List<UserType>) userTypeRepository.findAll();
			
			if(userTypeList.size() < 1)
				throw new ResourceNotFoundException("User type List not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(userTypeList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Usertype");
		}
	}

	@Override
	public ResponseEntity<Object> getUserTypeById(int id) 
	{
		try {
			UserType userType = userTypeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for id: " + id));
			return ResponseEntity.status(HttpStatus.OK).body(userType);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch User type for id: "+ id);
		}
	}
	
}

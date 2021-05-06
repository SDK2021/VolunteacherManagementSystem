package com.volunteacher.app.service.classes;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;
import com.volunteacher.app.repository.NotificationRepository;
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
	NotificationRepository notificationRepository;
	
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
		User updateuser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for id: "+ id));
		updateuser.setEmail(user.getEmail());
		updateuser.setGender(user.getGender());
		updateuser.setPassword(user.getPassword());
		updateuser.setPhoneNumber(user.getPhoneNumber());
		updateuser.setUserName(user.getUserName());
		updateuser.setDob(user.getDob());
		updateuser.setType(user.getType());
		
		
		try {
			userRepository.save(updateuser);
			return ResponseEntity.status(HttpStatus.OK).body(updateuser);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating User for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteUser(Long id)
	{
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
		notificationRepository.deleteByCreatedByUserId(id);
		try {
			System.out.println(id);
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
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
	public ResponseEntity<Object> userTypeById(int id) {
		UserType userType = userTypeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("UserType is not found id:" +id));
		return ResponseEntity.status(HttpStatus.OK).body(userType);
	}

	@Override
	public ResponseEntity<Object> userByEmail(String email) {
		System.out.println(email);
		try {
			User user = userRepository.findByEmail(email);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching User by Email");
		}
	}

	@Override
	public ResponseEntity<Object> userByPhoneNumber(String number) {
		System.out.println(number);
		try {
			User user = userRepository.findByPhoneNumber(number);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching User by Email");
		}
	}
	
	@Override
	public ResponseEntity<Object> usersByBirthday() {
		try {
			List<User> userList = userRepository.findAllByDob();
			return ResponseEntity.status(HttpStatus.OK).body(userList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching User by Email");
		}
	}
	
	@Override
	public boolean setProfile(String url,String userId) {
		try {
			User user =userRepository.findById(Long.parseLong(userId)).orElseThrow(()-> new ResourceNotFoundException("Error on set profile"));
			user.setPhoto(url);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> userByType(int type) {
		try {
			List<User> userList = userRepository.findAllByTypeTypeId(type);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}
	
}

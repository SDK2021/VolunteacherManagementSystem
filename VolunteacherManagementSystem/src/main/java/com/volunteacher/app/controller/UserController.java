package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;
import com.volunteacher.app.model.Volunteacher;
import com.volunteacher.app.service.interfaces.UserService;
import com.volunteacher.app.service.interfaces.VolunteacherService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")  
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	VolunteacherService volunteacherService;
	
	@GetMapping("/volunteachers/")
	public List<Volunteacher> volunteacherList()
	{
		return volunteacherService.volunteacherList();
	}
	
	@GetMapping("/volunteachers/day")
	public List<Volunteacher> vyByDay()
	{
		return volunteacherService.vtByToday();
	}
	
	@GetMapping("/users/")
	public List<User> userList()
	{
		return userService.userList();
	}
	
	@GetMapping("/users/{id}")
	public User User(@PathVariable Long id)
	{
		return userService.getUser(id);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id)
	{
		return userService.deleteUser(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user)
	{
		return userService.addUser(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user,@PathVariable Long id)
	{
		return userService.updateUser(user, id);
	}
	
	@GetMapping("/user-types/")
	public List<UserType> userTypeList()
	{
		return userService.userTypeList();
	}
	
	@GetMapping("/error")
	public void check_error()
	{
		throw new ResourceNotFoundException("No resource found");
	}

}

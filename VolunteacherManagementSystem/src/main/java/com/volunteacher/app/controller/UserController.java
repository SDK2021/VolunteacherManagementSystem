package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;
import com.volunteacher.app.service.UserService;

@RestController
@RequestMapping(path = "/vms")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/kids")
	public List<Kid> kidsList()
	{
		return userService.kidList();
	}
	
	@GetMapping("/user")
	public List<User> userList()
	{
		return userService.userList();
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody User user)
	{
		return userService.addUser(user);
	}
	
	@GetMapping("/usertype/{id}")
	public UserType typeById(@PathVariable int id)
	{
		return userService.userType(id);
	}
	@GetMapping("/error")
	public void check_error()
	{
		throw new ResourceNotFoundException("No resource found");
	}

}

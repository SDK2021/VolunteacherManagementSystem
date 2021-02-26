package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Kid;
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

}

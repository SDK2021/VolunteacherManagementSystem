package com.volunteacher.app.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.User;
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
	
	@GetMapping("/volunteachers")
	public ResponseEntity<Object> getVolunteacherList()
	{
		return volunteacherService.volunteacherList();
	}
	
	@PostMapping("/volunteachers")
	public ResponseEntity<Object> addVolunteacher(@RequestBody Volunteacher volunteacher)
	{
		return volunteacherService.addVolunteacher(volunteacher);
	}
	
	@GetMapping("/volunteachers/{id}")
	public ResponseEntity<Object> getVolunteacher(@PathVariable int id)
	{
		return volunteacherService.volunteacherById(id);
	}
	
	@GetMapping("/volunteachers/users")
	public ResponseEntity<Object> getVolunteacherByUserId(@RequestParam("user") long userId)
	{
		return volunteacherService.volunteacherByUserId(userId); 
	}
	
	@PutMapping("/volunteachers/{id}")
	public ResponseEntity<Object> updateVolunteacher(@RequestBody Volunteacher volunteacher, @PathVariable int id)
	{
		return volunteacherService.updateVolunteacher(volunteacher, id);
	}
//	
//	@DeleteMapping("/volunteachers/{id}")
//	public ResponseEntity<Object> deleteVolunteacher(@PathVariable int id)
//	{
//		return volunteacherService.deleteVolunteacher(id);
//	}
//	
	@GetMapping("/volunteachers/day")
	public ResponseEntity<Object> getVolunteacherByDay()
	{
		return volunteacherService.vtByToday();
	}
	
	@GetMapping("/users")
	public ResponseEntity<Object> getUserList()
	{
		return userService.userList();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Long id)
	{
		return userService.userById(id);
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
	
	@GetMapping("/emailusers")
	public ResponseEntity<Object> getUserByEmail(@RequestParam("email") String email)
	{
		return userService.userByEmail(email);
	}
	
	@GetMapping("/user-types")
	public ResponseEntity<Object> getUserTypeList()
	{
		return userService.userTypeList();
	}
	
	@GetMapping("/user-types/{id}")
	public ResponseEntity<Object> getUserTypeList(@PathVariable int id)
	{
		return userService.userTypeById(id);
	}
	
	@GetMapping("/birthUsers")
	public ResponseEntity<Object> getUserByBirthday()
	{
		return userService.usersByBirthday();
	}
	
	@GetMapping("/error")
	public void check_error()
	{
		throw new ResourceNotFoundException("No resource found");
	}

}

package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.TimelinePost;
import com.volunteacher.app.service.interfaces.TimelinePostService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class TimelinePostController {
	
	@Autowired
	TimelinePostService timelinePostService;
	
	@GetMapping("/posts/")
	public List<TimelinePost> postList()
	{
		return timelinePostService.postList();
	}
	
	@PostMapping("/posts")
	public ResponseEntity<Object> addPost(@RequestBody TimelinePost post)
	{
		return timelinePostService.addPost(post);
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Object> deletePost(@PathVariable Long id)
	{
		return timelinePostService.deletePost(id);
	}
	
}

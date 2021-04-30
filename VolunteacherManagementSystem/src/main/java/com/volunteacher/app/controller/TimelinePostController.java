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

import com.volunteacher.app.model.TimelinePost;
import com.volunteacher.app.service.interfaces.TimelinePostService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class TimelinePostController {
	
	@Autowired
	TimelinePostService timelinePostService;
	
	@GetMapping("/posts")
	public ResponseEntity<Object> getPostList(@RequestParam("page") int page)
	{
		return timelinePostService.postList(page);
	}
	
	@PostMapping("/posts")
	public ResponseEntity<Object> addPost(@RequestBody TimelinePost post)
	{
		return timelinePostService.addPost(post);
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Object> updatePost(@RequestBody TimelinePost post,@PathVariable Long id)
	{
		return timelinePostService.updatePost(post, id);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Object> deletePost(@PathVariable Long id)
	{
		return timelinePostService.deletePost(id);
	}
	
	@GetMapping("/totalPosts/{id}")
	public int getAllPostByUser(@PathVariable int id)
	{
		return timelinePostService.TotalPostByUser(id);
	}
	
	@GetMapping("/userPosts")
	public ResponseEntity<Object> getAllPostByUser(@RequestParam("user") Long id)
	{
		return timelinePostService.postListByUser(id);
	}
	
}
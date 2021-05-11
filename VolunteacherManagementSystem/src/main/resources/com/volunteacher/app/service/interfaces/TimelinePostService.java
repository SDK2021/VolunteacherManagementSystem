package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.TimelinePost;

public interface TimelinePostService {
	
	public ResponseEntity<Object> addPost(TimelinePost post);
	
	public ResponseEntity<Object> postList(int page);
	
	public ResponseEntity<Object> updatePost(TimelinePost post, Long id);
	
	public ResponseEntity<Object> postById(Long id);
	
	public ResponseEntity<Object> deletePost(Long id);
	
	public ResponseEntity<Object> postListByUser(Long id);
	
	public int TotalPostByUser(int id);
	
}

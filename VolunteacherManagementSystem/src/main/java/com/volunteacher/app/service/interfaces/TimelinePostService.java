package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.TimelinePost;

public interface TimelinePostService {
	
	public ResponseEntity<Object> addPost(TimelinePost post);
	
	public List<TimelinePost> postList();
	
	public ResponseEntity<Object> updatePost(TimelinePost post, Long id);
	
	public TimelinePost postById(Long id);
	
	public ResponseEntity<Object> deletePost(Long id);
	
}

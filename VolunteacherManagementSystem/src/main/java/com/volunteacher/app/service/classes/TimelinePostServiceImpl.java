package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.TimelinePost;
import com.volunteacher.app.repository.TimelinePostRepository;
import com.volunteacher.app.service.interfaces.TimelinePostService;

@Service
public class TimelinePostServiceImpl implements TimelinePostService {
	
	@Autowired
	TimelinePostRepository timelinePostRepository;
	
	@Override
	public ResponseEntity<Object> addPost(TimelinePost post)
	{
		try {
			TimelinePost addPost = timelinePostRepository.save(post);
			return ResponseEntity.status(HttpStatus.CREATED).body(addPost);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating post");
		}
	}
	
	@Override
	public List<TimelinePost> postList()
	{
		List<TimelinePost> postList = (List<TimelinePost>) timelinePostRepository.findAll();
		
		if(postList.size() < 1)
			throw new ResourceNotFoundException("Timepost list not found");
		
		return  postList;
	}
	
	@Override
	public TimelinePost postById(Long id)
	{
		TimelinePost post = timelinePostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post is not found for id: "+id));
		return post;
	}
	
	@Override
	public ResponseEntity<Object> updatePost(TimelinePost post, Long id)
	{
		TimelinePost updatePost = timelinePostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found for id: "+id));
		updatePost.setPostTitle(post.getPostTitle());
		updatePost.setPostDescription(post.getPostDescription());
		updatePost.setPostPhoto(post.getPostPhoto());
		
		timelinePostRepository.save(updatePost);
		return ResponseEntity.status(HttpStatus.OK).body(updatePost);
		
	}
	
	@Override
	public ResponseEntity<Object> deletePost(Long id)
	{
		timelinePostRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("POst is not found for id: "+ id));
		timelinePostRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("post deleted for id: "+id);
	}
	
}

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
			TimelinePost savePost = timelinePostRepository.save(post);
			return ResponseEntity.status(HttpStatus.CREATED).body(savePost);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating post");
		}
	}
	
	@Override
	public ResponseEntity<Object> postList()
	{
		try {
			List<TimelinePost> postList = (List<TimelinePost>) timelinePostRepository.findAll();
			
			if(postList.size() < 1)
				throw new ResourceNotFoundException("Timelinepost list not found");
			return ResponseEntity.status(HttpStatus.OK).body(postList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Timeline post ");
		}
	}
	
	@Override
	public ResponseEntity<Object> postById(Long id)
	{
		try {
			TimelinePost post = timelinePostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Timeline Post is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(post);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Post for id: "+ id);
		}
	}
	
	@Override
	public ResponseEntity<Object> updatePost(TimelinePost post, Long id)
	{
		TimelinePost updatePost = timelinePostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Timeline Post not found for id: "+id));
		
		updatePost.setPostTitle(post.getPostTitle());
		updatePost.setPostDescription(post.getPostDescription());
		updatePost.setPostPhoto(post.getPostPhoto());
		
		try {
			timelinePostRepository.save(updatePost);
			return ResponseEntity.status(HttpStatus.OK).body(updatePost);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Timeline postfor id:" +id);
		}
		
	}
	
	@Override
	public ResponseEntity<Object> deletePost(Long id)
	{
		timelinePostRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TimelinePost is not found for id: "+ id));
	
		try {
			timelinePostRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("post deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Timeline Post for id:" +id);
		}
	}
	
}

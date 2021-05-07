package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Content;
import com.volunteacher.app.repository.ContentRepository;
import com.volunteacher.app.service.interfaces.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentRepository contentRepository;

	@Override
	public ResponseEntity<Object> addContent(Content content)
	{
		try {
			Content saveContent = contentRepository.save(content);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveContent);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Content");
		}
	}
	
	@Override
	public ResponseEntity<Object> contentList()
	{
		try {
			List<Content> contentList = (List<Content>) contentRepository.findAll();	
			return ResponseEntity.status(HttpStatus.CREATED).body(contentList);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch content list");
		}
	}
	
	@Override
	public ResponseEntity<Object> contentById(int id)
	{
		try {
			Content content = contentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Content is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.CREATED).body(content);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Content for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> updateContent(Content content, int id)
	{
		try {
			Content updateContent = contentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Content not found for id: "+id));
			
			updateContent.setContentData(content.getContentData());
			updateContent.setGroup(content.getGroup());
			contentRepository.save(updateContent);
			return ResponseEntity.status(HttpStatus.OK).body(updateContent);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Content for id:" +id);
		}
		
	}
	
	@Override
	public ResponseEntity<Object> contentByGroup(int groupId)
	{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findByGroupGroupId(groupId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in fetching Content By group");
		}
	}
	
	@Override
	public ResponseEntity<Object> deleteContent(int id)
	{
		contentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Content is not found for id: "+ id));
		
		try {
			contentRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting content for id:" +id);
		}
	}
}

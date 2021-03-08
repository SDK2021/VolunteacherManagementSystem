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
		try 
		{
			contentRepository.save(content);
			return ResponseEntity.status(HttpStatus.CREATED).body(content);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Content");
		}
	}
	
	@Override
	public List<Content> contentList()
	{
		List<Content> contentList = (List<Content>) contentRepository.findAll();
		
		if(contentList.size() < 1)
			throw new ResourceNotFoundException("Content List not found");
		
		return contentList;
	}
	
	@Override
	public Content contentById(int id)
	{
		Content content = contentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Content is not found for id: "+id));
		return content;
	}
	
	@Override
	public ResponseEntity<Object> updateContent(Content content, int id)
	{
		Content updateContent = contentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Content not found for id: "+id));
		
		updateContent.setContentData(content.getContentData());
		updateContent.setGroup(content.getGroup());
		
		contentRepository.save(updateContent);
		return ResponseEntity.status(HttpStatus.OK).body(updateContent);
		
	}
	
	@Override
	public ResponseEntity<Object> deleteContent(int id)
	{
		contentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Content is not found for id: "+ id));
		contentRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Content deleted for id: "+id);
	}
}

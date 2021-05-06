package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Content;

public interface ContentService {
	
	public ResponseEntity<Object> addContent(Content content);
	
	public ResponseEntity<Object> contentList();
	
	public ResponseEntity<Object> contentById(int id);
	
	public ResponseEntity<Object> updateContent(Content content, int id);
	
	public ResponseEntity<Object> deleteContent(int id);
}

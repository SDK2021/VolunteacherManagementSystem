package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Content;

public interface ContentService {
	
	public ResponseEntity<Object> addContent(Content content);
	
	public List<Content> contentList();
	
	public Content contentById(int id);
	
	public ResponseEntity<Object> updateContent(Content content, int id);
	
	public ResponseEntity<Object> deleteContent(int id);
}

package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Participant;

public interface ParticipantService {
	
	public ResponseEntity<Object> addParticipant(Participant participant);

	public ResponseEntity<Object> participantList();
	
	public ResponseEntity<Object> participantById(Long id);
	
	public ResponseEntity<Object> updateParticipant(Participant participant, Long id);
}

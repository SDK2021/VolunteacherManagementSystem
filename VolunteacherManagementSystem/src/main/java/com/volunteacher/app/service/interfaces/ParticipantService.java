package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Participant;

public interface ParticipantService {
	
	public ResponseEntity<Object> addParticipant(Participant participant);

	public List<Participant> participantList();
	
	public Participant participant(Long id);
	
	public ResponseEntity<Object> updateParticipant(Participant participant, Long id);
}

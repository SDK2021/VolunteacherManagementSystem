package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Participant;
import com.volunteacher.app.model.User;

public interface ParticipantService {
	
	public ResponseEntity<Object> addParticipant(Participant participant);

	public ResponseEntity<Object> participantList();
	
	public ResponseEntity<Object> participantById(Long id);
	
	public ResponseEntity<Object> updateParticipant(Participant participant, Long id);
	
	public ResponseEntity<Object> totalParticipantByEvent(int eventId);
	
	public ResponseEntity<Object> totalVolunteacherParticipantByEvent(int eventId,int typeId);
		
	public ResponseEntity<Object> totalOtherParticipantByEvent(int eventId);
	
	public ResponseEntity<Object> addUserParticipant(List<User> users,int eventId);

}

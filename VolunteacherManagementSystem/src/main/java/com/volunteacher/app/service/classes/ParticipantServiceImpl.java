package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Participant;
import com.volunteacher.app.repository.ParticipantRepository;
import com.volunteacher.app.service.interfaces.ParticipantService;

@Service
public class ParticipantServiceImpl implements ParticipantService {
	
	@Autowired
	ParticipantRepository participantRepository;
	
	@Override
	public ResponseEntity<Object> addParticipant(Participant participant)
	{
		try {
			Participant addParticipant = participantRepository.save(participant);
			return ResponseEntity.status(HttpStatus.CREATED).body(addParticipant);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating participant");
		}
	}
	
	@Override
	public List<Participant> participantList()
	{
		List<Participant> participantList = (List<Participant>) participantRepository.findAll();
		
		if(participantList.size() < 1)
			throw new ResourceNotFoundException("Participant List not found");
		
		return participantList;
	}
	
	@Override
	public Participant participant(Long id)
	{
		Participant participant = participantRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("participant not found for id: "+ id));
		return participant;
	}
	@Override
	public ResponseEntity<Object> updateParticipant(Participant participant, Long id)
	{
		Participant updateparticipant = participantRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("participant not found for id: "+ id));
		updateparticipant.setActivities(participant.getActivities());
		updateparticipant.setName(participant.getName());
		updateparticipant.setGender(participant.getGender());
		updateparticipant.setDob(participant.getDob());
		updateparticipant.setEmail(participant.getEmail());
		updateparticipant.setPhoneNumber(participant.getPhoneNumber());
		updateparticipant.setType(participant.getType());
		
		participantRepository.save(updateparticipant);
		return ResponseEntity.status(HttpStatus.OK).body(updateparticipant);
		
	}
	
	
}

package com.volunteacher.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Participant;

@Repository
public interface ParticipantRepository extends PagingAndSortingRepository<Participant, Long>{

	@Query(nativeQuery = true,value = "Select COUNT(*) from participant where event_event_id=:eventId")
	int totalParticipantByEvent(int eventId);
	
	@Query(nativeQuery = true,value = "Select COUNT(*) from participant where user_type_type_id=:typeId and event_event_id=:eventId")
	int totalParticipateVolunteacherByEvent(int typeId,int eventId);
	
	@Query(nativeQuery = true,value = "Select COUNT(*) from participant where event_event_id=:eventId")
	int totalParticipateOtherByEvent(int eventId);
	
	Participant findByEventEventIdAndEmail(int eventId,String email);
}

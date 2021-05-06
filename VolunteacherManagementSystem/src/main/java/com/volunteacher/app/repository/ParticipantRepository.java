package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Participant;

@Repository
public interface ParticipantRepository extends PagingAndSortingRepository<Participant, Long>{

}

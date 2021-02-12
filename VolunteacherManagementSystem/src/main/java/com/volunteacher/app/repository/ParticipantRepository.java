package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Participant;

public interface ParticipantRepository extends PagingAndSortingRepository<Participant, Integer>{

}

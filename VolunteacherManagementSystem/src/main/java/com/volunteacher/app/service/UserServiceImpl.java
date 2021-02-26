package com.volunteacher.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.User;
import com.volunteacher.app.repository.KidRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	KidRepository kidrepository;

	@Override
	public List<User> userList() {
		return null;
	}

	@Override
	public List<Kid> kidList() {
		Iterable<Kid> kids = kidrepository.findAll();
		return (List<Kid>) kids;	
	}

}

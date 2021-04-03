package com.volunteacher.app.config.SecurityClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.volunteacher.app.model.User;
import com.volunteacher.app.repository.UserRepository;

@Service
@CrossOrigin(origins="http://localhost:4200")  
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userPrincipal = userRepository.findByEmail(username);
		
		if(userPrincipal == null)
			throw new UsernameNotFoundException("Username not found for user: "+ username);
		
		System.out.print(userPrincipal.getType());
		return new UserPrincipal(userPrincipal);
	}

}

package com.volunteacher.app.config.SecurityClasses;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.volunteacher.app.model.User;
import com.volunteacher.app.model.UserType;
import com.volunteacher.app.service.interfaces.UserService;

@Component
@CrossOrigin(origins="http://localhost:4200")  
public class UserPrincipal implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private User user;
	
	@Autowired
	UserService userService;
	
	public UserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		UserType userType = user.getType();
		System.out.println(userType.getType());
		List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
		auths.add(new SimpleGrantedAuthority(userType.getType()));
		return auths;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

package com.shohna.MyReactApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shohna.MyReactApp.Repositories.UserRepository;
import com.shohna.MyReactApp.domain.User;
import com.shohna.MyReactApp.exceptions.UsernameAlreadyExistsException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		
		try {
			newUser.setPassword(bcryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			newUser.setConfirmPassword("");
			return userRepository.save(newUser);
		}catch(Exception e) {
			throw new UsernameAlreadyExistsException("Username " +newUser.getUsername()+" already exists");
		}
		
		
	}

}

/**
 * 
 */
package com.webencyclop.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.roleRepository;
import com.webencyclop.demo.repository.userRepository;

/**
 * @author ejaigsa
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	userRepository userrepo;
	
	@Autowired
	roleRepository rolerepo;
	
	

	@Override
	public void saveUser(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERFIED");
		Role userRole =rolerepo.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userrepo.save(user);
		
		
	}

	@Override
	public boolean isUserAlreadyExist(User user) {
		 boolean isUserAlreadyExists = false;
	     User existingUser = userrepo.findByEmail(user.getEmail());
	     // If user is found in database, then then user already exists.
	     if(existingUser != null){
	         isUserAlreadyExists = true; 
	     }
	     return isUserAlreadyExists;
	}

}

/**
 * 
 */
package com.webencyclop.demo.service;

import org.springframework.stereotype.Service;

import com.webencyclop.demo.model.User;

/**
 * @author ejaigsa
 *
 */
@Service
public interface UserService {
	
	public void saveUser(User user);
	
	public boolean isUserAlreadyExist(User user);

}

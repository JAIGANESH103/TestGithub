/**
 * 
 */
package com.webencyclop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.User;

/**
 * @author ejaigsa
 *
 */
@Repository
public interface userRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

}

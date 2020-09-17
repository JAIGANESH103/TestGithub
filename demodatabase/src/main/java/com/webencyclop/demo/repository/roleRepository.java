/**
 * 
 */
package com.webencyclop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.Role;

/**
 * @author ejaigsa
 *
 */
@Repository
public interface roleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRole(String role);

}

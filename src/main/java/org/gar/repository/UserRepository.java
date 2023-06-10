package org.gar.repository;

import org.gar.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>{
	
	public AppUser findByUsername(String username);

}

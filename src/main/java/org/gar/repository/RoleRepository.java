

package org.gar.repository;

import org.gar.entites.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long>{
	
	public AppRole findByRolename(String rolename);

}

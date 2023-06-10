package org.gar.Interface;

import org.gar.entites.AppRole;
import org.gar.entites.AppUser;

public interface AccountInterface {

    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUse(String username, String roleName);
    public AppUser findUserByUsername(String username);
    
}

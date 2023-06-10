package org.gar.service;

import org.gar.Interface.AccountInterface;
import org.gar.entites.AppRole;
import org.gar.entites.AppUser;
import org.gar.repository.RoleRepository;
import org.gar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements AccountInterface{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser user) {
       String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
       user.setPassword(hashPW);
       return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUse(String username, String roleName) {
       AppRole role = roleRepository.findByRolename(roleName);
       AppUser user = userRepository.findByUsername(username);
       user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}

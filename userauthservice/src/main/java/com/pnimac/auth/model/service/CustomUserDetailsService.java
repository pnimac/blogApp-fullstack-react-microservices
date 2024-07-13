package com.pnimac.auth.model.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pnimac.auth.model.Role;
import com.pnimac.auth.model.User;
import com.pnimac.auth.model.repository.RoleRepository;
import com.pnimac.auth.model.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	 
	 private final String DEFAULT_ROLE = "BLOGUSER";
	 	
	 
	public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User saveNewUser(User user) throws RoleNotFoundException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Optional<Role> optionalRoles = roleRepository.findByRolename(DEFAULT_ROLE);
		if(optionalRoles.isEmpty()) {
			throw new RoleNotFoundException("Default role not found for blog user with username " + user.getUsername());
		}
		Collection<Role> roles = Collections.singletonList(optionalRoles.get());
		user.setRoles(roles);
		return userRepository.saveAndFlush(user);
	}

}

package com.pnimac.auth.model.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pnimac.auth.model.Role;
import com.pnimac.auth.model.User;
import com.pnimac.auth.model.repository.RoleRepository;
import com.pnimac.auth.model.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private final String ROLE_USER = "ROLE_USER";

	public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRolename().toUpperCase()))
				.collect(Collectors.toList());

		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).authorities(authorities).build();

	}

	@Transactional
	public User save(User user) throws RoleNotFoundException {
		user.setEnabled(true);
		Optional<Role> optionalRoles = roleRepository.findByRolename(ROLE_USER);
		if (optionalRoles.isEmpty()) {
			throw new RoleNotFoundException("Default role not found for blog user with username " + user.getUsername());
		}
		Collection<Role> roles = Collections.singletonList(optionalRoles.get());
		user.setRoles(roles);
		return userRepository.saveAndFlush(user);
	}

}

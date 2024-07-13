package com.pnimac.auth.controller;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pnimac.auth.model.User;
import com.pnimac.auth.model.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private final CustomUserDetailsService userService;
	
	public AuthController(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bcryptEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.userService = customUserDetailsService;
	}

	@PostMapping("/signup")
    public String signup(@RequestBody User user) {
        try {
			userService.saveNewUser(user);
	        return "User registered successfully";
		} catch (RoleNotFoundException e) {
	        return "Default role not found for the user.";
		}
    }

    @GetMapping("/user")
    public UserDetails user() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService userService) {
        return username -> {
            User user = (User) userService.loadUserByUsername(username);
            if (user != null) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities("USER")
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }
}	

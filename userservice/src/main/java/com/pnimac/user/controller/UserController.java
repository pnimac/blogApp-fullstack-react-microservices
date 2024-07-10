package com.pnimac.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userservice")
public class UserController {
	
	@GetMapping("/hello")
	private String hello() {
		return "Hello from Userservice";
	}

}

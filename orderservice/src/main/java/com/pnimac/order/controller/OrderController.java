package com.pnimac.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderservice")
public class OrderController {
	
	@GetMapping("/hello")
	private String hello() {
		return "Hello from OrderService";
	}
	

}

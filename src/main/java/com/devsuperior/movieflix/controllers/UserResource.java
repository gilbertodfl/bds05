package com.devsuperior.movieflix.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value = "/users/profile")
public class UserResource {

	
	@Autowired
	private UserService service;
	
	
	@Autowired AuthService authService;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@GetMapping
	public ResponseEntity<UserDTO> findByEmail() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Passei no UserResource e chamei  ==> findByEmail  " + username);
		UserDTO dto = service.findByEmail(username);
		return ResponseEntity.ok().body(dto);
	}

} 

package com.devsuperior.movieflix.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			// a chamada abaixo pega o usuário que já foi reconhecido pelo spring-secutiry
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			logger.info("Autenticado ---->  " + username);
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			logger.warn("Autenticação falhou ---->  " ) ;
			throw new UnauthorizedException("Invalid user");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		logger.info("Usuario id " + userId);
		User user = authenticated();
		logger.warn("AuthService: validateSelfOrAdmin  " ) ;
//		if (!user.getId().equals(userId) && ( ! user.hasHole("ROLE_MEMBER") || ! user.hasHole("ROLE_VISITOR") )) {
/*		if (!user.getId().equals(userId) ) {
			logger.info("Usuario sem permissão ---->  " + user);

			logger.info("Usuario sem permissão - access denied");
			throw new ForbiddenException("Access denied");
		}
*/
	}
}

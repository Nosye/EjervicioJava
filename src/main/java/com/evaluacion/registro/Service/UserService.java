package com.evaluacion.registro.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.evaluacion.registro.Entity.User;
import com.evaluacion.registro.Repository.UserRepository;
import com.evaluacion.registro.Utils.TokenUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private TokenUtils tokenUtils;
													

	/**
	 * Exists user email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<User> existsUserEmail(String email)  {
		
		return userRepository.findByEmail(email);
	}
	
	/**
	 * Validate email format.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean validateEmailFormat(String email) {
		String emailRegex = enviroment.getProperty("regex.email");
		return email.matches(emailRegex);
	}
	
	/**
	 * Validate password format.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean validatePasswordFormat(String password) {
		String passwordRegex = enviroment.getProperty("regex.password");
		return password.matches(passwordRegex);
	}
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User saveUser(User user) {
		
		String token = tokenUtils.generateToken(user);
		user.setToken(token);
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setActive(true);
		
		return userRepository.save(user);
	}
	
}

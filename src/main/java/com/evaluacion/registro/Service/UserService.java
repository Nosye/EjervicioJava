package com.evaluacion.registro.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.registro.Entity.User;
import com.evaluacion.registro.Repository.UserRepository;
import com.evaluacion.registro.Utils.Utils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final String EMAIL_REGULAR = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	private static final String PASSWORD_REGULAR = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).+$";
													

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
		Pattern pattern = Pattern.compile(EMAIL_REGULAR);
		return pattern.matcher(email).matches();
	}
	
	/**
	 * Validate password format.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean validatePasswordFormat(String password) {
		Pattern pattern = Pattern.compile(PASSWORD_REGULAR);
		return pattern.matcher(password).matches();
	}
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User saveUser(User user) {
		
		user.setToken(Utils.generateId());
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setActive(true);
		
		return userRepository.save(user);
	}
	
}

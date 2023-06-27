package com.evaluacion.registro.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.registro.Entity.User;
import com.evaluacion.registro.Service.UserService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
public class UserController {
	
	@Autowired
	private UserService userService; 

	/**
	 * Register user.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@PostMapping("/registrar")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		
		Optional<User> existingEmail = userService.existsUserEmail(user.getEmail());
		if(existingEmail.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage("El correo ya está registrado"));
		}
		
		if(!userService.validateEmailFormat(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage("Ingrese correo válido"));
		}

		if(!userService.validatePasswordFormat(user.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage("La contraseña debe contener lo siguiente: Una Mayuscula, letras minúsculas, y dos numeros"));
		}
		
		User createdUser = userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		
	}
	
	/**
	 * Error message.
	 *
	 * @param mensaje the mensaje
	 * @return the map
	 */
	private Map<String, String> errorMessage(String mensaje){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("mensaje", mensaje);
		return errorMap;
	}
	
}

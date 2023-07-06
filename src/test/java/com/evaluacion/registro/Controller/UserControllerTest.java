package com.evaluacion.registro.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.evaluacion.registro.Entity.User;
import com.evaluacion.registro.Service.UserService;

class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void testRegisterUserSucess() {
		User user = new User();
		user.setEmail("test@test.cl");
		user.setPassword("Test12");
		
		when(userService.existsUserEmail(user.getEmail())).thenReturn(Optional.empty());
		when(userService.validateEmailFormat(user.getEmail())).thenReturn(true);
		when(userService.validatePasswordFormat(user.getPassword())).thenReturn(true);
		when(userService.saveUser(user)).thenReturn(user);
		
		ResponseEntity<?> responseEntity = userController.registerUser(user);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(user, responseEntity.getBody());
		
		verify(userService, times(1)).existsUserEmail(user.getEmail());
		verify(userService, times(1)).validateEmailFormat(user.getEmail());
		verify(userService, times(1)).validatePasswordFormat(user.getPassword());
		verify(userService, times(1)).saveUser(user);
	}

}

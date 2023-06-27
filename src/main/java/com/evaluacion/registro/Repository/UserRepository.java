package com.evaluacion.registro.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.evaluacion.registro.Entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<User> findByEmail(String email);

}

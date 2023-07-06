package com.evaluacion.registro.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evaluacion.registro.Entity.User;

@Component
public class TokenUtils {

	private static final long EXPIRATION_TIME = 24*60*60*1000;

	@Autowired
	private SecretKey secretKey;
	
	
	public String generateToken(User user) {
		Date fecha = new Date();
		Date expiration = new Date(fecha.getTime() + EXPIRATION_TIME);
		
		return Jwts.builder()
				.setSubject(user.getEmail())
				.setIssuedAt(expiration)
				.setExpiration(expiration)
				.signWith(secretKey)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
}

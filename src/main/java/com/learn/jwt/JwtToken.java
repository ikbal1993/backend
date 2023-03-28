package com.learn.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.learn.model.Login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expire}")
	private long expireTime;
	
	public String generateToken(Authentication auth) {
		String name = auth.getName();
		System.out.println("*************"+name+"***************");
		Date current=new Date();
		Date expireDate = new Date(current.getTime()+expireTime);
		
		 String token = Jwts.builder()
					.setSubject(name).setIssuedAt(current).setExpiration(expireDate)
					.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		 return token;
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUserDetailsFromJwt(String token) {
		
		Claims body = Jwts.parser()
		.setSigningKey(jwtSecret)
		.parseClaimsJws(token).getBody();
		
		return body.getSubject();
	}

}

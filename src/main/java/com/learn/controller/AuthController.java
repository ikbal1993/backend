package com.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jwt.JwtResponse;
import com.learn.jwt.JwtToken;
import com.learn.model.Login;

@RestController
@CrossOrigin("http://127.0.0.1:5501/index.html")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authentication;
	@Autowired
	private JwtToken token;
//---> http://localhost:8080/auth/token
	
//	@PostMapping("/auth/token")
	@RequestMapping(value="/auth/token",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> generateToken(@RequestBody Login dto){
		System.out.println("**********test1233*******");
		Authentication authenticate1 = null;
		try {

			 authenticate1 = authentication.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
					);
			
			SecurityContextHolder.getContext().setAuthentication(authenticate1);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(new JwtResponse("User email and Password does not exist"),HttpStatus.BAD_GATEWAY);
		}

		String generateToken = token.generateToken(authenticate1);
		
		
		
		return new ResponseEntity<>(new JwtResponse(generateToken),HttpStatus.OK);
	}
}

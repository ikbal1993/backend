package com.learn.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learn.security.UserDetailsServiceImpl;

@Component
public class JwtFilter extends OncePerRequestFilter{
	@Autowired
	private JwtToken jwttoken;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token=null;
		
		String bearerToken = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer")) {
			token = bearerToken.substring(7,bearerToken.length());
		}
		
		if(token!=null) {
			
			if(jwttoken.validateToken(token)) {
				String username = jwttoken.getUserDetailsFromJwt(token);
				UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			  }
		}
		
		filterChain.doFilter(request,response);
	}

}

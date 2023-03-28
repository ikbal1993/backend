package com.learn.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learn.model.Student;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	private Student student;
	
	public UserDetailsImpl(Student student) {
		super();
		this.student = student;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<SimpleGrantedAuthority> auth = new HashSet<>();
		auth.add(new SimpleGrantedAuthority(student.getRole()));
		
		return auth;
	}

	@Override
	public String getPassword() {
		String pw = null;
		try {
			pw = student.getPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pw;
	}

	@Override
	public String getUsername() {
		String email = null;
		try {
			email = student.getEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

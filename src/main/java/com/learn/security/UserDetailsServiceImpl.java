package com.learn.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learn.global_Ex.UserNotFoundException;
import com.learn.model.Student;
import com.learn.repository.StudentRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private StudentRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Student  s = null;
		 try {
			 
			  s = repo.findByEmail(email).get();
			 
		} catch (UserNotFoundException e) {
            throw new UserNotFoundException("Does not Exist your credentials");
		}catch (Exception e) {
			e.printStackTrace();
		}
		 UserDetailsImpl detailsImpl = new UserDetailsImpl(s);
		 
		return detailsImpl;
	}

}

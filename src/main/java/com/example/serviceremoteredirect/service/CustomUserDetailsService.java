package com.example.serviceremoteredirect.service;

import com.example.serviceremoteredirect.entity.User;
import com.example.serviceremoteredirect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;





	@Service("userDetailsService")
	public class CustomUserDetailsService implements UserDetailsService {

		@Autowired
		private UserRepository userRepo;




		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

			System.out.println(email + " EMAIL jee");
			User user = userRepo.findByEmail(email);

			if(user == null) {
				throw new UsernameNotFoundException("Korisnik nije pronadjen");

			}
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

			System.out.println("OVO JE KORISNIK " + user.getEmail());


			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
		}



	}
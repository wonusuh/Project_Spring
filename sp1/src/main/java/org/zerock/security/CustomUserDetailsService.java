package org.zerock.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("--------------------------loadUserByUsername----------------------------------");

		UserDetails user = User.builder() // ~User 클래스를 이용해 구현
				.username(username) // id
				.password("$2a$10$F9QMtSuvTz5yG7iQHRQacume6nbdHGOvvcx5kaw03x.cZGsqC64iK") // pw
				.roles("USER") // ROLE_USER
				.build();

		return user;
	}
}

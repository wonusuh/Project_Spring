package org.zerock.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.dto.AccountDTO;
import org.zerock.dto.AccountRole;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("--------------------------loadUserByUsername----------------------------------{}", username);
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUid(username);
		// 패스워드는 1111 을 BCrypt 로 만든 문자열 사용
		accountDTO.setUpw("$2a$10$yRt5uQ6qgGvTk9TtH7B0z.ZZ8p9V.k8hGhB4Y1sMztfFgB8rC7E6W");
		accountDTO.addRole(AccountRole.USER);
		accountDTO.addRole(AccountRole.MANAGER);
		return accountDTO;
	}
}

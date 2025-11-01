package org.zerock.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AccountDTO implements UserDetails {
	private String uid;
	private String upw;
	private String uname;
	private String email;
	private List<AccountRole> roleNames;

	public void addRole(AccountRole role) {
		// 초기화
		if (roleNames == null) {
			roleNames = new ArrayList<>();
		}

		roleNames.add(role);
	}

	public void clearRole() {
		roleNames.clear();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//
		if (roleNames == null || roleNames.size() == 0) {
			return List.of();
		}

		return roleNames.stream().map((accountRole) ->
		{
			new SimpleGrantedAuthority("ROLE_" + accountRole.name());
			
		}
				).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}

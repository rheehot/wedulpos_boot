package com.wedul.wedulpos.user.dto;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author wedul
 *
 */
public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	UserDto user;

	public MyAuthenticaion(String id, String password, List<GrantedAuthority> grantedAuthorityList, UserDto user) {
		super(id, password, grantedAuthorityList);
		this.user = user;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}

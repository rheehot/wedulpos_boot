package com.wedul.wedulpos.user.dto;

import org.apache.ibatis.type.Alias;

import com.wedul.common.dto.CommonDto;
import com.wedul.common.util.HashUtil;

/**
 * User정보 Dto 
 * 
 * @author wedul
 * @date 2017. 11. 4.
 * @name UserDto
 */
@Alias("UserDto")
public class UserDto extends CommonDto {
	private String email;
	private String password;
	private boolean isadmin;
	
	public UserDto() {}
	
	public UserDto(String email) {
		this.email = email;
	}
	
	public UserDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserDto(String email, String password, boolean isadmin) {
		this.email = email;
		this.password = password;
		this.isadmin = isadmin;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEcPassword() {
		return HashUtil.sha256(this.password);
	}
	
}

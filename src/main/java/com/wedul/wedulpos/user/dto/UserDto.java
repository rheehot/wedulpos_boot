package com.wedul.wedulpos.user.dto;

import org.apache.ibatis.type.Alias;

import com.wedul.common.dto.CommonDto;
import com.wedul.common.util.HashUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User정보 Dto 
 * 
 * @author wedul
 * @date 2017. 11. 4.
 * @name UserDto
 */
@Alias("UserDto")
@Data
@EqualsAndHashCode(callSuper=false)
public class UserDto extends CommonDto {
	private String nickname;
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
	
	public UserDto(String email, String password, String nickname, boolean isadmin) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.isadmin = isadmin;
	}
	
	public String getEcPassword() {
		return HashUtil.sha256(this.password);
	}
	
}

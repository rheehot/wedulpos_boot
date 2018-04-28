package com.wedul.wedulpos.user.dto;

import org.apache.ibatis.type.Alias;

/**
 * 사용자 OTP DTO
 * 
 * @author wedul
 *
 */
@Alias("UserOtpDto")
public class UserOtpDto {
	private EnumOtpType type;
	private String userid;
	private String otp;
	
	public UserOtpDto() {}
	
	public UserOtpDto(EnumOtpType type, String userid, String otp) {
		this.type = type;
		this.userid = userid;
		this.otp = otp;
	}

	public EnumOtpType getType() {
		return type;
	}

	public void setType(EnumOtpType type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}

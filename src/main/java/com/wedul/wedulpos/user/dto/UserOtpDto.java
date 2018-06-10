package com.wedul.wedulpos.user.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 OTP DTO
 * 
 * @author wedul
 *
 */
@Alias("UserOtpDto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOtpDto {
	private EnumOtpType type;
	private String userid;
	private String otp;
}

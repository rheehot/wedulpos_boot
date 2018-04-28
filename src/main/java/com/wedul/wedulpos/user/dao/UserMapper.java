package com.wedul.wedulpos.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wedul.wedulpos.user.dto.UserDto;
import com.wedul.wedulpos.user.dto.UserOtpDto;

@Mapper
public interface UserMapper {

	/**
	 * 사용자 조회
	 * 
	 * @param user
	 * @return
	 */
	public UserDto selectUser(UserDto user);
	
	/**
	 * 사용자 입력
	 * 
	 * @param user
	 */
	public int insertUser(UserDto user);
	
	/**
	 * 사용자 OTP 입력
	 * 
	 * @param dto
	 */
	public void insertUserOtp(UserOtpDto dto);
	
	/**
	 * 사용자 OTP 제거
	 * 
	 * @param dto
	 * @return
	 */
	public int deleteUserOtp(UserOtpDto dto);
	
	/**
	 * 임시 비밀번호 업데이트
	 * 
	 * @param dto
	 * @return
	 */
	public int updateTempPw(UserDto dto);

}

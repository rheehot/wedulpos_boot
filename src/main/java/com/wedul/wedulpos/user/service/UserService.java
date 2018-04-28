package com.wedul.wedulpos.user.service;

import com.wedul.wedulpos.user.dto.UserDto;

/**
 * @author wedul
 *
 */
public interface UserService {
	
	/**
	 * User 정보 찾기
	 * 
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	UserDto selectUser(UserDto user);
	
	/**
	 * User 정보 입력하기
	 * 
	 * @param user
	 * @throws Exception
	 */
	boolean insertUser(UserDto user) throws Exception;
	
	/**
	 * Email 인증
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	String checkEmail(String email) throws Exception;
	
	/**
	 * 이메일 인증번호 확인 
	 * 
	 * @param otp
	 * @return
	 * @throws Exception
	 */
	boolean checkCert(String otp) throws Exception;
	
	/**
	 * 임시 비밀번호 발급
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	String createTempPassword(String email) throws Exception;
}

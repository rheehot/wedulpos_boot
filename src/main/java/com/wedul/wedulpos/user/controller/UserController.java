package com.wedul.wedulpos.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wedul.wedulpos.user.dto.UserDto;
import com.wedul.wedulpos.user.service.UserService;

/**
 * User관련 컨트롤러
 * 
 * @author wedul
 *
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
	
	@Autowired
	UserService userService;

	/**
	 * 회원가입
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/join")
	public ResponseEntity<?> join(
			@RequestParam String email,
			@RequestParam String password) throws Exception {
		return ResponseEntity.ok(userService.insertUser(new UserDto(email, password, false)));
	}
	
	/**
	 * 이메일 인증 요청 
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/email")
	public ResponseEntity<?> checkEmail(String email) throws Exception {
		return ResponseEntity.ok(userService.checkEmail(email));
	}
	
	/**
	 * 임시 비밀번호 발급
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/send/temppw")
	public ResponseEntity<?> createTempPw(String email) throws Exception {
		return ResponseEntity.ok(userService.createTempPassword(email));
	}
	
	/**
	 * 인증번호 확인 
	 * 
	 * @param otp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cert/check")
	public ResponseEntity<?> checkCert(String otp) throws Exception {
		return ResponseEntity.ok(userService.checkCert(otp));
	}
	
	/**
	 * 패스워드 초기화 
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/password")
	public ResponseEntity<?> changePassword(
			@RequestParam String email,
			@RequestParam String password) throws Exception {
		return ResponseEntity.ok(userService.changePassword(email, password));
	}
}

package com.wedul.wedulpos.user.serviceImpl;

import com.google.common.collect.Lists;
import com.wedul.common.config.SpringSecurityConfig;
import com.wedul.common.dto.ResultDto;
import com.wedul.common.util.Constant;
import com.wedul.wedulpos.user.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.wedul.common.util.CommonUtils;
import com.wedul.common.util.MailUtil;
import com.wedul.common.util.MessageBundleUtil;
import com.wedul.wedulpos.user.dao.UserMapper;
import com.wedul.wedulpos.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 사용자 관련 작업을 진행하는 서비스
 * 
 * @author wedul
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserMapper userDao;

	@Autowired
	MessageBundleUtil messageBundleUtil;

	@Autowired
	MailUtil mailUtil;

	@Autowired
	AuthProvider authProvider;

	@Override
	public UserDto selectUser(UserDto user) {
		return userDao.selectUser(user);
	}

	@Override
	public boolean insertUser(UserDto user) throws Exception {
		return userDao.insertUser(user) > 0;
	}

	private UserDto insertSnsUser(UserDto reqDto) throws Exception {
		UserDto userDto = selectUser(reqDto);

		if (null == userDto) {
			if (insertUser(reqDto)) {
				return reqDto;
			} else {
				return null;
			}
		}

		return userDto;
	}

	@Override
	public ResultDto facebookLogin(HttpServletRequest request, UserDto reqDto) throws Exception {
		UserDto userDto = insertSnsUser(reqDto);

		if (null == userDto) {
			return ResultDto.fail("등록된 사용자가 없습니다.");
		}

		// 인증 토큰 생성
		MyAuthenticaion token = new MyAuthenticaion(userDto.getSnsId(), "", Arrays.asList(new SimpleGrantedAuthority(Constant.ROLE_TYPE.ROLE_USER.toString())), userDto, EnumLoginType.FACE_BOOK);
		token.setDetails(new WebAuthenticationDetails(request));
		authProvider.authenticate(token);

		// Security Context에 인증 토큰 셋팅
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(token);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

		return ResultDto.success();
	}

	@Override
	public String checkEmail(String email) throws Exception {
		try {
			// 이미 사용중인 이메일인지 확인
			if (null != userDao.selectUser(new UserDto(email))) {
				return messageBundleUtil.getMessage("user.join.message.alreadyEmail");
			}

			// otp 메일 발송
			String otp = CommonUtils.generateNumber(6);
			userDao.insertUserOtp(new UserOtpDto(EnumOtpType.JOIN_OTP, email, otp));
			mailUtil.sendMailWithJava(email, messageBundleUtil.getMessage("user.join.message.email_subject"),
					messageBundleUtil.getMessageWithParam("user.join.message.email_content", new String[] { otp }));
			return "";

		} catch (Exception ex) {
			logger.error("send error", ex);
			return messageBundleUtil.getMessage("user.join.message.failEmail");
		}
	}

	@Override
	public String checkNickname(String nickname) {
		try {
			// 이미 사용중인 이메일인지 확인
			UserDto user = new UserDto();
			user.setNickname(nickname);
			if (null != userDao.selectUser(user)) {
				return messageBundleUtil.getMessage("user.join.message.alreadyNickName");
			}

			return "";

		} catch (Exception ex) {
			logger.error("send error", ex);
			return messageBundleUtil.getMessage("user.join.message.failNickname");
		}
	}

	@Override
	public boolean checkCert(String otp) throws Exception {
		if (CommonUtils.int2boolean(userDao.deleteUserOtp(new UserOtpDto(EnumOtpType.JOIN_OTP, null, otp)))) {
			return true;
		}

		return false;
	}

	@Override
	public String createTempPassword(String email) throws Exception {
		try {
			// 사용자 체크
			UserDto user = userDao.selectUser(new UserDto(email));
			if (null == user) {
				return messageBundleUtil.getMessage("user.find.message.non_exist_email");
			}

			String tempPw = CommonUtils.createTempPassword();
			user.setPassword(tempPw);
			if (userDao.updateTempPw(user) == 0) {
				return messageBundleUtil.getMessage("user.find.message.fail_temp_pw");
			}
			mailUtil.sendMailWithJava(email, messageBundleUtil.getMessage("user.find.message.subject"),
					messageBundleUtil.getMessageWithParam("user.find.message.content", new String[] { tempPw }));
			return "";
		} catch (Exception ex) {
			logger.error("send error", ex);
			return messageBundleUtil.getMessage("user.find.message.fail_temp_pw");
		}

	}

	@Override
	public String changePassword(String email, String password) {
		// 사용자 체크
		UserDto user = userDao.selectUser(new UserDto(email));
		if (null == user) {
			return messageBundleUtil.getMessage("user.find.message.non_exist_email");
		}
		
		user.setPassword(password);
		userDao.updateTempPw(user);
		
		return "";
	}

}

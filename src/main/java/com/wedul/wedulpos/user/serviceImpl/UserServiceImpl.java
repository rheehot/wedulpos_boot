package com.wedul.wedulpos.user.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedul.common.util.CommonUtils;
import com.wedul.common.util.MailUtil;
import com.wedul.common.util.MessageBundleUtil;
import com.wedul.wedulpos.user.dao.UserMapper;
import com.wedul.wedulpos.user.dto.EnumOtpType;
import com.wedul.wedulpos.user.dto.UserDto;
import com.wedul.wedulpos.user.dto.UserOtpDto;
import com.wedul.wedulpos.user.service.UserService;

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

	@Override
	public UserDto selectUser(UserDto user) {
		return userDao.selectUser(user);
	}

	@Override
	public boolean insertUser(UserDto user) throws Exception {
		return userDao.insertUser(user) > 0;
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

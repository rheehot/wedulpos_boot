package com.wedul.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wedul.wedulpos.user.dto.EnumLoginType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wedul.wedulpos.user.dto.MyAuthenticaion;
import com.wedul.wedulpos.user.dto.UserDto;

/**
 * session 관련 유틸 클래스 
 * 
 * @author wedul
 * @date 2017. 11. 4.
 * @name SessionUtil
 */
/**
 * @author wedul
 *
 */
/**
 * @author wedul
 *
 */
public enum SessionUtil {
	INSTANCE;

	/**
	 * 로그인 User 설정 
	 * 
	 * @authoer: wedul
	 * @date : 2017. 11. 4.
	 * @file : SessionUtil.java
	 * @return : void
	 */
	public static void login(HttpServletRequest request, UserDto dto) {
		request.getSession().setAttribute(Constant.USER, dto);;
	}
	
	/**
	 * 로그아웃 처리
	 * 
	 * @authoer: wedul
	 * @date : 2017. 11. 4.
	 * @file : SessionUtil.java
	 * @return : void
	 */
	public static void logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(Constant.LOGIN, null);
		session.invalidate();
	}
	
	/**
	 * 로그인 여부 확인 
	 * 
	 * @authoer: wedul
	 * @date : 2017. 11. 4.
	 * @file : SessionUtil.java
	 * @return : boolean
	 */
	public static boolean isLogined(HttpServletRequest request) {
		return request.getSession().getAttribute(Constant.LOGIN) != null;
	}
	
	/**
	 * 현재 로그인 사용자 정보 
	 * 
	 * @authoer: wedul
	 * @date : 2017. 11. 4.
	 * @file : SessionUtil.java
	 * @return : UserDto
	 */
	public static UserDto getLoginUserDto(HttpServletRequest req) {
		return (UserDto) req.getAttribute(Constant.LOGIN);
	}
	
	/**
	 * 현재 로그인된 유저
	 * 
	 * @return
	 */
	public static UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticaion)
            return ((MyAuthenticaion) authentication).getUser();
        return null;
    }

	/**
	 * Gets login type.
	 *
	 * @return the login type
	 */
	public static EnumLoginType getLoginType() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof MyAuthenticaion)
			return ((MyAuthenticaion) authentication).getLoginType();
		return null;
	}

    /**
     * 현재 로그인된 유저 설정
     * 
     * @param user
     */
    public static void setCurrentUser(UserDto user) {
        ((MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
    }
	
}

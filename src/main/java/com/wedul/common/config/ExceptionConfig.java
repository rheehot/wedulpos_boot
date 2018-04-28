package com.wedul.common.config;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedul.common.enums.EnumResultType;

/**
 * 에러 유형을 나타내는 Config
 * 
 * @author wedul
 * @Date 2017. 07. 09
 */
@ControllerAdvice
public class ExceptionConfig {

	@ExceptionHandler({Exception.class })
	@ResponseBody
	public HashMap<String, Object> errorHandler(Exception ex) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", EnumResultType.FAIL);
		map.put("errMsg", makeErrorCode(ex));

		return map;
	}

	/**
	 * Error code 만들기
	 *
	 * @date 2017. 7. 9.
	 * @author wedul
	 * @return String
	 */
	private String makeErrorCode(Exception ex) {
		StackTraceElement[] ste = ex.getStackTrace();
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] arrayOfStackTraceElement1;
		int j = (arrayOfStackTraceElement1 = ste).length;
		for (int i = 0; i < j; i++) {
			StackTraceElement el = arrayOfStackTraceElement1[i];
			String className = el.getClassName();
			if (className.startsWith("com.wedul.wedulpos")) {
				sb.append(className.substring(className.lastIndexOf(".") + 1).toUpperCase()).append("[");
				sb.append(el.getLineNumber()).append("]");
				break;
			}
		}
		if (StringUtils.isBlank(sb.toString())) {
			return ex.getStackTrace()[0].getClassName();
		}
		return sb.toString();
	}
}

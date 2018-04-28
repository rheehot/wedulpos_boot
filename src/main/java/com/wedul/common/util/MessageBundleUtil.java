package com.wedul.common.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * 메시지 관련 유틸리티 
 *
 * @author jeongcheol
 * @date 2017. 6. 17.
 */
@Component
public class MessageBundleUtil {
	 private MessageSourceAccessor accessor = null;
	 
	 /**
	  * Get the Message.
	  * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		 return accessor.getMessage(key);
	 }
	 
	/**
	 * Gets the message with param.
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public String getMessageWithParam(String key, String[] params) {
        return accessor.getMessage(key, params);
    }
	
    /**
     * Sets the message source accessor.
     *
     * @param msAcc the new message source accessor
     */
    public void setMessageSourceAccessor(MessageSourceAccessor accessor) {
        this.accessor = accessor;
    }
}

package com.wedul.wedulpos.userService;

import org.junit.Test;

import com.wedul.common.util.AES256Cipher;

public class UserServiceTest {
	
	@Test
	public void getAesString() {
		System.out.println(AES256Cipher.getInstance().AES_Encode("GomChul5848!@"));;
	}

}

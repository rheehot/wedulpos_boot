package com.wedul.wedulpos.variables.serviceI;

public interface VariablesServiceI {

	/**
	 * 설정값에서 String value 찾기
	 * 
	 * @param key
	 * @return
	 */
	String getStringValue(String key);
	
	/**
	 * 설정값에서 int 값 찾기
	 * 
	 * @param key
	 * @return
	 */
	int getIntegerValue(String key);
	
}

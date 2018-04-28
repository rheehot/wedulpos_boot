package com.wedul.common.util.rest;

import feign.Param;
import feign.RequestLine;

/**
 * Http Request Client
 * 
 * @author wedul
 *
 */
public interface RestClientI {

	@RequestLine("POST /password/find")
	String findPassword();
	
	@RequestLine("GET /weather?id={id}&APPID=7a4b48354c62cb247d7276be5c87766d")
	String selectWeather(@Param("id") String id);
	
}

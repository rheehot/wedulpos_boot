package com.wedul.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedul.common.service.CommonService;

/**
 * 공통으로 사용하는 영역에 대한 컨트로러
 * 
 * @author wedul
 *
 */
@RestController
public class CommonController {
	
	@Autowired
	CommonService commonService;
	
	/**
	 * 날씨 가져오기
	 * 
	 * @return
	 */
	@PostMapping(value="/weather")
	public ResponseEntity<?> getWeather() {
		return ResponseEntity.ok(commonService.getWeatherData());
	}

}

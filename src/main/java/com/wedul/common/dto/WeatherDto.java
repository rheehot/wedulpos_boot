package com.wedul.common.dto;

import org.apache.ibatis.type.Alias;

/**
 * Wheather Dto
 * 
 * @author wedul
 *
 */
@Alias("WetherDto")
public class WeatherDto {
	private String id;
	private String main;
	private String icon;
	private String temp;
	
	public WeatherDto(String id, String icon, String main, String temp) {
		this.id = id;
		this.icon = icon;
		this.temp = temp;
		this.main = main;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}
	
}

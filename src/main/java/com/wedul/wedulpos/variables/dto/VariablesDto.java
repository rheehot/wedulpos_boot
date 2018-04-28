package com.wedul.wedulpos.variables.dto;

import org.apache.ibatis.type.Alias;

/**
 * 관리자 설정값을 담는 DTO
 * 
 * @author wedul
 *
 */
@Alias("VariablesDto")
public class VariablesDto {
	private String name;
	private String value;
	
	public VariablesDto() { }
	
	public VariablesDto(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

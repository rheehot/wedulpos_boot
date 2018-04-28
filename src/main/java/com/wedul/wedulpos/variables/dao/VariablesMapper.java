package com.wedul.wedulpos.variables.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wedul.wedulpos.variables.dto.VariablesDto;

/**
 * 관리자 설정 DAO 클래스
 * 
 * @author wedul
 *
 */

@Mapper
public interface VariablesMapper {

	/**
	 * 관리자 데이터 입력
	 * 
	 * @param dto
	 */
	public void insertVariables(VariablesDto dto);
	
	/**
	 * 관리자 데이터 수정
	 * 
	 * @param dto
	 */
	public void updateVariables(VariablesDto dto);
	
	/**
	 * 관리자 환경설정 가져오기
	 * 
	 * @return
	 */
	public List<VariablesDto> selectVariablesList();

}

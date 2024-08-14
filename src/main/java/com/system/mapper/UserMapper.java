package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.system.dto.UserDto;

@Mapper
public interface UserMapper {
	// 삭제 안된 회원 리스트 가져오기
	public List<UserDto> findAll() throws Exception;
	public UserDto findById(int id);
	// 회원 추가하기
	public void insert(UserDto user);
	// 회원 삭제하기
	public void softDeleteById(int id);
}

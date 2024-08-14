package com.system.service;

import java.util.List;

import com.system.dto.UserDto;

public interface UserService {
	public List<UserDto> getActiveUserList() throws Exception;
	public UserDto findById(int id) throws Exception; 
	public void addUser(UserDto user) throws Exception;
	public void deleteUser(int id) throws Exception;
}

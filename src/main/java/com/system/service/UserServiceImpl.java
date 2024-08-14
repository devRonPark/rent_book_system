package com.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.UserDto;
import com.system.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper um;

	@Override
	public List<UserDto> getActiveUserList() throws Exception {
		return um.findAll();
	}

	@Override
	public void addUser(UserDto user) throws Exception {
		um.insert(user);
	}

	@Override
	public void deleteUser(int id) throws Exception {
		UserDto validUser = findById(id);
		if (validUser == null) {
			throw new Exception("Invalid id");
		}
		
		um.softDeleteById(id);
	}

	@Override
	public UserDto findById(int id) throws Exception {
		return um.findById(id);
	}

}

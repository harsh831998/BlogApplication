package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto User);
	UserDto updateUser(UserDto User,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
	UserDto registerNewUser(UserDto user);
}

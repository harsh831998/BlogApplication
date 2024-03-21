package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.RoleRepo;
import com.blog.repositories.userRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private userRepo userRepos;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto UserDto) {
		User user=userDtoToUser(UserDto);
		User savedUser=userRepos.save(user);
		return userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto UserDto, Integer userId) {
		
		User user=userRepos.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setName(UserDto.getName());
		user.setEmail(UserDto.getEmail());
		user.setPassword(UserDto.getPassword());
		user.setAbout(UserDto.getAbout());
		
		User updatedUser=userRepos.save(user);
		
		return userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=userRepos.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		return userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> allUser=userRepos.findAll();
		
		List<UserDto> allUserDto = allUser.stream().map(user->userToUserDto(user)).collect(Collectors.toList());
		
		return allUserDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=userRepos.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		userRepos.delete(user);
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}
	
	public User userDtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepos.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}
}

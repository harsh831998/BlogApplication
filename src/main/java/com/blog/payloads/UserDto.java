package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4,message="Name length must be greater than 3 characters")
	private String name;
	
	@Email(message="Email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=8,message="Password length must be greater than 2 and less than 8")
	private String password;
	
	@NotEmpty
	private String about;
}

package com.blog.payloads;

import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private String postTitle;
	private String postDescription;
	private String imageName;
	
	private Date postDate;
	private CategoryDto category;
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
}

package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

private int categoryId;
	
	@NotEmpty
	@Size(min = 4,message="Name length must be greater than 3 characters")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=10)
	private String categoryDescription;
}

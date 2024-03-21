package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto lCategoryDto);
	CategoryDto updateCategory(CategoryDto lCategoryDto,Integer catId);
	CategoryDto getCategoryById(Integer catId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(Integer catId);
}

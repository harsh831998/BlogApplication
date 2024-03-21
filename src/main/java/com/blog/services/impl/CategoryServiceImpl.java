package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo lCategoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto lCategoryDto) {
		Category Cat = this.modelMapper.map(lCategoryDto, Category.class);
		Category savedCat = lCategoryRepo.save(Cat);
		return this.modelMapper.map(savedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto lCategoryDto, Integer catId) {
		Category cat = lCategoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));

		cat.setCategoryTitle(lCategoryDto.getCategoryTitle());
		cat.setCategoryDescription(lCategoryDto.getCategoryDescription());
		Category updatedCategory = lCategoryRepo.save(cat);

		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer catId) {

		Category cat = lCategoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		return this.modelMapper.map(cat, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCat = lCategoryRepo.findAll();

		List<CategoryDto> allCatDto = allCat.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return allCatDto;
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category cat = lCategoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		lCategoryRepo.delete(cat);
	}

}

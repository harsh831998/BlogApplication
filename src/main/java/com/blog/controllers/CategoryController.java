package com.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {

	@Autowired
	private CategoryService lCategoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto CategoryDto){
		
		CategoryDto createdCat=lCategoryService.createCategory(CategoryDto);
		return new ResponseEntity<>(createdCat,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto CategoryDto,@PathVariable("catId") Integer catId){
		
		CategoryDto updatedCat=lCategoryService.updateCategory(CategoryDto, catId);
		return ResponseEntity.ok(updatedCat);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer catId){
		lCategoryService.deleteCategory(catId);
		return ResponseEntity.ok(Map.of("message","Category Deleted successfully"));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(lCategoryService.getAllCategory());
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId){
		return ResponseEntity.ok(lCategoryService.getCategoryById(catId));
	}
}

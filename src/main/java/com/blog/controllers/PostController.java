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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.AppConstants;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.PageResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService lPostService;
	
	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer catId){
		
		PostDto createdpost=lPostService.createPost(postDto,userId,catId);
		return new ResponseEntity<>(createdpost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId){
		
		List<PostDto> posts=lPostService.getPostByUserId(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>>  getPostByCategoryId(@PathVariable Integer catId){
		
		List<PostDto> posts=lPostService.getPostByCategoryId(catId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto>  getPostById(@PathVariable Integer postId){
		
		PostDto posts=lPostService.getPostById(postId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<PageResponse>  getAllPosts(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required = false) String sortDir
			){
		PageResponse posts=lPostService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updateCategory(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
		
		PostDto updatedPost=lPostService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer postId){
		lPostService.deletePost(postId);
		return ResponseEntity.ok(Map.of("message","Post Deleted successfully"));
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable String keyword){
		List<PostDto> allposts=lPostService.searchPostByKeyWord(keyword);
		return new ResponseEntity<>(allposts,HttpStatus.OK);
	}
}

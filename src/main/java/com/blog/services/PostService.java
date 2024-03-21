package com.blog.services;

import java.util.List;

import com.blog.payloads.PageResponse;
import com.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer catId);
	PostDto updatePost(PostDto postDto,Integer postId);
	PostDto getPostById(Integer postId);
	void deletePost(Integer postId);
	PageResponse getAllPost(Integer pageNumber , Integer pageSize, String sortBy , String sortDir);
	List<PostDto> getPostByCategoryId(Integer catId);
	List<PostDto> getPostByUserId(Integer userId);
	
	List<PostDto> searchPostByKeyWord(String Keyword);
	
	
}

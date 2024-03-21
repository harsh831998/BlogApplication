package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PageResponse;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.userRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo lPostRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo lCategoryRepo;
	
	@Autowired
	private userRepo lUserRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId) {
	
		Category cat = lCategoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
        
		User user=lUserRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setPostDate(new Date());
		
		post.setCategory(cat);
		post.setUser(user);
		
		Post savedPost=lPostRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = lPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		post.setPostTitle(postDto.getPostTitle());
		post.setPostDescription(postDto.getPostDescription());
		post.setImageName(postDto.getImageName());
		
		Post savedPost=lPostRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = lPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = lPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		lPostRepo.delete(post);
	}

	@Override
	public PageResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort1 =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber,pageSize,sort1);
		Page<Post> pagePost=lPostRepo.findAll(p);
		List<Post> allposts=pagePost.getContent();
		List<PostDto> allPostDto = allposts.stream().map(post-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PageResponse lPageResponse=new PageResponse();
		lPageResponse.setContent(allPostDto);
		lPageResponse.setPageNumber(pagePost.getNumber());
		lPageResponse.setPageSize(pagePost.getSize());
		lPageResponse.setTotalElements(pagePost.getTotalElements());
		lPageResponse.setTotalPages(pagePost.getTotalPages());
		lPageResponse.setLastPage(pagePost.isLast());
		
		return lPageResponse;
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer catId) {
		Category cat = lCategoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
	    List<Post> allPosts=lPostRepo.findByCategory(cat);
	    List<PostDto> allPostDto = allPosts.stream().map(post-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDto;
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		 
		User user=lUserRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		List<Post> allPosts=lPostRepo.findByUser(user);
	    List<PostDto> allPostDto = allPosts.stream().map(post-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDto;
	}

	@Override
	public List<PostDto> searchPostByKeyWord(String Keyword) {
		List<Post> allPosts = lPostRepo.findByPostTitleContaining(Keyword);
		
		List<PostDto> allpostDto = allPosts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

		return allpostDto;
	}

}

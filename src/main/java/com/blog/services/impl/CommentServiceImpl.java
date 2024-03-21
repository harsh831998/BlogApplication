package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepo lcommentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo lpostRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = lpostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment com=this.modelMapper.map(commentDto, Comment.class);
		com.setPost(post);
		Comment savedCom = lcommentRepo.save(com);
		return this.modelMapper.map(savedCom, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=lcommentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Commnet","id",commentId));
		lcommentRepo.delete(com);
		
	}

}

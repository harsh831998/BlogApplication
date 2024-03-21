package com.blog.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import com.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService lcomSer;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		
		CommentDto createdCom =lcomSer.createComment(commentDto,postId);
		return new ResponseEntity<>(createdCom,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{comId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer comId){
		lcomSer.deleteComment(comId);
		return ResponseEntity.ok(Map.of("message","Post Deleted successfully"));
	}
}

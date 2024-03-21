package com.blog.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String content;
	
	@ManyToOne
	private Post post;

     
}

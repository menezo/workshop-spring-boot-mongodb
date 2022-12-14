package com.menezo.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menezo.workshopmongo.domain.Post;
import com.menezo.workshopmongo.repositories.PostRepository;
import com.menezo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found id: " + id));
	}
}

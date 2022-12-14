package com.menezo.workshopmongo.services;

import java.util.Date;
import java.util.List;

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
	
	public List<Post> findByTitle(String text) {
		return repository.searchTitle(text);
	}
	
	public List<Post> fullsearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
} 

package com.menezo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.menezo.workshopmongo.domain.Post;
import com.menezo.workshopmongo.domain.User;
import com.menezo.workshopmongo.dtos.AuthorDTO;
import com.menezo.workshopmongo.dtos.CommentDTO;
import com.menezo.workshopmongo.repositories.PostRepository;
import com.menezo.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("11/12/2022"), "To cansado", "Vou tirar um cochilo! flw", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("13/12/2022"), "Bom dia pra quem?", "Acordei atrasado", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Bom cochilo mano!", sdf.parse("11/12/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Sonha comigo", sdf.parse("12/12/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Corre!", sdf.parse("13/12/2022"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}

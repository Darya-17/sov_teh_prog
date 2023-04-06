package com.example.java_spring_posts.services;

import com.example.java_spring_posts.models.Post;
import com.example.java_spring_posts.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public List<Post> getPostByName(String firstName){
        return postRepository.findByName(firstName);
    }

    public void savePost(Post post){
        postRepository.save(post);
    }

    public void deletePost(Integer id){
        postRepository.deleteById(id);
    }

    public Post getPostById(Integer id){
        return postRepository.findById(id).get();
    }
}
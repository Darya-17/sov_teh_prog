package com.example.java_spring_posts.repositories;

import com.example.java_spring_posts.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByName(String firstName);
}
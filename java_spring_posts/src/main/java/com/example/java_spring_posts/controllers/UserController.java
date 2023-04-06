package com.example.java_spring_posts.controllers;

import com.example.java_spring_posts.models.Category;
import com.example.java_spring_posts.models.Post;
import com.example.java_spring_posts.models.User;
import com.example.java_spring_posts.services.CategoryService;
import com.example.java_spring_posts.services.PostService;
import com.example.java_spring_posts.services.UserService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User order = userService.getUserById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("Пользователь был успешно добавлен", HttpStatus.OK);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<Set<Post>> getPosts(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id);
            var posts = postService.getAllPosts().stream().filter(it -> it.getUser().getId() == id).collect(Collectors.toSet());
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("{id}")
    public void delete(@RequestBody Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{id}/posts/{category_id}/")
    public ResponseEntity<String> addPost(@PathVariable Integer id, @PathVariable Integer category_id, @RequestBody Post post) {
        User user = userService.getUserById(id);
        post.setUser(user);
        Category category = categoryService.getCategorytById(category_id);
        post.setCategory(category);
        postService.savePost(post);
        return new ResponseEntity<>("Пост был успешно добавлен", HttpStatus.OK);
    }

    @PostMapping("/{id}/posts/{post_id}/update")
    public ResponseEntity<String> updatePost(@PathVariable Integer id, @PathVariable Integer post_id, @RequestBody Post post) {
        try {
            Post existingPost = postService.getPostById(post_id);
            existingPost.updatePost(post);
            postService.savePost(existingPost);
            return new ResponseEntity<>("Пост был успешно сохранен", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Такого поста не существует!", HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/{id}/posts/")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {

        try {
            postService.deletePost(id);
            return new ResponseEntity<>("Пост был успешно удален", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Такого поста не существует!", HttpStatus.NOT_FOUND);
        }
    }
}

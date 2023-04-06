package com.example.java_spring_posts.models;

import com.example.java_spring_posts.serializers.LocalDateAttributeDeserializer;
import com.example.java_spring_posts.serializers.LocalDateAttributeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name="posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate uploadDate;
    @ManyToOne()
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private User user;

    public void updatePost(Post post){
        if(post.name != null){
            this.name = post.name;
        }
        if(post.description != null){
            this.description = post.description;
        }
        if(post.uploadDate != null){
            this.uploadDate = post.uploadDate;
        }
        if(post.category != null){
            this.category = post.category;
        }
        if(post.user != null){
            this.user = post.user;
        }
    }
}

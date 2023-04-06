package com.example.java_spring_posts.models;

import com.example.java_spring_posts.serializers.LocalDateAttributeDeserializer;
import com.example.java_spring_posts.serializers.LocalDateAttributeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name="categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @UpdateTimestamp
    private LocalDate updatedAt;
}

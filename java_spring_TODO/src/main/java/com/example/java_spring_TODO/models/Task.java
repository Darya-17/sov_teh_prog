package com.example.java_spring_TODO.models;

import com.example.java_spring_TODO.serializers.LocalDateAttributeDeserializer;
import com.example.java_spring_TODO.serializers.LocalDateAttributeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name="tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate competedDate;


    private boolean Completed;
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

    public void updateTask(Task task){
        if(task.name != null){
            this.name = task.name;
        }
        if(task.description != null){
            this.description = task.description;
        }
        if(task.competedDate != null){
            this.competedDate = task.competedDate;
        }
        if(task.category != null){
            this.category = task.category;
        }
        if(task.user != null){
            this.user = task.user;
        }
    }
}

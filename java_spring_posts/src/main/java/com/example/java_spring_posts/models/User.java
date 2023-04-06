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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String lastName;
    private String firstName;
    private String patronymic;
    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @UpdateTimestamp
    private LocalDate updatedAt;

    public void updateUser(User user){
        if(user.firstName != null){
            this.firstName = user.firstName;
        }
        if(user.lastName != null){
            this.lastName = user.lastName;
        }
        if(user.patronymic != null){
            this.patronymic = user.patronymic;
        }
        if(user.dateOfBirth != null){
            this.dateOfBirth = user.dateOfBirth;
        }
        if(user.login != null){
            this.login = user.login;
        }
    }
}

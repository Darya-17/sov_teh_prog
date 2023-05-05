package com.example.building_materials_server.models;
import com.example.building_materials_server.serializers.LocalDateAttributeDeserializer;
import com.example.building_materials_server.serializers.LocalDateAttributeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.time.LocalDate;

@Entity
@Table(name="users")
@Data
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @UpdateTimestamp
    private LocalDate updatedAt;

    public void updateUser(User user){
        if(user.login != null){
            this.login = user.login;
        }
    }
}

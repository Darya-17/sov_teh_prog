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

import java.time.LocalDate;

@Entity
@Table(name="requests")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="material_id", nullable = false)
    private Material material;
    @ManyToOne
    @JoinColumn(name="request_type_id", nullable = false)
    private RequestType requestType;
    private int count;

    private boolean handled;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    @UpdateTimestamp
    private LocalDate updatedAt;
}

package com.springboot_task.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class JsonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jsonModel;

    // Getters and setters
}

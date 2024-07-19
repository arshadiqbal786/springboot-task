package com.springboot_task.repository;


import com.springboot_task.entity.JsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonRepository extends JpaRepository<JsonEntity, Long> {
}


package com.narender.ToDo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narender.ToDo.entities.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}

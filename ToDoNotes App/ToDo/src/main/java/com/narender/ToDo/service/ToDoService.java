package com.narender.ToDo.service;

import java.util.List;

import com.narender.ToDo.entities.ToDo;

public interface ToDoService {

	List<ToDo> getAllTodos();

	ToDo saveTodo(ToDo todo);

	ToDo findById(Long id);

	ToDo updateTodo(ToDo todo);

	void deleteTodo(Long id);

}

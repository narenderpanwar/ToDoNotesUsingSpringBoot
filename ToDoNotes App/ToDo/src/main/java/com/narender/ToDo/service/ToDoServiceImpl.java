package com.narender.ToDo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.narender.ToDo.entities.ToDo;
import com.narender.ToDo.repository.ToDoRepository;

@Service
@Transactional
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	
	@Override
	public List<ToDo> getAllTodos() {
		return this.todoRepository.findAll();
	}

	@Override
	public ToDo saveTodo(ToDo todo) {
		return this.todoRepository.save(todo);
	}

	@Override
	public ToDo findById(Long id) {
		return this.todoRepository.findById(id).get();
	}

	@Override
	public ToDo updateTodo(ToDo todo) {
		return this.todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(Long id) {
		this.todoRepository.deleteById(id);
	}

}

package com.narender.ToDo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.narender.ToDo.entities.ToDo;
import com.narender.ToDo.service.ToDoService;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService todoService;
	
	@GetMapping("/")
	public String allTodos(Model model) {
		List<ToDo> todos = todoService.getAllTodos();
		model.addAttribute("todos", todos);
		model.addAttribute("todo", new ToDo());
		model.addAttribute("title", "ToDos");
		model.addAttribute("isAdd", true);
		return "todosForm";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("todo") ToDo todo, RedirectAttributes redirectAttributes, Model model) {
		ToDo dbTodo = todoService.saveTodo(todo);
		if(dbTodo!=null) {
			redirectAttributes.addFlashAttribute("successMessage", "Your ToDo has been successfully saved!");
			return "redirect:/";
		}
		else {
			model.addAttribute("errorMessage", "Failed to save, Please try again!");
			model.addAttribute("todo", todo);
			return "todosForm";
		}
	}
	
	
	@GetMapping("/alltodos")
	public String processButton(Model model) {
		List<ToDo> todos = todoService.getAllTodos();
		model.addAttribute("todos", todos);
		return "allTodos";
		
	}
	
	@GetMapping("/getTodo/{id}")
	public String getTodo(@PathVariable Long id, Model model) {
		
		ToDo todo= todoService.findById(id);
		
		List<ToDo> todos = todoService.getAllTodos();
		model.addAttribute("todos", todos);
		
		model.addAttribute("todo", todo);
		model.addAttribute("title", "Update ToDo");
		model.addAttribute("isAdd", false);
		
		return "todosForm";
	}
	

	@PostMapping("/update")
	public String update(@ModelAttribute ToDo todo, RedirectAttributes redirectAttributes, Model model) {
		
		System.out.println(todo);
		
		ToDo dbTodo = todoService.updateTodo(todo);
		if(dbTodo!=null) {
			redirectAttributes.addFlashAttribute("successMessage", "Your ToDo has been successfully updated!");
			return "redirect:/";
		}
		else {
			model.addAttribute("errorMessage", "Failed to update, Please try again!");
			model.addAttribute("todo", todo);
			return "todosForm";
		}
		
	}
	
	@GetMapping("/deleteTodo/{id}")
	public String deleteTodo(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model) {
		todoService.deleteTodo(id);
		return "redirect:/alltodos";
	}
	
	
}

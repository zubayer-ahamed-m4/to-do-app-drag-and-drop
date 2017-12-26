package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.model.TodoItem;
import com.todo.services.TodoServices;

@Controller
public class HomeController {
	
	@Autowired
	private TodoServices todoServices;

	@RequestMapping("/")
	public String loadIndexPage(Model model){
		model.addAttribute("todoItem", new TodoItem());
		model.addAttribute("todoItems", todoServices.getTodoList().size() == 0? null : todoServices.getTodoList());
		return "views/index";
	}
	
}

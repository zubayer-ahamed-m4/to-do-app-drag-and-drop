package com.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.todo.exception.TodoException;
import com.todo.model.TodoItem;
import com.todo.services.TodoServices;

@Controller
public class TodoController {

	@Autowired
	private TodoServices todoServices;
	
	@RequestMapping(value="/addTodoItem", method=RequestMethod.POST)
	public String addTodoItem(@ModelAttribute("todoItem") @Valid TodoItem todoItem, BindingResult result, Model model) throws TodoException{
		if(result.hasErrors()){
			model.addAttribute("todoItems", todoServices.getTodoList().size() == 0? null : todoServices.getTodoList());
			return "views/index";
		}
		todoServices.addItem(todoItem);
		//model.addAttribute("todoItems", todoServices.getTodoList().size() == 0? null : todoServices.getTodoList());
		return "redirect:/";
	}
	
}

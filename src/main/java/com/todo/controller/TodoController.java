package com.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.todo.exception.TodoException;
import com.todo.model.TodoItem;
import com.todo.services.DoneServices;
import com.todo.services.InProgressServices;
import com.todo.services.TodoServices;

@Controller
public class TodoController {

	@Autowired
	private TodoServices todoServices;
	@Autowired
	private InProgressServices inProgressServices;
	@Autowired
	private DoneServices doneServices;

	@RequestMapping("/")
	public String loadIndexPage(Model model) {
		model.addAttribute("todoItem", new TodoItem());
		model.addAttribute("todoItems", todoServices.getTodoList().size() == 0 ? null : todoServices.getTodoList());
		model.addAttribute("inProgressItems",
				inProgressServices.getTodoList().size() == 0 ? null : inProgressServices.getTodoList());
		model.addAttribute("doneItems", doneServices.getTodoList().size() == 0 ? null : doneServices.getTodoList());
		return "views/index";
	}

	@RequestMapping(value = "/addTodoItem", method = RequestMethod.POST)
	public String addTodoItem(@ModelAttribute("todoItem") @Valid TodoItem todoItem, BindingResult result, Model model)
			throws TodoException {
		if (result.hasErrors()) {
			model.addAttribute("todoItems", todoServices.getTodoList().size() == 0 ? null : todoServices.getTodoList());
			return "views/index";
		}
		todoServices.addItem(todoItem);
		// model.addAttribute("todoItems", todoServices.getTodoList().size() == 0? null
		// : todoServices.getTodoList());
		return "redirect:/";
	}

	@RequestMapping(value = "/updateTodoItem/{id}")
	public String updateTodoItem(@PathVariable("id") long id, Model model) throws TodoException {
		model.addAttribute("todoItemById", todoServices.getById(id));
		model.addAttribute("todoItems", todoServices.getTodoList().size() == 0 ? null : todoServices.getTodoList());
		model.addAttribute("update", "true");
		model.addAttribute("todoItem", new TodoItem());
		return "views/index";
	}

	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public String updateItem(@ModelAttribute("todoItemById") @Valid TodoItem todoItem, BindingResult result,
			Model model) throws TodoException {
		if (result.hasErrors()) {
			model.addAttribute("todoItems", todoServices.getTodoList().size() == 0 ? null : todoServices.getTodoList());
			model.addAttribute("update", "true");
			model.addAttribute("todoItem", new TodoItem());
			return "views/index";
		}
		todoServices.updateItem(todoItem);
		return "redirect:/";
	}

	@RequestMapping(value = "/moveToInProgress/{id}", method = RequestMethod.GET)
	public String moveToInProgress(@PathVariable("id") long id, Model model) throws TodoException {
		inProgressServices.addItem(todoServices.getById(id));
		todoServices.deleteItem(todoServices.getById(id));
		return "redirect:/";
	}

	@RequestMapping(value = "/moveToTodo/{id}", method = RequestMethod.GET)
	public String moveToTodo(@PathVariable("id") long id, Model model) throws TodoException {
		todoServices.addItem(inProgressServices.getById(id));
		inProgressServices.deleteItem(inProgressServices.getById(id));
		return "redirect:/";
	}

	@RequestMapping(value = "/moveToDone/{id}", method = RequestMethod.GET)
	public String moveToDone(@PathVariable("id") long id, Model model) throws TodoException {
		doneServices.addItem(inProgressServices.getById(id));
		inProgressServices.deleteItem(inProgressServices.getById(id));
		return "redirect:/";
	}

	@RequestMapping(value = "/moveToInProgressFromDone/{id}", method = RequestMethod.GET)
	public String moveToInProgressFromDone(@PathVariable("id") long id, Model model) throws TodoException {
		inProgressServices.addItem(doneServices.getById(id));
		doneServices.deleteItem(doneServices.getById(id));
		return "redirect:/";
	}

	@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") long id, Model model) throws TodoException {
		doneServices.deleteItem(doneServices.getById(id));
		return "redirect:/";
	}

}

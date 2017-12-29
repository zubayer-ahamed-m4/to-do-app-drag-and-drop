package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.exception.TodoException;
import com.todo.message.Response;
import com.todo.services.DoneServices;
import com.todo.services.InProgressServices;
import com.todo.services.TodoServices;

@RestController
@RequestMapping("/rest")
public class TodoRestController {

	@Autowired private TodoServices todoServices;
	@Autowired private InProgressServices inProgressServices;
	@Autowired private DoneServices doneServices;
	
	@RequestMapping(value="/changeData/{fromId}/{itemId}/{toId}")
	public String changeData(@PathVariable("fromId") String fromId, @PathVariable("itemId") long id, @PathVariable("toId") String toId) throws TodoException{
		if(fromId.contains("todo") && toId.contains("inprogress")) {
			inProgressServices.addItem(todoServices.getById(id));
			todoServices.deleteItem(todoServices.getById(id));
		}else if(fromId.contains("todo") && toId.contains("done")) {
			doneServices.addItem(todoServices.getById(id));
			todoServices.deleteItem(todoServices.getById(id));
		}else if(fromId.contains("inprogress") && toId.contains("todo")) {
			todoServices.addItem(inProgressServices.getById(id));
			inProgressServices.deleteItem(inProgressServices.getById(id));
		}else if(fromId.contains("inprogress") && toId.contains("done")) {
			doneServices.addItem(inProgressServices.getById(id));
			inProgressServices.deleteItem(inProgressServices.getById(id));
		}else if(fromId.contains("done") && toId.contains("todo")) {
			todoServices.addItem(doneServices.getById(id));
			doneServices.deleteItem(doneServices.getById(id));
		}else if(fromId.contains("done") && toId.contains("inprogress")) {
			inProgressServices.addItem(doneServices.getById(id));
			doneServices.deleteItem(doneServices.getById(id));
		}
		return "success";
	}
	
	@RequestMapping(value="/deleteData/{parentId}/{id}")
	public String deleteData(@PathVariable("parentId") String parentId, @PathVariable("id") long id) throws TodoException{
		if(parentId.contains("todo")) {
			todoServices.deleteItem(todoServices.getById(id));
		}else if(parentId.contains("inprogress")) {
			inProgressServices.deleteItem(inProgressServices.getById(id));
		}else {
			doneServices.deleteItem(doneServices.getById(id));
		}
		return "success";
	}
}

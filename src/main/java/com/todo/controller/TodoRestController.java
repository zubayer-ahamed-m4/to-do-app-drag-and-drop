package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.message.Response;
import com.todo.services.TodoServices;

@RestController
@RequestMapping("/rest")
public class TodoRestController {

	@Autowired
	private TodoServices todoServices;
	
	@RequestMapping(value="/updateTodoItem/{id}")
	public Response getItemById(@PathVariable("id") long id){
		Response response = new Response("Done", todoServices.getById(id));
		return response;
	}
	
}

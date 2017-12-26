package com.todo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo.exception.TodoException;
import com.todo.model.TodoItem;

@Service
public class InProgressServices {

	private List<TodoItem> todoList = new ArrayList<>();

	public void addItem(TodoItem todoItem) throws TodoException {
		if (todoList.stream().filter(r -> r.getId() == todoItem.getId()).count() > 0) {
			throw new TodoException("Item Already Exist");
		}
		todoItem.setId(new Random().nextLong());
		todoList.add(todoItem);
	}

	public void updateItem(TodoItem todoItem) throws TodoException {
		List<TodoItem> list = todoList.stream().filter(r -> r.getId().equals(todoItem.getId()))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new TodoException("No items found for update");
		}
		todoList.remove(list.get(0));
		todoList.add(todoItem);
	}

	public void deleteItem(TodoItem todoItem) throws TodoException {
		List<TodoItem> list = todoList.stream().filter(r -> r.getId().equals(todoItem.getId()))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new TodoException("No items found for delete");
		}
		todoList.remove(list.get(0));
	}

	public TodoItem getById(long id) {
		List<TodoItem> list = todoList.stream().filter(r -> r.getId().equals(id)).collect(Collectors.toList());
		if (list.size() != 1) {
			return null;
		}
		return list.get(0);
	}

	public List<TodoItem> getTodoList() {
		return todoList;
	}

}

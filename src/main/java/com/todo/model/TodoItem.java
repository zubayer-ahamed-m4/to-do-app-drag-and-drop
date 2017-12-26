package com.todo.model;

import org.hibernate.validator.constraints.NotEmpty;

public class TodoItem {

	private Long id;
	@NotEmpty(message="This field must not be empty")
	private String des;

	public TodoItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodoItem(Long id, String des) {
		super();
		this.id = id;
		this.des = des;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", des=" + des + "]";
	}

}

package com.todo.message;

public class Response {

	private String status;
	private Object data;

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}

}

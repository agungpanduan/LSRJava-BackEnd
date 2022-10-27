package com.lsr.response;

public class CommonResponse<T> {
	private String status;
    private String message;
    private T data;
    private Number countData;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Number getCountData() {
		return countData;
    }
	public void setCountData(Number countData) {
		this.countData = countData;
	}    
}

package com.arttraining.api.bean;

public class OrderRemainTimeBean {
	private String error_code;
	private String error_msg;
	private Integer remaining_time;
	
	
	public OrderRemainTimeBean() {
		this.remaining_time = 0;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public Integer getRemaining_time() {
		return remaining_time;
	}
	public void setRemaining_time(Integer remaining_time) {
		this.remaining_time = remaining_time;
	}
	
	
}
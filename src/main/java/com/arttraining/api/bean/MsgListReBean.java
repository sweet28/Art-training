package com.arttraining.api.bean;

import java.util.ArrayList;
import java.util.List;

public class MsgListReBean {
	private String error_code;
	private String error_msg;
	private List<MsgListBean> msg_list;
	
	public MsgListReBean() {
		this.msg_list = new ArrayList<MsgListBean>();
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
	public List<MsgListBean> getMsg_list() {
		return msg_list;
	}
	public void setMsg_list(List<MsgListBean> msg_list) {
		this.msg_list = msg_list;
	}
	
	
}

package com.arttraining.api.bean;

public class OrgShowCourseBean {
	private int course_id;
	private String course_name;
	private String course_intro;
	private Double course_pay;
	private String course_pay_zh;
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_intro() {
		return course_intro;
	}
	public void setCourse_intro(String course_intro) {
		this.course_intro = course_intro;
	}
	public Double getCourse_pay() {
		return course_pay;
	}
	public void setCourse_pay(Double course_pay) {
		this.course_pay = course_pay;
	}
	public String getCourse_pay_zh() {
		return course_pay_zh;
	}
	public void setCourse_pay_zh(String course_pay_zh) {
		this.course_pay_zh = course_pay_zh;
	}
}

package com.arttraining.api.bean;

import java.util.ArrayList;
import java.util.List;

public class MajorLevelListBean {
	private int major_id;
	private String major_name;
	private int father_id;
	private String father_name;
	private int level;
	private List<MajorLevelListBean> son_majors;
	
	public MajorLevelListBean() {
		this.son_majors = new ArrayList<MajorLevelListBean>();
	}

	public int getMajor_id() {
		return major_id;
	}

	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public int getFather_id() {
		return father_id;
	}

	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<MajorLevelListBean> getSon_majors() {
		return son_majors;
	}

	public void setSon_majors(List<MajorLevelListBean> son_majors) {
		this.son_majors = son_majors;
	}
	
	
}

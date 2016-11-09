package com.arttraining.api.bean;

import java.util.ArrayList;
import java.util.List;

public class HomePageStatusesBean {
	private Integer stus_id;
	private String stus_type;
	private Integer owner;
	private String owner_type;
	private String owner_name;
	private String owner_head_pic;
	private String create_time;
	private String tag;
	private String city;
	private String identity;
	private String title;
	private String content;
	private Integer browse_num;
	private Integer comment_num;
	private Integer like_num;
	private String is_like;
	private String is_comment;
	private String remarks;
    
    private List<HomePageAttBean> att;
    

	public HomePageStatusesBean() {
		this.stus_id = 0;
		this.stus_type = "";
		this.owner = 0;
		this.owner_type = "";
		this.owner_name = "";
		this.owner_head_pic = "";
		this.create_time = "";
		this.tag = "";
		this.city = "";
		this.identity = "";
		this.title = "";
		this.content = "";
		this.browse_num = 0;
		this.comment_num = 0;
		this.like_num = 0;
		this.is_like = "";
		this.is_comment = "";
		this.remarks="";
		this.att = new ArrayList<HomePageAttBean>();;
	}

	public Integer getStus_id() {
		return stus_id;
	}

	public void setStus_id(Integer stus_id) {
		this.stus_id = stus_id;
	}

	public String getStus_type() {
		return stus_type;
	}

	public void setStus_type(String stus_type) {
		this.stus_type = stus_type;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getOwner_type() {
		return owner_type;
	}

	public void setOwner_type(String owner_type) {
		this.owner_type = owner_type;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getOwner_head_pic() {
		return owner_head_pic;
	}

	public void setOwner_head_pic(String owner_head_pic) {
		this.owner_head_pic = owner_head_pic;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBrowse_num() {
		return browse_num;
	}

	public void setBrowse_num(Integer browse_num) {
		this.browse_num = browse_num;
	}

	public Integer getComment_num() {
		return comment_num;
	}

	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}

	public Integer getLike_num() {
		return like_num;
	}

	public void setLike_num(Integer like_num) {
		this.like_num = like_num;
	}

	public String getIs_like() {
		return is_like;
	}

	public void setIs_like(String is_like) {
		this.is_like = is_like;
	}

	public String getIs_comment() {
		return is_comment;
	}

	public void setIs_comment(String is_comment) {
		this.is_comment = is_comment;
	}

	public List<HomePageAttBean> getAtt() {
		return att;
	}

	public void setAtt(List<HomePageAttBean> att) {
		this.att = att;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
}
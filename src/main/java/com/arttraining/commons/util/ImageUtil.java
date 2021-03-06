package com.arttraining.commons.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arttraining.api.bean.HomePageAttBean;

public class ImageUtil {
	//依据动态类型和store_path来解析相应的封面
	public static String parseStatusThumbnail(String store_path,String type) {
		String path="";
		if(type.equals("")) {
			//首先判断是否是Json
			JSONArray jsonArray = JSONArray.parseArray(store_path);
			JSONObject jsonObject=jsonArray.getJSONObject(0);
			path=jsonObject.getString("store_path");
		} else {
			path = store_path;
			switch (type) {
			case "status":
				path=ImageUtil.parsePicPath(path, 1);
				break;
			case "g_stus":
				path=ImageUtil.parsePicPath(path, 3);
				break;
			case "work":
				path=ImageUtil.parsePicPath(path, 6);
				break;
			default:
				break;
			}
		}
		return path;
	}
	
	//传递store_path 解析
	public static String parseWorkPicPath(String store_path) {
		String path="";
		//首先判断是否是Json
		JSONArray jsonArray = JSONArray.parseArray(store_path);
		JSONObject jsonObject=jsonArray.getJSONObject(0);
		path=jsonObject.getString("store_path");
		path=ImageUtil.parsePicPath(path, 6);
		return path;
	}
	//封装一个方法用于解析json数据 然后将其拆解
	public static List<HomePageAttBean> parseAttPathByType(Integer att_id,String att_type,
				String duration,String thumbnail,String store_path,Integer type) {
		List<HomePageAttBean> attList = new ArrayList<HomePageAttBean>();
		HomePageAttBean h = null;
			 
		String path="";
		//首先判断是否是Json
		JSONArray jsonArray = JSONArray.parseArray(store_path);
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
			JSONObject jsonObject = (JSONObject) iterator.next();
			h = new HomePageAttBean();
			h.setAtt_id(att_id);
			h.setAtt_type(att_type);

			h.setDuration(duration);
			path = jsonObject.getString("store_path");
			path = ImageUtil.parsePicPath(path, type);

			String tmpPath = path;
			if (att_type.equals("pic")) {
				path = path + "-1024X768";
			}

			h.setStore_path(path);
			thumbnail = ImageUtil.parsePicPath(thumbnail, type);

			if (att_type.equals("pic")) {
				thumbnail = tmpPath + "-400X247";
			}
			if (att_type.equals("video")) {
				thumbnail = thumbnail + "-400X247";
			}

			h.setThumbnail(thumbnail);
			attList.add(h);
		}
		return attList;
	}
	// 封装一个方法用于解析json数据 然后将其拆解
	public static String parseAttPath(String store_path) {
		String path = "";
		//去除首尾空格字符串
		store_path=store_path.trim();
		// 首先判断是否是Json
		JSONArray jsonArray = JSONArray.parseArray(store_path);
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
			JSONObject jsonObject = (JSONObject) iterator.next();
			path = jsonObject.getString("store_path");
		}
		return path;
	}

	// 封装一个方法用于解析json数据 然后将其拆解
	public static String parseQiNiuPath(String store_path,Integer type) {
		String path = "";
		String pre_path="";
		if(ConfigUtil.CODE_TYPE.equals(ConfigUtil.CODE_TYPE_DEV)){
			type = 0;
		}
		switch (type) {
		case 0:
			pre_path=ConfigUtil.QINIU_BUCKET_COM_URL;
			break;
		case 1:
			pre_path=ConfigUtil.QINIU_BUCKET_BBS_COM_URL;
			break;
		case 2:
			pre_path=ConfigUtil.QINIU_BUCKET_COURSE_COM_URL;
			break;
		case 3:
			pre_path=ConfigUtil.QINIU_BUCKET_G_STUS_COM_URL;
			break;
		case 4:
			pre_path=ConfigUtil.QINIU_BUCKET_INFO_COM_URL;
			break;
		case 5:
			pre_path=ConfigUtil.QINIU_BUCKET_STU_ORG_TEC_COM_URL;
			break;
		case 6:
			pre_path=ConfigUtil.QINIU_BUCKET_WORKS_COM_URL;
			break;
		case 7:
			pre_path=ConfigUtil.QINIU_BUCKET_LIVE_COM_URL;
			break;
		default:
			break;
		}
		
		// 首先判断是否是Json
		JSONArray jsonArray = JSONArray.parseArray(store_path);
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
			JSONObject jsonObject = (JSONObject) iterator.next();
			path = pre_path + "/"
					+ jsonObject.getString("store_path");
		}

		return path;
	}
	
	public static String parsePicPath(String store_path,Integer type){
		String result = "";
		if(ConfigUtil.CODE_TYPE.equals(ConfigUtil.CODE_TYPE_DEV)){
			type = 0;
		}
		if(null != store_path && !"".equals(store_path.trim())){
			String pre_path="";
			switch (type) {
			case 0:
				pre_path=ConfigUtil.QINIU_BUCKET_COM_URL;
				break;
			case 1:
				pre_path=ConfigUtil.QINIU_BUCKET_BBS_COM_URL;
				break;
			case 2:
				pre_path=ConfigUtil.QINIU_BUCKET_COURSE_COM_URL;
				break;
			case 3:
				pre_path=ConfigUtil.QINIU_BUCKET_G_STUS_COM_URL;
				break;
			case 4:
				pre_path=ConfigUtil.QINIU_BUCKET_INFO_COM_URL;
				break;
			case 5:
				pre_path=ConfigUtil.QINIU_BUCKET_STU_ORG_TEC_COM_URL;
				break;
			case 6:
				pre_path=ConfigUtil.QINIU_BUCKET_WORKS_COM_URL;
				break;
			case 7:
				pre_path=ConfigUtil.QINIU_BUCKET_LIVE_COM_URL;
				break;
			default:
				break;
			}
			result = pre_path+"/" + store_path;
		}else{
			result = store_path;
		}
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package com.arttraining.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.arttraining.api.bean.AdvertiseListBean;
import com.arttraining.api.bean.AdvertiseShowBean;
import com.arttraining.api.service.impl.AdvertiseService;
import com.arttraining.commons.util.ConfigUtil;
import com.arttraining.commons.util.ErrorCodeConfigUtil;
import com.arttraining.commons.util.NumberUtil;
import com.arttraining.commons.util.ServerLog;
import com.google.gson.Gson;

@Controller
@RequestMapping("/advertising")
public class AdvertiseController {
	@Resource
	private AdvertiseService advertiseService;
	
	/***
	 * 获取广告列表
	 * 分页查询 默认返回2条记录
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object list(HttpServletRequest request, HttpServletResponse response) {
		String errorCode = "";
		String errorMessage = "";
		
		//调用service执行获取信息列表
		List<AdvertiseListBean> adList = this.advertiseService.getAdList();
		//判断是否有内容
		if(adList.size()>0) {
			errorCode="0";
			errorMessage="ok";	
		}
		else {
			adList = new ArrayList<AdvertiseListBean>();
			errorCode="20007";
			errorMessage=ErrorCodeConfigUtil.ERROR_MSG_ZH_20007;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ConfigUtil.PARAMETER_ERROR_CODE, errorCode);
		jsonObject.put(ConfigUtil.PARAMETER_ERROR_MSG, errorMessage);
		jsonObject.put("ads", adList);
		ServerLog.getLogger().warn(jsonObject.toString());
		return jsonObject;
	}
	/***
	 * 根据广告ID获取广告详情信息
	 * 传递的参数:
	 * ad_id--广告ID
	 */
	@RequestMapping(value = "/show", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object show(HttpServletRequest request, HttpServletResponse response) {
		String errorCode = "";
		String errorMessage = "";

		AdvertiseShowBean adShow = new AdvertiseShowBean();
		//获取传递的ad_id参数,判断传递的参数是否不存在或者是否为空
		String ad_id=request.getParameter("ad_id");
		ServerLog.getLogger().warn("ad_id:"+ad_id);
		
		if(ad_id==null || ad_id.equals("")) {
			errorCode="20032";
			errorMessage=ErrorCodeConfigUtil.ERROR_MSG_ZH_20032;
		}
		else
		if(!NumberUtil.isInteger(ad_id)) {
			errorCode="20033";
			errorMessage=ErrorCodeConfigUtil.ERROR_MSG_ZH_20033;
		}
		else {
			//广告ID
			Integer i_ad_id = Integer.valueOf(ad_id);
			adShow = this.advertiseService.getAdShowByPrimaryKey(i_ad_id);
			if(adShow==null) {
				adShow = new AdvertiseShowBean();
				errorCode="20007";
				errorMessage=ErrorCodeConfigUtil.ERROR_MSG_ZH_20007;
			}
			else {
				errorCode="0";
				errorMessage="ok";
			}
		}
		adShow.setError_code(errorCode);
		adShow.setError_msg(errorMessage);
		
		Gson gson = new Gson();
		ServerLog.getLogger().warn(gson.toJson(adShow));
		return gson.toJson(adShow);
	}
}

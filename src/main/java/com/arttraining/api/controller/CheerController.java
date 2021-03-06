package com.arttraining.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.arttraining.api.pojo.VoteCheer;
import com.arttraining.api.service.impl.CheerService;
import com.arttraining.commons.util.ConfigUtil;
import com.arttraining.commons.util.ErrorCodeConfigUtil;
import com.arttraining.commons.util.NumberUtil;
import com.arttraining.commons.util.ServerLog;

@Controller
@RequestMapping("/cheer")
public class CheerController {
	@Resource
	private CheerService cheerService;
	
	/***
	 * 用于获取专题活动视频宣传列表信息
	 * 传递的参数:act_id--活动ID
	 * act_type--活动类型
	 * 
	 */
	@RequestMapping(value = "/act/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object actList(HttpServletRequest request, HttpServletResponse response) {
		String errorCode = "";
		String errorMessage = "";
		
		//以下是必选参数
		String callbackparam=request.getParameter("callbackparam");
		String act_id=request.getParameter("act_id");
		String act_type=request.getParameter("act_type");
		
		ServerLog.getLogger().warn("act_id:"+act_id+"-act_type:"+act_type);
		List<VoteCheer> cheerList = new ArrayList<VoteCheer>();
		if(act_id==null || act_type==null) {
			errorCode = "20032";
			errorMessage = ErrorCodeConfigUtil.ERROR_MSG_ZH_20032;
		} else if(act_id.equals("") || act_type.equals("")) {
			errorCode = "20032";
			errorMessage = ErrorCodeConfigUtil.ERROR_MSG_ZH_20032;
		} else if(!NumberUtil.isInteger(act_id)) {
			errorCode = "20033";
			errorMessage = ErrorCodeConfigUtil.ERROR_MSG_ZH_20033;
		} else {
			Integer i_act_id=Integer.valueOf(act_id);
			//去查询宣传视频列表信息
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("act_id", i_act_id);
			map.put("act_type", act_type);
			cheerList = this.cheerService.getCheerListByActId(map);
			if(cheerList.size()>0) {
				errorCode="0";
				errorMessage="ok";
			} else {
				cheerList = new ArrayList<VoteCheer>();
				errorCode = "20007";
				errorMessage = ErrorCodeConfigUtil.ERROR_MSG_ZH_20007;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ConfigUtil.PARAMETER_ERROR_CODE, errorCode);
		jsonObject.put(ConfigUtil.PARAMETER_ERROR_MSG, errorMessage);
		jsonObject.put("cheer_list", cheerList);
		
		ServerLog.getLogger().warn(jsonObject.toString());
		String jsonResult=callbackparam+"("+jsonObject.toString()+")";
		return jsonResult;
	}
}

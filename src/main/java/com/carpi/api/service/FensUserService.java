package com.carpi.api.service;

import com.arttraining.commons.util.JsonResult;
import com.carpi.api.pojo.FensUser;

public interface FensUserService {

	//粉丝注册
	public JsonResult register(FensUser fensUser,String code_type,String code);
	
	//登入
	public JsonResult login(FensUser fensUser);
}

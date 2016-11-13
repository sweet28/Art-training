package com.arttraining.commons.util;

import com.qiniu.util.StringMap;

public class ConfigUtil {
	// todo:七牛云密钥
	public static String QINIU_AK = "4NLPjCaLLjYCXYUQ-Jl5iW5Ceknmux9iDRDtQ2vX";
	public static String QINIU_SK = "ZxF06uDuQ_ZQVKsNSQykLN1YwWYrC1lFTunBUFf6";
	public static String QINIU_BUCKET = "yipei-2016";// 空间名
	public static String QINIU_KEY = null;
	public static long QINIU_EXPIRES = 86400;// 有效时长，单位秒
	public static boolean STRICT = true;
	public static StringMap QINIU_POLICY = null;
	public static String QINIU_BUCKET_COM_URL = "http://oflkt0ank.bkt.clouddn.com";

	public static String PARAMETER_ERROR_CODE = "error_code";
	public static String PARAMETER_ERROR_MSG = "error_msg";
	public static String PARAMETER_QINIU_TOKEN = "qiniu_token";
	public static String PARAMETER_UID = "uid";
	public static String PARAMETER_USER_CODE = "user_code";
	public static String PARAMETER_NAME = "name";

	// todo:token配置
	public static int EXPIRE_TIME = 7 * 24 * 60 * 60;// 单位秒
	// todo:pwd md5字符
	public static String MD5_PWD_STR = "YUNHUYI_YZL_@)!^";
	// todo:sms验证码长度
	public static int ALIDAYU_SMS_CHECK_CODE_LENGTH = 4;
	public static String ALIDAYU_SMS_URL = "http://gw.api.taobao.com/router/rest";
	public static String ALIDAYU_SMS_APPKEY = "23523438";
	public static String ALIDAYU_SMS_SECRET = "221e92217b31a02c2a5620dc80f15d74";
	public static String ALIDAYU_SMS_TYPE = "normal";
	public static String ALIDAYU_SMS_EXTEND = "";
	public static String ALIDAYU_SMS_PRODUCT = "云互艺";
	public static String ALIDAYU_SMS_FREE_SIGN_NAME_REG = "注册验证";
	public static String ALIDAYU_SMS_FREE_SIGN_NAME_LOGIN = "登录验证";
	public static String ALIDAYU_SMS_FREE_SIGN_NAME_CHANGE = "变更验证";
	public static String ALIDAYU_SMS_FREE_SIGN_NAME_IDENTITY = "身份验证";
	public static String ALIDAYU_SMS_FREE_SIGN_NAME_ACTIVITY = "活动验证";
	public static String ALIDAYU_SMS_TEMPLATE_CODE_IDENTITY = "SMS_11625303";// 身份验证验证码模板ID
	public static String ALIDAYU_SMS_TEMPLATE_CODE_LOGIN = "SMS_11625301";// 登录确认验证码模板ID
	public static String ALIDAYU_SMS_TEMPLATE_CODE_LOGIN_REEOR = "SMS_11625300";// 登录异常验证码模板ID
	public static String ALIDAYU_SMS_TEMPLATE_CODE_REG = "SMS_11625299";// 注册验证码模板ID
	public static String ALIDAYU_SMS_TEMPLATE_CODE_ACTIVITY = "SMS_11625298";// 活动确认验证码模板ID
	public static String ALIDAYU_SMS_TEMPLATE_CODE_CHANGE_PWD = "SMS_11625297";// 修改密码验证码模板ID
	public static String ALIDAYU_SMS_TEMPLATE_CODE_CHANGE_INFO = "SMS_11625296";// 信息变更验证码ID
	public static int ALIDAYU_SMS_SEND_INTERVAL = 60;// 发送验证码的间隔
	public static int ALIDAYU_SMS_EXPIRE_TIME = 5;// 发送的验证码有效时间，分钟

	public static String ALIDAYU_SMS_CODE_TYPE_REG = "reg_code";// 发送注册类型的验证码
	public static String ALIDAYU_SMS_CODE_TYPE_LOGIN = "login_code";// 发送登录验证码
	public static String ALIDAYU_SMS_CODE_TYPE_CHANGE = "change_code";// 发送变更身份的验证码
	public static String ALIDAYU_SMS_CODE_TYPE_IDENTITY = "identity_code";// 发送身份验证的验证码6
	public static String ALIDAYU_SMS_CODE_TYPE_ACTIVITY = "activity_code";// 发送活动验证的验证码

	// 分页时 默认显示记录条数
	public static Integer PAGESIZE = 10;
	// 广告 默认显示的记录条数
	public static Integer ADVERTISE_PAGESIZE = 2;
	// 首页名师 默认显示记录条数
	public static Integer HOMEPAGE_PAGESIZE = 2;
	// 首页作品 默认显示2条记录数
	public static Integer HOMEWORK_PAGESIZE = 2;

	// 帖子/小组动态名师点评显示的记录数
	public static Integer DIANPING_PAGESIZE = 2;

}

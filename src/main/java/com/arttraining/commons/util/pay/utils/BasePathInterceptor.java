package com.arttraining.commons.util.pay.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BasePathInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = Logger.getLogger(BasePathInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String path = request.getContextPath();
		String basePath = scheme + "://" + serverName + ":" + port + path;
		logger.info(basePath);
		request.setAttribute("basePath", basePath);
		return true;
	}

}
package com.arttraining.api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arttraining.commons.util.pay.AliPay.AlipayConfig;
import com.arttraining.commons.util.pay.AliPay.AlipayCore;
import com.arttraining.commons.util.pay.AliPay.RSA;


@Controller
@RequestMapping("mobile/ali")
public class AlipayNotify {
	/**
	 * 支付宝消息验证地址
	 */
	private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

	@RequestMapping("/pay")
	public void alipay(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
	
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
	
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
	
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			
			// 交易金额
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
			
			// 交易付款时间
			String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), "UTF-8");
	
			// 异步通知ID
			String notify_id = request.getParameter("notify_id");
	
			// sign
			String sign = request.getParameter("sign");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
			if (notify_id != "" && notify_id != null) {// //判断接受的post通知中有无notify_id，如果有则是异步通知。
				if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
				{
					if (AlipayNotify.getSignVeryfy(params, sign))// 使用支付宝公钥验签
					{
						// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
						if (trade_status.equals("TRADE_FINISHED")) {
							
						} else if (trade_status.equals("TRADE_SUCCESS")) {
							// 查询订单中的价格
							Map<String, Object> orderMap = new HashMap<String, Object>();
							orderMap.put("id", out_trade_no);//自己的订单号，传给支付宝，再回传给自己，读取相关信息。
							//利用orderMap得到自己后台记录的订单信息
							String order_price = "1";//demo，数据库记录订单价格为1元
							if (total_fee.equals(order_price)) {
								//支付宝回传的价格，与自己记录的价格一致
								//正式完成订单已支付的相关操作
								//可以做的更严谨
							}
						}
						// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
						response.getWriter().println("success");// 请不要修改或删除
					} else// 验证签名失败
					{
						response.getWriter().println("sign fail");
					}
				} else// 验证是否来自支付宝的通知失败
				{
					response.getWriter().println("response fail");
				}
			} else {
				response.getWriter().println("no notify message");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params) {

		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "false";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params, sign);

		// 写日志记录（若要调试，请取消下面两行注释）
		// String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign +
		// "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
		// AlipayCore.logResult(sWord);

		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @return 生成的签名结果
	 */
	public static boolean getSignVeryfy(Map<String, String> Params, String sign) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
		// 获取待签名字符串
		String preSignStr = AlipayCore.createLinkString(sParaNew);
		// 获得签名验证结果
		boolean isSign = false;
		if (AlipayConfig.sign_type.equals("RSA")) {
			isSign = RSA.verify(preSignStr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset);
		}
		return isSign;
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	public static String verifyResponse(String notify_id) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String partner = AlipayConfig.partner;
		String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;
		return checkUrl(veryfy_url);
	}

	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	public static String checkUrl(String urlvalue) {
		String inputLine = "";
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}
		return inputLine;
	}
}

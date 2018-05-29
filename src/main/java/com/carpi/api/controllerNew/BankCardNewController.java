package com.carpi.api.controllerNew;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arttraining.commons.util.JsonResult;
import com.arttraining.commons.util.TimeUtil;
import com.carpi.api.pojo.BankCard;
import com.carpi.api.service.BankCardService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("fs/bank")
public class BankCardNewController {

	@Autowired
	private BankCardService bankCardService;

	// 根据粉丝Id查询粉丝银行卡列表
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo<BankCard> selectList(HttpServletRequest request, HttpServletResponse response) {
		// 当前页
		String pageNum = request.getParameter("pg");
		// 每页的条数
		String pageSize = request.getParameter("ts");
		// 粉丝id
		String fensUserId = request.getParameter("uid");
		return bankCardService.selectAll(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				Integer.valueOf(fensUserId));
	}

	// 设置默认使用的银行卡(0：不使用；1：使用)
	@RequestMapping(value = "/xgApply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult updateApply(HttpServletRequest request, HttpServletResponse response) {
		// 银行卡状态(设为默认)
		String is_apply = request.getParameter("mr");
		// 粉丝id
		String fensUserId = request.getParameter("uid");

		BankCard bankCard = new BankCard();
		bankCard.setIsApply(Integer.valueOf(is_apply));
		bankCard.setFensUserId(Integer.valueOf(fensUserId));

		return bankCardService.updateApply(bankCard);
	}

	// 粉丝添加银行卡
	@RequestMapping(value = "/tjcard", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult addBlank(HttpServletRequest request, HttpServletResponse response) {
		// 粉丝id
		String fensUserId = request.getParameter("uid");
		// 银行卡号
		String cardNumber = request.getParameter("kh");
		// 银行
		String bank = request.getParameter("yh");
		// 开户支行
		String openBranch = request.getParameter("zh");
		// 姓名
		String name = request.getParameter("xm");
		// 手机号码
		String phone = request.getParameter("sh");

		BankCard bankCard = new BankCard();
		bankCard.setFensUserId(Integer.valueOf(fensUserId));
		bankCard.setCardNumber(cardNumber);
		bankCard.setBank(bank);
		bankCard.setCreateDate(TimeUtil.getTimeStamp());
		bankCard.setOpenBranch(openBranch);
		bankCard.setName(name);
		bankCard.setPhone(phone);

		return bankCardService.addBlank(bankCard);
	}

	// 粉丝修改银行卡
	// @RequestMapping(value = "/updateBlank", method = RequestMethod.POST, produces
	// = "application/json;charset=UTF-8")
	// @ResponseBody
	// public JsonResult updateBlank(BankCard bankCard) {
	// return bankCardService.updateBlank(bankCard);
	// }

}

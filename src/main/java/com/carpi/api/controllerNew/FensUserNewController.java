package com.carpi.api.controllerNew;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arttraining.commons.util.JsonResult;
import com.carpi.api.pojo.FensComputingPower;
import com.carpi.api.pojo.FensTeam;
import com.carpi.api.pojo.FensUser;
import com.carpi.api.service.FensUserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user/fens")
public class FensUserNewController {

	@Autowired
	private FensUserService fensUserService;

	// 注册
	@RequestMapping(value = "/zc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult register(HttpServletRequest request, HttpServletResponse response) {
		// 手机验证码类型
		String code_type = request.getParameter("yan_ma");
		// 身份证号码
		String IDcardNumber = request.getParameter("sf_cd");
		// 手机验证码
		String code = request.getParameter("ym");
		// 手机号码
		String phone = request.getParameter("sh");
		// 密码
		String pwd = request.getParameter("mn");
		// 邀请人手机号码
		String refereePhone = request.getParameter("yqrph");
		// 用户姓名
		String name = request.getParameter("xn");

		FensUser fensUser = new FensUser();
		fensUser.setPhone(phone);
		fensUser.setName(name);
		fensUser.setPwd(pwd);
		fensUser.setRefereePhone(refereePhone);

		return fensUserService.register(fensUser, code_type, code, IDcardNumber);
	}

	// 登入(手机号和密码)
	@RequestMapping(value = "/dl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult login(HttpServletRequest request, HttpServletResponse response) {
		// 手机号码
		String phone = request.getParameter("sh");
		// 密码
		String pwd = request.getParameter("mn");

		FensUser fensUser = new FensUser();
		fensUser.setPhone(phone);
		fensUser.setPwd(pwd);

		return fensUserService.login(fensUser);
	}
	
	@RequestMapping(value = "/dl2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult login2(HttpServletRequest request, HttpServletResponse response) {
		// 手机号码
		String phone = request.getParameter("sh");
		// 验证码
		String code = request.getParameter("ym");
		//验证码类型
		String code_type = request.getParameter("ym_tp");

		return fensUserService.login2(phone,code,code_type);
	}

	// 登入(短信验证码)
	
	
	// 根据ID查个人信息
	@RequestMapping(value = "/userxx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FensUser info(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("uid");
		FensUser fensUser = new FensUser();
		fensUser.setId(Integer.valueOf(id));
		return fensUserService.info(fensUser);
	}

	// 忘记密码
	@RequestMapping(value = "/wjmm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult forgetPwd(HttpServletRequest request, HttpServletResponse response) {
		// 手机验证码类型
		String code_type = request.getParameter("yan_ma");
		// 手机验证码
		String code = request.getParameter("ym");
		// 手机号码
		String phone = request.getParameter("sh");
		// 新密码
		String pwd = request.getParameter("xin_mm");
		FensUser fensUser = new FensUser();
		fensUser.setPhone(phone);
		fensUser.setPwd(pwd);
		return fensUserService.forgetPwd(fensUser, code_type, code);
	}

	// 修改密码
	@RequestMapping(value = "/xgmm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult updatePwd(HttpServletRequest request, HttpServletResponse response) {
		// 旧密码
		String OldPwd = request.getParameter("old_mm");
		// 新密码
		String newPwd = request.getParameter("new_mm");
		// 粉丝id (fensUserId)
		String fensUserId = request.getParameter("uid");

		return fensUserService.updatePwd(OldPwd, newPwd, Integer.valueOf(fensUserId));
	}

	// 设置交易密码
	@RequestMapping(value = "/szjymm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult jiaoYi(HttpServletRequest request, HttpServletResponse response) {
		// 交易密码
		String capitalPwd = request.getParameter("old_jymm");
		// 粉丝id
		String id = request.getParameter("uid");
		// 手机号码
		String phone = request.getParameter("sh");
		FensUser fensUser = new FensUser();
		fensUser.setId(Integer.valueOf(id));
		fensUser.setPhone(phone);
		fensUser.setCapitalPwd(capitalPwd);
		return fensUserService.jiaoYi(fensUser);
	}

	// 修改交易密码
	@RequestMapping(value = "/xgjymm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult updateJiaoYi(HttpServletRequest request, HttpServletResponse response) {
		// 旧交易密码
		String oldCapitalPwd = request.getParameter("old_jymm");
		// 新交易密码
		String newCapitalPwd = request.getParameter("new _jymm");
		// 粉丝id
		String fensUserId = request.getParameter("uid");

		return fensUserService.updateJiaoYi(oldCapitalPwd, newCapitalPwd, Integer.valueOf(fensUserId));
	}

	// 修改信息
	@RequestMapping(value = "/xgxx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult updateInfo(HttpServletRequest request, HttpServletResponse response) {
		// 手机号码
		String phone = request.getParameter("sh");
		FensUser fensUser = new FensUser();
		fensUser.setPhone(phone);
		return fensUserService.updateInfo2(fensUser);
	}

	// 粉丝团列表
	@RequestMapping(value = "/fentlb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo<FensTeam> slectAll(HttpServletRequest request, HttpServletResponse response) {
		// 当前页
		String page = request.getParameter("pg");
		// 每页多少条
		String num = request.getParameter("ts");
		// 粉丝id
		String fensUserId = request.getParameter("uid");
		// type传all查询所有
		String type = request.getParameter("tp");

		return fensUserService.selectAll(Integer.valueOf(page), Integer.valueOf(num), Integer.valueOf(fensUserId),
				type);
	}

	// 粉丝算力列表明细
	@RequestMapping(value = "/sllb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo<FensComputingPower> suanlilist(HttpServletRequest request, HttpServletResponse response) {
		// 当前页
		String page = request.getParameter("pg");
		// 每页多少条
		String num = request.getParameter("ts");
		// 粉丝id
		String fensUserId = request.getParameter("uid");

		return fensUserService.selectComputingPower(Integer.valueOf(page), Integer.valueOf(num),
				Integer.valueOf(fensUserId));
	}

	// 添加粉丝算力明细
	// @RequestMapping(value = "/add", method = RequestMethod.POST, produces =
	// "application/json;charset=UTF-8")
	// @ResponseBody
	// public JsonResult addselectComputingPower(FensComputingPower
	// fensComputingPower) {
	// return fensUserService.addselectComputingPower(fensComputingPower);
	// }

	// 修改粉丝算力
	// @RequestMapping(value = "/update", method = RequestMethod.POST, produces =
	// "application/json;charset=UTF-8")
	// @ResponseBody
	// public JsonResult updateComputingPower(FensComputingPower fensComputingPower)
	// {
	// return fensUserService.updateComputingPower(fensComputingPower);
	// }

	// 粉丝算力值和
	@RequestMapping(value = "/he", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult selectSum(HttpServletRequest request, HttpServletResponse response) {
		// 粉丝id
		String fensUserId = request.getParameter("uid");

		return fensUserService.selectSum(Integer.valueOf(fensUserId));
	}

	// 粉丝团列表2
	@RequestMapping(value = "/list2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo<FensUser> slectAllUser(HttpServletRequest request, HttpServletResponse response) {
		// 当前页
		String page = request.getParameter("pg");
		// 每页多少条
		String num = request.getParameter("ts");
		// 手机号码
		String phone = request.getParameter("sh");
		// type传all查询所有
		String type = request.getParameter("tp");

		return fensUserService.selectAllUser(Integer.valueOf(page), Integer.valueOf(num), phone, type);
	}

}
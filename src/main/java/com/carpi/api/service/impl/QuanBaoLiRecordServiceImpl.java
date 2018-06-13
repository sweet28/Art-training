package com.carpi.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arttraining.commons.util.JsonResult;
import com.arttraining.commons.util.TimeUtil;
import com.carpi.api.dao.BankCardMapper;
import com.carpi.api.dao.FensUserMapper;
import com.carpi.api.dao.QuanBaoLiMapper;
import com.carpi.api.dao.QuanBaoLiRecordMapper;
import com.carpi.api.dao.QuanDakuanRecordMapper;
import com.carpi.api.pojo.BankCard;
import com.carpi.api.pojo.FensUser;
import com.carpi.api.pojo.QuanBaoLi;
import com.carpi.api.pojo.QuanBaoLiRecord;
import com.carpi.api.pojo.QuanDakuanRecord;
import com.carpi.api.service.QuanBaoLiRecordService;

@Service
public class QuanBaoLiRecordServiceImpl implements QuanBaoLiRecordService {

	@Autowired
	private QuanBaoLiMapper quanBaoLiMapper;

	@Autowired
	private QuanBaoLiRecordMapper quanBaoLiRecordMapper;

	@Autowired
	private QuanDakuanRecordMapper quanDakuanRecordMapper;

	@Autowired
	private FensUserMapper fensUserDao;

	@Autowired
	private BankCardMapper bankCardDao;

	// 券宝理列表
	@Override
	public JsonResult selectList() {
		List<QuanBaoLi> list = quanBaoLiMapper.selectList();
		if (list.size() > 0) {
			return JsonResult.ok();
		}
		return JsonResult.build(500, "无信息");
	}

	// 券宝理个人订单
	@Override
	public JsonResult selectOne(QuanBaoLiRecord quanBaoLiRecord) {
		List<QuanBaoLiRecord> list = quanBaoLiRecordMapper.selectList(quanBaoLiRecord);
		if (!list.isEmpty()) {
			return JsonResult.ok(list);
		} else {
			return JsonResult.build(500, "无订单信息");
		}
	}

	// 查询券详情
	@Override
	public JsonResult xiangQing(Integer id) {
		if (id == null) {
			return JsonResult.build(500, "请选择订单");
		}
		// 查询订单信息
		QuanBaoLiRecord quanBaoLiRecord = quanBaoLiRecordMapper.selectByPrimaryKey(id);
		if (StringUtils.isEmpty(quanBaoLiRecord)) {
			return JsonResult.build(500, "无订单信息");
		}
		// 查询打款信息(根据券id)
		// QuanDakuanRecord quanDakuanRecord =
		// quanDakuanRecordMapper.selectQuan(quanBaoLiRecord.getId());

		// if (StringUtils.isEmpty(quanDakuanRecord)) {
		// return JsonResult.build(500, "无打款信息");
		// }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ddxx", quanBaoLiRecord);
		// map.put("dkxx", quanDakuanRecord);
		return JsonResult.ok(map);
	}

	/**
	 * 出场，两个人或多个人给出场人打款（匹配上了） order_type 1：购买待匹配 2：购买待打款 3：收益进行中（已打款） 4：周期结束 5：提取带匹配
	 * 6：提取匹配待打款 7：收益完成
	 */
	@Override
	public JsonResult quanChuChang(Integer id, Integer type, Integer dakuantype) {
		// 先查询出场人的用户信息
		// FensUser fensUser = fensUserDao.selectByPrimaryKey(fensUserId);
		// if (StringUtils.isEmpty(fensUser)) {
		// return JsonResult.build(500, "不是本人操作，请重新登入");
		// }
		// 出场人为收款方（收款人id）
		QuanBaoLiRecord quanBaoLiRecord = quanBaoLiRecordMapper.selectById(id, type);
		if (StringUtils.isEmpty(quanBaoLiRecord)) {
			return JsonResult.build(500, "不存在此券信息");
		}
		// 查询该券的打款人id
		List<QuanDakuanRecord> list = quanDakuanRecordMapper.selectlist(quanBaoLiRecord.getId(), dakuantype);

		if (list.isEmpty()) {
			return JsonResult.build(500, "不存在此券信息1");
		}
		List<Map<String, Object>> list2 = new ArrayList<>();
		for (QuanDakuanRecord quanDakuanRecord : list) {
			Map<String, Object> map = new HashMap<>();
			// 打款人id
			map.put("da_id", quanDakuanRecord.getDakuangId());
			// 打款人名字
			map.put("da_name", quanDakuanRecord.getDaName());
			map.put("id", quanDakuanRecord.getId());
			map.put("quan", quanBaoLiRecord);
			map.put("pip", quanDakuanRecord);
			list2.add(map);
			// 打款人的银行卡信息掉其他接口
		}
		return JsonResult.ok(list2);
	}

	// 根据id查询匹配订单信息
	@Override
	public JsonResult pipeixiangQing(Integer id) {
		if (id == null) {
			return JsonResult.build(500, "无匹配信息");
		}
		QuanDakuanRecord quanDakuanRecord = quanDakuanRecordMapper.selectByPrimaryKey(id);
		if (StringUtils.isEmpty(quanDakuanRecord)) {
			return JsonResult.build(500, "无匹配信息2");
		}
		return JsonResult.ok(quanDakuanRecord);
	}

	// 券保理买家付款
	@Override
	public JsonResult fuk(Integer pipeiId) {
		if(StringUtils.isEmpty(pipeiId)) {
			return JsonResult.build(500, "请选择打款信息");
		}
		QuanDakuanRecord quanDakuanRecord = new QuanDakuanRecord();
		//2：已打款待收款（待确认)
		quanDakuanRecord.setDakuanType(2); 
		quanDakuanRecord.setId(pipeiId);
		//打款时间
		quanDakuanRecord.setDakuanDate(TimeUtil.getTimeStamp());
		int reult = quanDakuanRecordMapper.updateByPrimaryKeySelective(quanDakuanRecord);
		if (reult != 1) {
			return JsonResult.build(500, "打款失败");
		}
		return JsonResult.ok();
	}

	//券保理卖家收款
	@Override
	public JsonResult shouk(Integer pipeiId) {
		if(StringUtils.isEmpty(pipeiId)) {
			return JsonResult.build(500, "请选择打款信息");
		}
		QuanDakuanRecord quanDakuanRecord = new QuanDakuanRecord();
		//已确认收款
		quanDakuanRecord .setDakuanType(3);
		quanDakuanRecord.setId(pipeiId);
		quanDakuanRecord.setShoukuanDate(TimeUtil.getTimeStamp());
		int reult = quanDakuanRecordMapper.updateByPrimaryKeySelective(quanDakuanRecord);
		if (reult != 1) {
			return JsonResult.build(500, "收款失败");
		}
		return JsonResult.ok();
	}

}

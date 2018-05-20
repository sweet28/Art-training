package com.carpi.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arttraining.commons.util.JsonResult;
import com.arttraining.commons.util.ServerLog;
import com.arttraining.commons.util.TimeUtil;
import com.carpi.api.dao.APoolMapper;
import com.carpi.api.dao.BPoolMapper;
import com.carpi.api.dao.FensWalletMapper;
import com.carpi.api.pojo.APool;
import com.carpi.api.pojo.BPool;
import com.carpi.api.pojo.FensWallet;
import com.carpi.api.pojo.FoneyRecord;
import com.carpi.api.service.FensWalletService;
import com.carpi.api.service.PoolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PoolServiceImpl implements PoolService {
	@Autowired
	private APoolMapper apoolMapper;

	@Autowired
	private BPoolMapper bpoolMapper;

	@Autowired
	private FensWalletMapper fensWalletMapper;

	@Autowired
	private FensWalletService fensWalletService;

	// a矿池列表
	@Override
	public PageInfo<APool> selectApool(Integer page, Integer num, Integer fensUserId) {
		PageHelper.startPage(page, num);
		List<APool> list = apoolMapper.selectApool(fensUserId);
		PageInfo<APool> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	// b矿池列表
	@Override
	public PageInfo<BPool> selectBpool(Integer page, Integer num, Integer fensUserId) {
		PageHelper.startPage(page, num);
		List<BPool> list = bpoolMapper.selectBpool(fensUserId);
		PageInfo<BPool> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	// 解冻(A)
	// 矿池里把该粉丝的cpa数 转出到他钱包下面 钱包的可用余额增加 钱包的转账记录也增加一条记录
	@Override
	public JsonResult thawAMiner(APool aPool) {
		// 根据粉丝Id查询该粉丝对应的矿池信息
		List<APool> list = apoolMapper.selectApool(aPool.getFensUserId());
		if (list != null && list.size() > 0) {
			// 钱包的可用余额增加相应数目的cpa
			// 根据粉丝Id查询该粉丝对应钱包信息
			/*List<FensWallet> walletsList = fensWalletMapper.selectAll(aPool.getFensUserId());
			// 钱包为空，请创建
			if (walletsList == null && walletsList.size() == 0) {
				return JsonResult.build(500, "请先创建钱包");
			}*/
			
			FensWallet fensWallet = fensWalletMapper.selectAll(aPool.getFensUserId());
			if (fensWallet == null) {
				return JsonResult.build(500, "钱包不存在");
			}

			APool aPool2 = list.get(0);
			// 判断矿池cpa是否充足
			if (aPool.getCpaCount() > aPool2.getCpaCount()) {
				return JsonResult.build(500, "您的余额不足");
			}
			// 解冻后矿池减掉相应的cpa
			Double cpa = aPool2.getCpaCount() - aPool.getCpaCount();
			// 转出时间
			Date date1 = TimeUtil.getTimeStamp();
			APool pool = new APool();
			pool.setCpaCount(cpa);
			pool.setId(aPool2.getId());
			// 更新cpa(a矿池)
			int result = apoolMapper.updateByPrimaryKeySelective(pool);
			if (result != 1) {
				ServerLog.getLogger().warn("更新矿池失败，粉丝id：" + aPool2.getFensUserId());
			}

			// 钱包添加cpa
//			FensWallet wallet = walletsList.get(0);
			Double ablecpa = fensWallet.getAbleCpa() + aPool.getCpaCount();
			// 到账时间
			Date date2 = TimeUtil.getTimeStamp();
			FensWallet wallet2 = new FensWallet();
			// 钱包可用余额增加
			wallet2.setAbleCpa(ablecpa);
			wallet2.setId(fensWallet.getId());
			// 更新钱包可用cpa
			int result2 = fensWalletMapper.updateByPrimaryKeySelective(wallet2);
			if (result2 != 1) {
				ServerLog.getLogger().warn("更新钱包可用失败，粉丝id：" + aPool2.getFensUserId());
			}

			// 钱包转账记录表添加一条记录
			FoneyRecord moneyRecord = new FoneyRecord();
			moneyRecord.setFensUserId(aPool.getFensUserId());
			// 交易金额
			moneyRecord.setPayment(aPool.getCpaCount());
			moneyRecord.setReceiveDate(date2);
			moneyRecord.setSendDate(date1);
			// 转账类型1 代表接收 2代表转出
			moneyRecord.setPaymentType(1);
			return fensWalletService.addWalletRecord(moneyRecord);
		}

		return JsonResult.build(500, "不存在矿池信息");
	}

	// 解冻(B)
	@Override
	public JsonResult thawBMiner(BPool bPool) {
		// 根据粉丝Id查询该粉丝对应的矿池信息
		List<BPool> list = bpoolMapper.selectBpool(bPool.getFensUserId());
		if (list != null && list.size() > 0) {
			// 钱包的可用余额增加相应数目的cpa
			// 根据粉丝Id查询该粉丝对应钱包信息
			/*List<FensWallet> walletsList = fensWalletMapper.selectAll(bPool.getFensUserId());
			// 钱包为空，请创建
			if (walletsList == null && walletsList.size() == 0) {
				return JsonResult.build(500, "请先创建钱包");
			}*/

			FensWallet fensWallet = fensWalletMapper.selectAll(bPool.getFensUserId());
			if (fensWallet == null) {
				return JsonResult.build(500, "钱包不存在");
			}
			BPool bPool2 = list.get(0);
			// 判断矿池cpa是否充足
			if (bPool.getCpaCount() > bPool2.getCpaCount()) {
				return JsonResult.build(500, "您的余额不足");
			}
			// 解冻后矿池减掉相应的cpa
			Double cpa = bPool2.getCpaCount() - bPool.getCpaCount();
			// 转出时间
			Date date1 = TimeUtil.getTimeStamp();
			BPool pool = new BPool();
			pool.setCpaCount(cpa);
			pool.setId(bPool2.getId());
			// 更新cpa(a矿池)
			int result = bpoolMapper.updateByPrimaryKeySelective(pool);
			if (result != 1) {
				ServerLog.getLogger().warn("更新矿池失败，粉丝id：" + bPool2.getFensUserId());
			}

			// 钱包添加cpa
//			FensWallet wallet = walletsList.get(0);
			Double ablecpa = fensWallet.getAbleCpa() + bPool.getCpaCount();
			// 到账时间
			Date date2 = TimeUtil.getTimeStamp();
			FensWallet wallet2 = new FensWallet();
			// 钱包可用余额增加
			wallet2.setAbleCpa(ablecpa);
			wallet2.setId(fensWallet.getId());
			// 更新钱包可用cpa
			int result2 = fensWalletMapper.updateByPrimaryKeySelective(wallet2);
			if (result2 != 1) {
				ServerLog.getLogger().warn("更新钱包可用失败，粉丝id：" + bPool2.getFensUserId());
			}

			// 钱包转账记录表添加一条记录
			FoneyRecord moneyRecord = new FoneyRecord();
			moneyRecord.setFensUserId(bPool.getFensUserId());
			// 交易金额
			moneyRecord.setPayment(bPool.getCpaCount());
			moneyRecord.setReceiveDate(date2);
			moneyRecord.setSendDate(date1);
			// 转账类型1 代表接收 2代表转出
			moneyRecord.setPaymentType(1);
			return fensWalletService.addWalletRecord(moneyRecord);
		}
		return JsonResult.build(500, "不存在矿池信息");
	}

}
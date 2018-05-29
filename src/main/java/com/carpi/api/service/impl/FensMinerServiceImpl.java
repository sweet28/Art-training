package com.carpi.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arttraining.commons.util.JsonResult;
import com.arttraining.commons.util.ServerLog;
import com.arttraining.commons.util.TimeUtil;
import com.carpi.api.dao.BankCardMapper;
import com.carpi.api.dao.FensMinerMapper;
import com.carpi.api.dao.FensUserMapper;
import com.carpi.api.dao.FensWalletMapper;
import com.carpi.api.pojo.BPool;
import com.carpi.api.pojo.BankCard;
import com.carpi.api.pojo.FensMiner;
import com.carpi.api.pojo.FensUser;
import com.carpi.api.pojo.FensWallet;
import com.carpi.api.pojo.FoneyRecord;
import com.carpi.api.service.FensMinerService;
import com.carpi.api.service.FensWalletService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FensMinerServiceImpl implements FensMinerService {

	@Autowired
	private FensMinerMapper fensMinerMapper;
	@Autowired
	private FensWalletMapper fensWalletMapper;
	@Autowired
	private FensWalletService fensWalletService;
	@Autowired
	private FensUserMapper fensUserMapper;
	@Autowired
	private BankCardMapper bankCardMapper;

	// 根据粉丝id查询矿机
	@Override
	public PageInfo<FensMiner> selectMinner(Integer page, Integer row, Integer fensUserId) {
		PageHelper.startPage(page, row);
		List<FensMiner> list = fensMinerMapper.selectMiner(fensUserId);
		PageInfo<FensMiner> pageInfo = new PageInfo<FensMiner>(list);
		return pageInfo;
	}
	
	// 根据粉丝id查询A矿机
	@Override
	public PageInfo<FensMiner> selectAMinner(Integer page, Integer row, Integer fensUserId) {
		PageHelper.startPage(page, row);
		List<FensMiner> list = fensMinerMapper.selectAMiner(fensUserId);
		PageInfo<FensMiner> pageInfo = new PageInfo<FensMiner>(list);
		return pageInfo;
	}
	
	// 根据粉丝id查询A矿机矿池
	@Override
	public PageInfo<FensMiner> selectAMinnerKC(Integer page, Integer row, Integer fensUserId) {
		PageHelper.startPage(page, row);
		List<FensMiner> list = fensMinerMapper.selectAMinerKC(fensUserId);
		PageInfo<FensMiner> pageInfo = new PageInfo<FensMiner>(list);
		return pageInfo;
	}
	
	// 根据粉丝id查询B矿机
	@Override
	public PageInfo<FensMiner> selectBMinner(Integer page, Integer row, Integer fensUserId) {
		PageHelper.startPage(page, row);
		List<FensMiner> list = fensMinerMapper.selectBMiner(fensUserId);
		PageInfo<FensMiner> pageInfo = new PageInfo<FensMiner>(list);
		return pageInfo;
	}
	
	// 根据粉丝id查询B矿机矿池
	@Override
	public PageInfo<FensMiner> selectBMinnerKC(Integer page, Integer row, Integer fensUserId) {
		PageHelper.startPage(page, row);
		List<FensMiner> list = fensMinerMapper.selectBMinerKC(fensUserId);
		PageInfo<FensMiner> pageInfo = new PageInfo<FensMiner>(list);
		return pageInfo;
	}

	// 新增粉丝矿机
	@Override
	public JsonResult addMiner(FensMiner fensMiner) {
		int result = fensMinerMapper.insertSelective(fensMiner);
		if (result == 1) {
			return JsonResult.ok();
		}
		return JsonResult.build(500, "添加失败");
	}

	// 修改粉丝矿机
	@Override
	public JsonResult updateMiner(FensMiner fensMiner) {
		int result = fensMinerMapper.updateByPrimaryKeySelective(fensMiner);
		if (result == 1) {
			return JsonResult.ok();
		}
		return JsonResult.build(500, "更新失败");
	}
	
	//解冻矿机收益
	@Override
	public JsonResult thawABMiner(FensMiner miner1) {
		// 根据粉丝Id查询该粉丝对应的矿池信息
		FensMiner miner = fensMinerMapper.selectByPrimaryKey(miner1.getId());
		
		FensUser fensUser = fensUserMapper.selectByPrimaryKey(miner.getFensUserId());
		// 判断是否存在该接单人
		if (fensUser == null) {
			return JsonResult.build(500, "交易失败，不存在此人");
		}

		// 查询身份证是否认证
		if (Integer.valueOf(fensUser.getAttachment()) != 1) {
			return JsonResult.build(500, "身份证未认证");
		}
		// 银行卡
		List<BankCard> list = bankCardMapper.selectAll(miner.getFensUserId());
		if (list.size() <= 0) {
			return JsonResult.build(500, "请绑定银行卡");
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (miner != null) {
			Date dd = TimeUtil.getTimeStamp();
			String sqDT = miner.getAttachment();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //获取String类型的时间
			if(sqDT.isEmpty()){
				sqDT = sdf.format(miner.getCreateDate());
			}
			
//			String nowDT = dd.toString();
			
			Date sqDD = TimeUtil.strToDateDayByFormat(sqDT);

			System.out.println("sqDT:::"+sqDT+"-------sqDD::"+sqDD.toString()+"--sqDT.gettime:::"+sqDD.getTime()+"------dd2string:::"+dd.toString()+"------dd.getTIme:"+dd.getTime());
			
			long a = TimeUtil.isOverTime(dd, sqDD);
			System.out.println("-------chazhi::::"+a);
			double b = a/(60*60*24);
			System.out.println("-------chazhi::bbb:"+b);
			
			if(b >= 1){
//				long nowDate = Date.parse(dd.toString());
	    		double rundate = dd.getTime() - miner.getCreateDate().getTime();
	    		rundate = rundate/(1000*60*60*24);
	    		
	    		if(rundate >= 15){
	    			rundate =15;
	    		}
	    		
	    		System.out.println(rundate+"---"+rundate+"------");

	    		double nowZSY;
	    		double syyz = 0;
	    		double beishu = 1;
	    		
	    		if(miner.getMinerType()==1){
		    		if(Integer.parseInt(miner.getBak1())==1){
		    			syyz=11;
		    		}else if(Integer.parseInt(miner.getBak1())==2){
		    			syyz=115;
		    			beishu = 0.2;
		    		}else if(Integer.parseInt(miner.getBak1())==3){
		    			syyz=1150;
		    			beishu = 0.1;
		    		}else if(Integer.parseInt(miner.getBak1())==4){
		    			syyz=6000;
		    			beishu = 0.05;
		    		}
	    		}else if(miner.getMinerType()==2){

	    			beishu = 0.2;
	    			if(Integer.parseInt(miner.getBak1())==1){
		    			syyz=5.5;
		    		}else if(Integer.parseInt(miner.getBak1())==2){
		    			syyz=55;
		    		}else if(Integer.parseInt(miner.getBak1())==3){
		    			syyz=550;
		    		}
	    		}
	    		
	    		nowZSY = rundate * (syyz/15);
	    		Double yhdSY,kySY;
	    		
	    		yhdSY = miner.getTotalRevenue();
	    		kySY = (nowZSY - yhdSY)*beishu;
	    		
	    		System.out.println("nowzsy:"+nowZSY+"---yhdSY:"+yhdSY);
	    		
	    		if(kySY < 1){
	    			return JsonResult.build(500, "收益少于1个CPA时，不能转入钱包。");
	    		}
	    		
	    		//解冻收益需要给领导人分红,同时更新矿机、钱包数据
	    		FensMiner fm = new FensMiner();
	    		fm.setTotalRevenue(kySY+yhdSY);
	    		fm.setId(miner.getId());
	    		fm.setFensUserId(miner.getFensUserId());
	    		fm.setAttachment(dd.toString());
	    		
	    		int result = fensMinerMapper.updateByPrimaryKeySelective(fm);
				
				if (result != 1) {
					ServerLog.getLogger().warn("更新矿池失败，粉丝id：" + fm.getFensUserId());
				}

				FensWallet fensWallet = fensWalletMapper.selectAll(miner.getFensUserId());
				if (fensWallet == null) {
					return JsonResult.build(500, "钱包不存在");
				}
				
				// 钱包添加cpa
				Double ablecpa = fensWallet.getAbleCpa() + kySY;
				// 到账时间
				Date date2 = TimeUtil.getTimeStamp();
				FensWallet wallet2 = new FensWallet();
				// 钱包可用余额增加
				wallet2.setAbleCpa(ablecpa);
				wallet2.setCpaCount(fensWallet.getCpaCount()+kySY);
				wallet2.setId(fensWallet.getId());
				// 更新钱包可用cpa
				int result2 = fensWalletMapper.updateByPrimaryKeySelective(wallet2);
				if (result2 != 1) {
					ServerLog.getLogger().warn("更新钱包可用失败，粉丝id：" + miner.getFensUserId());
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FensUser fuser = fensUserMapper.selectByPrimaryKey(miner.getFensUserId());
				if(fuser!=null){
					FensUser jsr = new FensUser();
					jsr.setPhone(fuser.getRefereePhone());
					FensUser jsrUser = fensUserMapper.selectRegister(jsr);
					if(jsrUser != null){
						//介绍人钱包
						FensWallet ldrWallet = fensWalletMapper.selectAll(jsrUser.getId());
						
						// 钱包添加cpa
						Double ablecpa2 = ldrWallet.getAbleCpa() + kySY*0.01;
						// 到账时间
						FensWallet ldrWallet2 = new FensWallet();
						// 钱包可用余额增加
						ldrWallet2.setAbleCpa(ablecpa2);
						ldrWallet2.setCpaCount(ldrWallet.getCpaCount()+kySY*0.01);
						ldrWallet2.setId(ldrWallet.getId());
						// 更新钱包可用cpa
						int result3 = fensWalletMapper.updateByPrimaryKeySelective(ldrWallet2);
						if (result3 != 1) {
							ServerLog.getLogger().warn("更新钱包可用失败，粉丝id：" + miner.getFensUserId());
						}
					}
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 钱包转账记录表添加一条记录
				FoneyRecord moneyRecord = new FoneyRecord();
				moneyRecord.setFensUserId(miner.getFensUserId());
				// 交易金额
				moneyRecord.setPayment(kySY);
				moneyRecord.setReceiveDate(date2);
				moneyRecord.setSendDate(date2);
				// 转账类型1 代表接收 2代表转出
				moneyRecord.setPaymentType(1);
				return fensWalletService.addWalletRecord(moneyRecord);
			}else{
				return JsonResult.build(500, "今天已经收取过CPA收益了，隔天再来。");
			}
		}
		return JsonResult.build(500, "不存在矿池信息");
	}
}

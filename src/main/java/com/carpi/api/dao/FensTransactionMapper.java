package com.carpi.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carpi.api.pojo.FensTransaction;

public interface FensTransactionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FensTransaction record);

    int insertSelective(FensTransaction record);

    FensTransaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FensTransaction record);

    int updateByPrimaryKey(FensTransaction record);
    
    //查询粉丝的交易记录(可根据粉丝id查询)
    List<FensTransaction> selectFensRecord(FensTransaction fensTransaction);
    //查询成交交易
	List<FensTransaction> selectCJFensRecord(FensTransaction fensTransaction);

	List<FensTransaction> selectCJFensRecordByID(Integer fensUserId);
	//查询粉丝待付款交易
	List<FensTransaction> selectDFKRecord(FensTransaction fensTransaction);
	//查询粉丝待收款交易
	List<FensTransaction> selectDSKRecord(FensTransaction fensTransaction);
	//查询粉丝完成交易
	List<FensTransaction> selectYWCRecord(FensTransaction fensTransaction);
	//查询粉丝交易
	List<FensTransaction> selectGDRecord(FensTransaction fensTransaction);

	Map getAllBlockCPA(@Param("uid")Integer uid, @Param("id")Integer id);
	
	//查询订单（买单，卖单）
	List<FensTransaction> selectjl(@Param("phone") String phone,@Param("traderType") Integer traderType);
	
	//待审核
	List<FensTransaction> selectDSH(Integer fensUserId);
	
	//粉丝交易量(当天)
	Double JYLsum();
	
	Double jylYesterdaySum();

	FensTransaction selectByPrimaryKeyDESC(Integer fensUserId);
	
	//查询买单总数量 
	int selectBuyCount(@Param("fensUserId") Integer fensUserId);
	
	//查询买单cap总数量
	Double selectBuyCpaCount(@Param("fensUserId") Integer fensUserId);
	
	//查询卖单总数量 
	int selectSellCount(@Param("traderId") Integer traderId);
	
	//查询卖单cpa总数量 
	Double selectSellCpaCount(@Param("traderId") Integer traderId);
	
}
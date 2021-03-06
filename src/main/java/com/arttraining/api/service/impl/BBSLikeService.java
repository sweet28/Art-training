package com.arttraining.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arttraining.api.dao.BBSLikeMapper;
import com.arttraining.api.dao.BBSMapper;
import com.arttraining.api.pojo.BBSLike;
import com.arttraining.api.service.IBBSLikeService;

@Service("bbsLikeService")
public class BBSLikeService implements IBBSLikeService {
	@Resource
	private BBSLikeMapper bbsLikeDao;
	@Resource
	private BBSMapper bbsDao;

	@Override
	public int insertBBSLikeSelective(BBSLike record) {
		// TODO Auto-generated method stub
		return this.bbsLikeDao.insertSelective(record);
	}

	@Override
	public BBSLike selectBBSLikeByUidAndFid(Integer fid, Integer uid) {
		// TODO Auto-generated method stub
		return this.bbsLikeDao.selectSelectiveByUidAndFid(fid, uid);
	}

	@Override
	public int updateBBSLikeSelective(BBSLike record) {
		// TODO Auto-generated method stub
		return this.bbsLikeDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public void insertAndUpdateBBS(BBSLike record, Integer id) {
		// TODO Auto-generated method stub
		this.bbsLikeDao.insertSelective(record);
		this.bbsDao.updateBBSLikeNumByPrimaryKey(id);
	}

}

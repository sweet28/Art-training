package com.arttraining.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arttraining.api.bean.HomeLikeOrCommentBean;
import com.arttraining.api.bean.HomePageStatusesBean;
import com.arttraining.api.bean.StatusesShowBean;
import com.arttraining.api.dao.BBSAttachmentMapper;
import com.arttraining.api.dao.BBSMapper;
import com.arttraining.api.dao.UserStuMapper;
import com.arttraining.api.pojo.BBS;
import com.arttraining.api.pojo.BBSAttachment;
import com.arttraining.api.pojo.UserStu;
import com.arttraining.api.service.IBBSService;

@Service("bbsService")
public class BBSService implements IBBSService {
	@Resource
	private BBSMapper bbsDao;
	@Resource
	private BBSAttachmentMapper bbsAttDao;
	@Resource
	private UserStuMapper userStuDao;

	@Override
	public BBS getBBSById(Integer id) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertBBSAndInsertAttr(BBS bbs, BBSAttachment bbsAttr, UserStu user) {
		// TODO Auto-generated method stub
		this.bbsDao.insertOneBBSSelective(bbs);
		int id = bbs.getId();
		if(bbsAttr!=null) {
			bbsAttr.setForeignKey(id);
			this.bbsAttDao.insertSelective(bbsAttr);
		}
		this.userStuDao.updateNumberBySelective(user);
	}

	@Override
	public HomeLikeOrCommentBean getIsLikeOrCommentOrAtt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectIsLikeOrCommentOrAtt(map);
	}

	@Override
	public StatusesShowBean getOneBBSById(Integer id) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectOneBBSByid(id);
	}

	@Override
	public List<HomePageStatusesBean> getBBSListByHomepage(Integer offset,
			Integer limit) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectBBSListByHomepage(offset, limit);
	}

	@Override
	public List<HomePageStatusesBean> getBBSListByUid(Integer uid,
			Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectBBSListByUid(uid, offset, limit);
	}

	@Override
	public int updateBBSNumber(BBS record) {
		// TODO Auto-generated method stub
		return this.bbsDao.updateNumberBySelective(record);
	}

	@Override
	public List<HomePageStatusesBean> getBBSListByMyComment(Integer uid,
			Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectBBSListByMyComment(uid, offset, limit);
	}

	@Override
	public String getBBSAttrInfoById(Integer id) {
		// TODO Auto-generated method stub
		return this.bbsDao.selectBBSAttrInfoById(id);
	}
	
}

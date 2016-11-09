package com.arttraining.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arttraining.api.bean.HomeLikeOrCommentBean;
import com.arttraining.api.bean.HomePageStatusesBean;
import com.arttraining.api.dao.StatusesAttachmentMapper;
import com.arttraining.api.dao.StatusesMapper;
import com.arttraining.api.pojo.Statuses;
import com.arttraining.api.pojo.StatusesAttachment;
import com.arttraining.api.service.IStatusesService;

@Service("statusesService")
public class StatusesService implements IStatusesService {
	@Resource
	private StatusesMapper statusesDao;
	@Resource
	private StatusesAttachmentMapper statusesAttDao;
	
	@Override
	public Statuses getStatusesById(Integer id) {
		// TODO Auto-generated method stub
		return this.statusesDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertStatusAndUpdateAttr(Statuses status,
			StatusesAttachment statusAttr) {
		// TODO Auto-generated method stub
		this.statusesDao.insertOneStatusSelective(status);
		int id = status.getId();
		if(statusAttr!=null) {
			statusAttr.setForeignKey(id);
			this.statusesAttDao.insertSelective(statusAttr);
		}
	}

	@Override
	public List<HomePageStatusesBean> selectStatusesListByGid(Integer gid,
			Integer limit) {
		// TODO Auto-generated method stub
		return this.statusesDao.selectStatusesListByGid(gid, limit);
	}

	@Override
	public List<HomePageStatusesBean> selectStatusesListByUidAndGid(
			Integer uid, Integer gid, Integer limit) {
		// TODO Auto-generated method stub
		return this.statusesDao.selectStatusesListByUidAndGid(uid, gid, limit);
	}

	@Override
	public HomeLikeOrCommentBean selectIsLikeOrCommentOrAtt(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.statusesDao.selectIsLikeOrCommentOrAtt(map);
	}

}

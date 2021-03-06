package com.arttraining.api.dao;

import java.util.List;
import java.util.Map;

import com.arttraining.api.bean.InformationListBean;
import com.arttraining.api.bean.InformationShowBean;
import com.arttraining.api.pojo.Information;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    //获取头条信息列表--information/list
    List<InformationListBean> selectInformationList();
    //依据头条ID查询某一个头条信息--information/show
    InformationShowBean selectOneInformation(Integer id);
    
    //爱好者首页返回资讯列表信息
    //List<InformationListBean> selectInfoListByHomePage();
    //coffee add 0106 依据传递的头条消息类型来查询相应的头条列表信息
    List<InformationListBean> selectInformationListByType(Map<String, Object> map);
}
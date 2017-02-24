package com.mapper;

import java.util.List;

import com.model.NoticeLogs;

public interface NoticeLogsMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(NoticeLogs record);

    int insertSelective(NoticeLogs record);

    NoticeLogs selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(NoticeLogs record);

    int updateByPrimaryKey(NoticeLogs record);
    
    List<NoticeLogs> selectByUser(String userid,String nid);
}
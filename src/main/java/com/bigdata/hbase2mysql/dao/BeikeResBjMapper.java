package com.bigdata.hbase2mysql.dao;

import com.bigdata.hbase2mysql.model.BeikeResBj;

import java.util.List;

public interface BeikeResBjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BeikeResBj record);

    int insertSelective(BeikeResBj record);

    BeikeResBj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BeikeResBj record);

    int updateByPrimaryKey(BeikeResBj record);

    int bathInsert(List<BeikeResBj> bs);
}
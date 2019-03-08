package com.niuparser.yazhiwebapi.dao;

import com.niuparser.yazhiwebapi.entity.YzTSNews;

public interface YzTSNewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(YzTSNews record);

    int insertSelective(YzTSNews record);

    YzTSNews selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YzTSNews record);

    int updateByPrimaryKeyWithBLOBs(YzTSNews record);

    int updateByPrimaryKey(YzTSNews record);
}
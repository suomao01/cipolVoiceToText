package com.niuparser.yazhiwebapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niuparser.yazhiwebapi.dao.YzTSNewsMapper;
import com.niuparser.yazhiwebapi.entity.YzTSNews;
import com.niuparser.yazhiwebapi.service.YzTSNewsServiceI;

@Service(value="yzTSNewsService")
public class YzTSNewsService implements YzTSNewsServiceI{
	
	@Autowired
	private YzTSNewsMapper yzTSNewsMapper;

	@Override
	public YzTSNews getById(String id) {
		// TODO Auto-generated method stub
		return yzTSNewsMapper.selectByPrimaryKey(id);
	}

	
}

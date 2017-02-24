package com.mapper;

import com.model.KUserMoneyChanges;

public interface KUserMoneyChangesMapper extends BaseMapper {
	//开卡添加数据
	public void addCard(KUserMoneyChanges kUserMoneyChanges);
	
}

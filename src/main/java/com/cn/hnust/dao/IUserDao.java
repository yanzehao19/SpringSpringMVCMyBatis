package com.cn.hnust.dao;


import com.cn.hnust.pojo.User;

public interface IUserDao {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	public User selectByPrimaryKey(int id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}

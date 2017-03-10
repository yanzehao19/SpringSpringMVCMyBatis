package com.cn.hnust.dao;

import com.cn.hnust.pojo.User;

public interface IUserDao {
  public User selectByPrimaryKey(int userId) ;
}

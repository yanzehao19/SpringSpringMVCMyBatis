package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;

	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	public User doUserLogin(User user) {
		String name=user.getUsername();
		User user2=userDao.selectByPrimaryKey(1);
		List<User> list = userDao.selectId(user.getUsername());
		if(list.size() == 0){
			return null;
		}else{
			if(user.getPassword().equals(list.get(0).getPassword())){				
				return list.get(0);			
			}else{
				return null;
			}
		}
		
	}

}

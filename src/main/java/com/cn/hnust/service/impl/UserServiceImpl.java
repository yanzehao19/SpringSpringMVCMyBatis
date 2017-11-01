package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.dao.RedisDaoImpl;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
import com.mysql.jdbc.StringUtils;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private RedisDaoImpl redisDaoImpl;

	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	public User doUserLogin(User user) {
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
	
	public void insertUser(User user) {
		//1.插入数据库
		
		//2.插入redis
		String userJson=redisDaoImpl.get("user_"+user.getName());
		if(StringUtils.isEmptyOrWhitespaceOnly(userJson)) {
			redisDaoImpl.set("user_"+user.getAge(), user.toString());
		}
	}
	
	
	 public User getUserById(String id) {  
	     //1.从redis中读   
		 String userJson = redisDaoImpl.get("user_" + id);  
	        User user = null;  
	        if(StringUtils.isEmptyOrWhitespaceOnly(userJson)){  
	            //2.从数据库中读
	            //不存在,设置  
	            if(user != null)  
	            	//3.写入redis
	            	redisDaoImpl.set("user_" + id, user.toString());  
	        }else{  
	            user =new User();  
	        }  
	        return user;  
	    }  
	
	
	
	

}

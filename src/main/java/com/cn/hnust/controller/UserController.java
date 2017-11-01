package com.cn.hnust.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	@ResponseBody 
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		  int userId = Integer.parseInt(request.getParameter("id"));  
	        User user = this.userService.getUserById(userId);  
	        //model.addAttribute("user", user);  
	        //return "showUser";  
	        String name=user.getUsername();
	        return name;
	    }  
	
	
	
	@RequestMapping("/dologin.do") //url
	public String dologin( @RequestBody User user, Model model){
		String info = loginUser(user);
		if (!"SUCC".equals(info)) {
			model.addAttribute("failMsg", "User does not exist or password error!");
			return "/jsp/fail";
		}else{
			model.addAttribute("successMsg", "login Succ!");//???????????
			model.addAttribute("name", user.getUsername());
			return "/jsp/success";//?????
		}
	}

	@RequestMapping("/logout.do")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			try{
				subject.logout();
			}catch(Exception ex){
			}
		}
		response.sendRedirect("/index.jsp");
	}


	private String loginUser(User user) {
		if (isRelogin(user)) return "SUCC"; // ?????????????

		return shiroLogin(user); // ??shiro?????
	}
	private String shiroLogin(User user) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword().toCharArray(), null);
		token.setRememberMe(true);

		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ex) {
			return "User does not exist or password error!";
		} catch (IncorrectCredentialsException ex) {
			return "User does not exist or password error!";
		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			return ex.getMessage(); // ???????
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Internal error, please try again!";
		}
		return "SUCC";
	}

	private boolean isRelogin(User user) {
		Subject us = SecurityUtils.getSubject();
		if (us.isAuthenticated()) {
			return true; // ??????????????????????
		}
		return false; // ??????
	}
	}  

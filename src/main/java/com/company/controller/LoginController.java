package com.company.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dao.UserDAO;
import com.company.daoimpl.UserDAOImpl;
import com.company.dbconfig.DBConfig;
import com.company.model.User;

@Controller
public class LoginController {
	
	UserDAO userDAO=new UserDAOImpl();
	
//	@RequestMapping("/loginController")
//	public String loginControllerPage(@ModelAttribute("user") User user, HttpSession httpSession, ModelMap map) throws Exception 
//	{
//		User userToCheck = userDAO.getUserByLogin(user);
//		
//		if(userToCheck!=null)
//		{
//			user=userToCheck;
//			httpSession.setAttribute("u", user);
//			httpSession.setMaxInactiveInterval(60*3);
//			httpSession.setAttribute("msg", "Welcome " + user.getUsername());
//			httpSession.setAttribute("pagename", "UserDetailsController");
//			httpSession.setAttribute("type", "success");
//			return "popup";
//		}
//		else
//		{	
////			map.addAttribute("isInvalidLogin", true);
//			httpSession.setAttribute("msg", "Username or password is incorrect");
//			httpSession.setAttribute("pagename", "loginPretty");
//			httpSession.setAttribute("type", "error");
//			return "popup";
//		}
//	}
	
	@RequestMapping("/loginPretty")
	public String getLoginPagePretty(ModelMap map) {
		map.addAttribute("user",new User());
		return "loginPretty";
	}


	
	@RequestMapping("/personaldetails")
	public String getPersonalDetails(ModelMap map) {
//		map.addAttribute("user",new User());
		return "personaldetails";
	}

	@RequestMapping("/forgotPassword")
	public String getResetPasswordFromEmailPage(ModelMap map) {
		return "emailPasswordForgotForm";
	}
	
	
	@RequestMapping("/logout")
	public String logoutAndShowLoginPage(ModelMap map, HttpSession httpSession) {
		httpSession.invalidate();
		map.addAttribute("user",new User());
		return "loginPretty";
	}

}

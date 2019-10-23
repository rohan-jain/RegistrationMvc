package com.company.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dao.UserDAO;
import com.company.daoimpl.UserDAOImpl;
import com.company.dbconfig.DBConfig;
import com.company.email.Email;
import com.company.model.User;

@Controller
public class ApplicationController {

	UserDAO userDao = new UserDAOImpl();
	
	@RequestMapping("/")
	public String getLoginPage() {
		return "redirect:/loginPretty";
	}

	@RequestMapping("/emailController")
	public String sendEmailToUser(@RequestParam("emailId") String emailId, ModelMap map, HttpSession httpSession) throws Exception {
		
//		Session session = DBConfig.getSession();
//		Query query=session.createQuery("from User where email=: email");
//		query.setParameter("email", emailId);
		User user = userDao.displayUserByEmail(emailId);
		
		
		if(user != null)
		{	
			String password = user.getPassword();
			Email email=new Email(emailId, "Your password reset successfully", "Your password is " + password);
			email.sendEmail();
			
			map.addAttribute("user",new User());
			httpSession.setAttribute("msg", "Password Successfully sent to email");
			httpSession.setAttribute("pagename", "loginPretty");
			httpSession.setAttribute("type", "success");
			return "popup";
		}
		else
		{
			httpSession.setAttribute("msg", "Given emailId is not there with us");
			httpSession.setAttribute("pagename", "forgotPassword");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		
	}
	
	@RequestMapping("/welcome")
	public String getWelcomePage(ModelMap map) {
		return "welcome";
	}

}

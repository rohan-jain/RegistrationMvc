package com.company.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dbconfig.DBConfig;
import com.company.model.User;

@Controller
public class LoginController {
	
	@RequestMapping("/loginController")
	public String loginControllerPage(@ModelAttribute("user") User user, HttpSession httpSession, ModelMap map) throws Exception {

		Session session = DBConfig.getSession();
		Query query=session.createQuery("from User where username= :user and password=: pass");
		query.setParameter("user", user.getUsername());
		query.setParameter("pass", user.getPassword());
		
		if(!query.getResultList().isEmpty())
		{	
			Query q = session.createQuery("from User where username= :user");
			q.setParameter("user", user.getUsername());
			User u = (User)q.getResultList().get(0);
			
			httpSession.setAttribute("u", u);
			httpSession.setMaxInactiveInterval(60*3);
			System.out.println(u.getRole());
			System.out.println("test");
			System.out.println("in list not empty");
//			return "redirect:/UserDetailsController";
			httpSession.setAttribute("msg", "Welcome " + u.getUsername());
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "success");
			return "popup";
			
		}
		else
		{	
//			map.addAttribute("isInvalidLogin", true);
			httpSession.setAttribute("msg", "Username or password is incorrect");
			httpSession.setAttribute("pagename", "loginPretty");
			httpSession.setAttribute("type", "error");
//			return "loginPretty";
			return "popup";
		}
	}
	
	@RequestMapping("/loginPretty")
	public String getLoginPagePretty(ModelMap map) {
		map.addAttribute("user",new User());
		return "loginPretty";
	}
	
//	@RequestMapping("/login")
//	public String getLoginPage(ModelMap map) {
//		map.addAttribute("user",new User());
//		return "login";
//	}
//	
//	@RequestMapping("/login2")
//	public String getLoginPage2(ModelMap map) {
//		map.addAttribute("user",new User());
//		return "login2";
//	}
	
	


	@RequestMapping("/forgotPassword")
	public String getResetPasswordFromEmailPage(ModelMap map) {
		return "emailPasswordForgotForm";
	}
	
	
	@RequestMapping("/logout")
	public String getLoginPagePretty(ModelMap map, HttpSession httpSession) {
		httpSession.invalidate();
		map.addAttribute("user",new User());
		return "loginPretty";
	}
	

	


}

package com.company.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dbconfig.DBConfig;
import com.company.email.Email;
import com.company.model.User;

@Controller
public class ApplicationController {

	
	@RequestMapping("/index")
	public String getIndexPage() {
		return "index";
	}
	

	@RequestMapping("/emailController")
	public String sendEmailToUser(@RequestParam("emailId") String emailId, ModelMap map) throws Exception {
		

		
		Session session = DBConfig.getSession();
		Query query=session.createQuery("from User where email=: email");
		query.setParameter("email", emailId);
		
		if(!query.getResultList().isEmpty())
		{	
			System.out.println("email id in list");
			String password = ((User)query.getResultList().get(0)).getPassword();
			
			System.out.println("email is" + emailId);
			Email email=new Email(emailId, "Your password reset successfully", "Your password is " + password);
			email.sendEmail();
			
			map.addAttribute("user",new User());
			return "loginPretty";
		}
		else
		{
			return "emailReset";
		}
		
//		
//		
//  		map.addAttribute("user",new User());
//		return "login";
	}
	
	

	
	@RequestMapping("/welcome")
	public String getWelcomePage(ModelMap map) {
		return "welcome";
	}
	
//	@RequestMapping("/error")
//	public String getErrorPage(ModelMap map) {
//		return "error";
//	}
	
	

}

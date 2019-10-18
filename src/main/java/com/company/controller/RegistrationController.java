package com.company.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dbconfig.DBConfig;
import com.company.email.Email;
import com.company.model.Organization;
import com.company.model.User;

@Controller
public class RegistrationController {

	
	@RequestMapping("/register")
	public String getRegistrationPage(ModelMap map) throws Exception {
		map.addAttribute("user", new User());
		Session session = DBConfig.getSession();
		List<Organization> organizations=session.createQuery("from Organization").list();
		map.addAttribute("organizations",organizations);
		return "registrationPretty";
	}
	

	@RequestMapping("/registrationController")
	public String registrationControllerPage(@ModelAttribute("user") User user, HttpSession httpSession) throws Exception {

		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{
//			System.out.println("s ddfdsfds");
			System.out.println(user.getOrganization().getOrganizationID());
			user.setRole("user");
			session.save(user);
			Email email=new Email(user.getEmail(), "Registered Successfully!!!", "Welcome to Spring !!!");
			email.sendEmail();
			transaction.commit();
			httpSession.setAttribute("msg", "Registered Successfully");
			httpSession.setAttribute("pagename", "loginPretty");
			httpSession.setAttribute("type", "success");
			return "popup";
		}
		catch (Exception e)
		{
			transaction.rollback();
			System.out.println(e);
			httpSession.setAttribute("msg", e.getMessage());
			httpSession.setAttribute("pagename", "register");
			httpSession.setAttribute("type", "success");
			return "popup";
			
		}
//		return "loginPretty";
	

	}
	
	
}

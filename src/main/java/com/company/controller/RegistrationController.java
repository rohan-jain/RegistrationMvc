package com.company.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
		
		
//		Session session = DBConfig.getSession();
		Query query=session.createQuery("from User where username=: username");
		query.setParameter("username", user.getUsername());
		System.out.println("Hello");
		if(!query.getResultList().isEmpty())
		{	
			System.out.println("username already used choose different username");
			httpSession.setAttribute("msg", "Submitted Username is already in use, please choose a different Username");
			httpSession.setAttribute("pagename", "register");
			httpSession.setAttribute("type", "error");
			return "popup";
//			return "registrationPretty";
		}
		System.out.println(query.getResultList());
		
		query=session.createQuery("from User where email=: email");
		query.setParameter("email", user.getEmail());
		if(!query.getResultList().isEmpty())
		{	
			httpSession.setAttribute("msg", "Submitted EmailId is already in use, change Email ID or" + 
											" click \"Forgot Password\" to get Email with Lost Password");
			httpSession.setAttribute("pagename", "register");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		
		boolean isValidRegistration = true;
		String errorMsg = "";
		String mobileNo = user.getMobileno();
		String password = user.getPassword();
		
		if(mobileNo.length() != 10) {
			errorMsg += "\nPlease Enter Mobile number of length ten";
			isValidRegistration = false;
		}
		if (!mobileNo.matches("[0-9]+")) {
			errorMsg += "\nPlease enter only digits in mobile number";
			isValidRegistration = false;
		}
		if(password.length() < 4) {
			errorMsg += "\nPlease enter password atleast length four";
			isValidRegistration = false;
		}
		
		if(!isValidRegistration)
		{	
			httpSession.setAttribute("msg", errorMsg);
			httpSession.setAttribute("pagename", "register");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		
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

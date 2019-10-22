package com.company.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dao.OrganizationDAO;
import com.company.dao.UserDAO;
import com.company.daoimpl.OrganizationDaoImpl;
import com.company.daoimpl.UserDAOImpl;
import com.company.email.Email;
import com.company.model.Organization;
import com.company.model.User;

@Controller
public class RegistrationController {

	UserDAO userDAO = new UserDAOImpl();
	
	OrganizationDAO organizationDAO = new OrganizationDaoImpl();
	
	
	@RequestMapping("/register")
	public String getRegistrationPage(ModelMap map) throws Exception
	{
		map.addAttribute("user", new User());
		List<Organization> organizations= organizationDAO.displayOrganizations();
		map.addAttribute("organizations",organizations);
		return "registrationPretty";
	
	}

	@RequestMapping("/registrationController")
	public String registrationControllerPage(@Valid @ModelAttribute("user") User user,BindingResult br,HttpSession httpSession,ModelMap map) throws Exception 
	{
		try
		{
			if(userDAO.isUsernameDuplicate(user)) {
				br.rejectValue("username", "error.user", "this username taken, please choose another");
			}
			
			if(userDAO.isEmailDuplicate(user)) {
				br.rejectValue("email", "error.user", "this email taken, please choose another");
			}
			
			if(!br.hasErrors())
			{
	
					user.setRole("user");
					userDAO.addUser(user);
					
					Email email=new Email(user.getEmail(), "Registered Successfully!!!", "Welcome to user management");
					email.sendEmail();
					
					httpSession.setAttribute("msg", "Registered Successfully");
					httpSession.setAttribute("pagename", "loginPretty");
					httpSession.setAttribute("type", "success");
					return "popup";
				
			}
			else
			{

	
				map.addAttribute("user", user);
				List<Organization> organizations=organizationDAO.displayOrganizations();
				map.addAttribute("organizations",organizations);
				
				return "registrationPretty";
			}

		}
		catch (Exception e)
		{
			httpSession.setAttribute("msg", e.getMessage());
			httpSession.setAttribute("pagename", "register");
			httpSession.setAttribute("type", "success");
			return "popup";
		}
	}
}	
	


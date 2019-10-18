package com.company.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dbconfig.DBConfig;
import com.company.model.Organization;
import com.company.model.User;

@Controller
public class OrganizationController {
	
	
	@RequestMapping("/addorganization")
	public String getOrganizationPage(ModelMap map) {
		map.addAttribute("organization",new Organization());
		return "addorganization";
	}
	
	@RequestMapping("/UserDetailsController")
	private String getUserDetailsPage(ModelMap map) throws Exception {

		Session session = DBConfig.getSession();
		List<User> users=session.createQuery("from User ").getResultList();
		map.addAttribute("user",new User());
		map.addAttribute("users",users);
		return "userdetails";
	}
	

	@RequestMapping("/organizatiocontroller")
	public String registrationControllerPage(@ModelAttribute("organization") Organization organization) throws Exception {
		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(organization);
		transaction.commit();
		return "redirect:/register";
	}


}

package com.company.controller;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dao.OrganizationDAO;
import com.company.dao.UserDAO;
import com.company.daoimpl.OrganizationDaoImpl;
import com.company.daoimpl.UserDAOImpl;
import com.company.model.Organization;
import com.company.model.User;


@Controller
public class OrganizationController {
	UserDAO userDao = new UserDAOImpl();
	OrganizationDAO organizationDAO = new OrganizationDaoImpl();
	
	@RequestMapping("/addorganization")
	public String getOrganizationPage(ModelMap map, HttpSession httpSession) {
		
		if(httpSession==null
				   || httpSession.getAttribute("u")==null
				   || ((User)httpSession.getAttribute("u")).getUsername()==null) {
					
					httpSession.setAttribute("msg", "Please login to access this page");
					httpSession.setAttribute("pagename", "loginPretty");
					httpSession.setAttribute("type", "error");
					return "popup";
				}
		
		
		map.addAttribute("organization",new Organization());
		return "addorganizationPretty";
	}
	
	@RequestMapping("/UserDetailsController")
	public String getUserDetailsPage(ModelMap map, HttpSession httpSession) throws Exception {
		
		if(httpSession==null
		   || httpSession.getAttribute("u")==null
		   || ((User)httpSession.getAttribute("u")).getUsername()==null) {
			
			httpSession.setAttribute("msg", "Please login to access this page");
			httpSession.setAttribute("pagename", "loginPretty");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		

		List<User> users = userDao.displayUsers();
		map.addAttribute("user",new User());
		map.addAttribute("users",users);
		return "userdetails";
	}
	

	@RequestMapping("/organizatiocontroller")
	public String registrationControllerPage(@ModelAttribute("organization") Organization organization, HttpSession httpSession) throws Exception {
		if(organizationDAO.addOrganization(organization)) {
			httpSession.setAttribute("msg", "Successfully registered organization");
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "success");
			return "popup";
		} else {
			httpSession.setAttribute("msg", "Some issue encountered adding organization");
			httpSession.setAttribute("pagename", "addorganization");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
	}


}

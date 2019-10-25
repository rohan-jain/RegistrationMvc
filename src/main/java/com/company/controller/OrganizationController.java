package com.company.controller;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@RequestMapping("/deleteorgcontroller")
	public String deleteController(@RequestParam("organizationID") int organizationId, HttpSession httpSession) throws Exception {
		
		String pagename = "OrganizationDetailsController"; // will return for both success or error
		
		if(organizationDAO.deleteOrganization(organizationId)) {
			httpSession.setAttribute("msg", "Successfully deleted organization");
			httpSession.setAttribute("pagename", pagename);
			httpSession.setAttribute("type", "success");
			return "popup";
		} else {
			httpSession.setAttribute("msg", "Some issue encountered deleting organization");
			httpSession.setAttribute("pagename", pagename);
			httpSession.setAttribute("type", "error");
			return "popup";
		}
	}
		
	
	
	@RequestMapping("/OrganizationDetailsController")
	public String modifyOrganizationPage(ModelMap map, HttpSession httpSession) {
		
		if(httpSession==null
				   || httpSession.getAttribute("u")==null
				   || ((User)httpSession.getAttribute("u")).getUsername()==null) {
					
					httpSession.setAttribute("msg", "Please login to access this page");
					httpSession.setAttribute("pagename", "loginPretty");
					httpSession.setAttribute("type", "error");
					return "popup";
				}
		
		List<Organization> organizations = organizationDAO.displayOrganizations();
		map.addAttribute("organizations",organizations);
		
		return "modifyOrganization";
	}
	
	
	@RequestMapping("/UserDetailsController")
	public String getUserDetailsPage(ModelMap map, HttpSession httpSession) throws Exception {
		
		
//		if(httpSession==null
//		   || httpSession.getAttribute("u")==null
//		   || ((User)httpSession.getAttribute("u")).getUsername()==null) {
//			
//			httpSession.setAttribute("msg", "Please login to access this page");
//			httpSession.setAttribute("pagename", "loginPretty");
//			httpSession.setAttribute("type", "error");
//			return "popup";
//		}
		
//		if(((User)httpSession.getAttribute("u")).getRole().equals("user")) {
//			return "personaldetails";
//		}
		org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = currentUser.getUsername();
		
		User myCurrentUser = new User();
		myCurrentUser.setUsername(username);
		myCurrentUser = userDao.displayUserByName(myCurrentUser);
		httpSession.setAttribute("u", myCurrentUser);
		
		List<User> users = userDao.displayUsers();
//		map.addAttribute("user",new User());
		map.addAttribute("users",users);
		return "userdetails";
	}
	

	@RequestMapping("/updateOrganizationController")
	public String updateUser(@RequestParam("organizationID") String organizationIdString,
						      @RequestParam("organizationName") String organizationName,
						      HttpSession httpSession,
							  ModelMap map) throws Exception {
		
		boolean hasErrors = false;
        String errorMessage = "";
        
        int organizationId;
        
        try {  
        	organizationId = Integer.parseInt(organizationIdString);  
        } catch(NumberFormatException e){  
			hasErrors = true;
			errorMessage += "\norganizationId: " + "OrganizationId must be simple integer";
			organizationId = -1;
        }  
		
		Organization org = new Organization();
		org.setOrganizationID(organizationId);
		org.setOrganizationName(organizationName);
		
		
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // howtodoinjava
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Organization>> constraintViolations = validator.validate(org);
 

        //Show errors
        if (constraintViolations.size() > 0) {
        	hasErrors = true;
            for (ConstraintViolation<Organization> violation : constraintViolations) {
                errorMessage += "\n" + violation.getPropertyPath() + ": " + violation.getMessage();
            }
        }
		
		if(organizationDAO.isOrganizationNameDuplicate(org)) {
			hasErrors = true;
			errorMessage += "\norganizationName: " + "Organization Name is already taken";
		}
		
//		if(!userDao.updateUser(user)) {
//			hasErrors = true;
//		}
        
        
        
		if(!hasErrors && !organizationDAO.updateOrganization(org)) {
			hasErrors = true;
		}
		
		if(!hasErrors) {
			
			httpSession.setAttribute("msg", "Data Updated Successfully!!!");
			httpSession.setAttribute("pagename", "OrganizationDetailsController");
			httpSession.setAttribute("type", "success");
			return "popup";
		} else {
			errorMessage = "Following errors found in update\n" + errorMessage;
			httpSession.setAttribute("msg", errorMessage);
			httpSession.setAttribute("pagename", "OrganizationDetailsController");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		
	}

	@RequestMapping("/organizatiocontroller")
	public String registrationControllerPage(@ModelAttribute("organization") Organization organization, HttpSession httpSession) throws Exception {
		if(organizationDAO.addOrganization(organization)) {
			httpSession.setAttribute("msg", "Successfully added organization");
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

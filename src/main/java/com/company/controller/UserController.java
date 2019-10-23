package com.company.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dao.OrganizationDAO;
import com.company.dao.UserDAO;
import com.company.daoimpl.OrganizationDaoImpl;
import com.company.daoimpl.UserDAOImpl;
import com.company.model.Organization;
import com.company.model.User;

@Controller
public class UserController {
	UserDAO userDao = new UserDAOImpl();
	OrganizationDAO organizationDao = new OrganizationDaoImpl();

	@RequestMapping("/deletecontroller")
	public String deleteController(@RequestParam("userid") int userid) throws Exception {
		
		User user=new User();
		user.setUserid(userid);
		userDao.deleteUser(user);
		return "redirect:/UserDetailsController";
	}
	
	
	@RequestMapping("/updateUserController")
	public String updateUser(@RequestParam("userid") String useridString,
						      @RequestParam("username") String username,
						      @RequestParam("email") String email,
						      @RequestParam("mobileno") String mobileno,
						      @RequestParam("address") String address,
						      @RequestParam("organizationId") String organizationIdString,
						      HttpSession httpSession,
							  ModelMap map) throws Exception {
		
		boolean hasErrors = false;
        String errorMessage = "";
        
        int userid;
        int organizationId;
        
        try {  
            userid = Integer.parseInt(useridString);  
        } catch(NumberFormatException e){  
			hasErrors = true;
			errorMessage += "\nuserid: " + "Userid must be simple integer";
			userid = -1;
        }  
        
        try {  
        	organizationId = Integer.parseInt(organizationIdString);  
        } catch(NumberFormatException e){  
			hasErrors = true;
			errorMessage += "\norganizationId: " + "OrganizationId must be simple integer";
			organizationId = -1;
        }  
        

		User user = new User();
		user.setUserid(userid);
		
		user = userDao.displayUserById(user);

		user.setUserid(userid);
		user.setUsername(username);
		user.setEmail(email);
		user.setMobileno(mobileno);
		user.setAddress(address);
		Organization org = new Organization();
		org.setOrganizationID(organizationId);
		user.setOrganization(org);
		
		
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // howtodoinjava
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
 

        //Show errors
        if (constraintViolations.size() > 0) {
        	hasErrors = true;
            for (ConstraintViolation<User> violation : constraintViolations) {
                errorMessage += "\n" + violation.getPropertyPath() + ": " + violation.getMessage();
            }
        }
		
		
//		if(userDao.isEmailDuplicate(user)) {
//			hasErrors = true;
//			errorMessage += "\nemail: " + "Email duplicate";
//		}
//		
//		if(userDao.isUsernameDuplicate(user)) {
//			hasErrors = true;
//			errorMessage += "\nusername: " + "UserName duplicate";
//		}
//		
		List<User> userNameOrEmailSame = userDao.getUsersByEmailOrName(user);
		for(User userToCheck : userNameOrEmailSame) {
			if(   userToCheck.getUsername().equals(user.getUsername())
			   && userToCheck.getUserid() != (user.getUserid())) {
				hasErrors = true;
				errorMessage += "\nusername: " + "UserName duplicate";
				break;
			}
		}
		
		for(User userToCheck : userNameOrEmailSame) {
			if(   userToCheck.getEmail().equals(user.getEmail())
			   && userToCheck.getUserid() != (user.getUserid())) {
				hasErrors = true;
				errorMessage += "\nemail: " + "Email duplicate";
				break;
			}
		}
		
		if(!organizationDao.doesOrganizationExist(user.getOrganization().getOrganizationID() )) {
			hasErrors = true;
			errorMessage += "\norganizationId: " + "Organization ID does not exist";
		}
		
		if(!hasErrors && !userDao.updateUser(user)) { // update user if no errors
			hasErrors = true;
		}
		
		if(!hasErrors) {
			
			map.addAttribute("user",new User());
			httpSession.setAttribute("msg", "Data Updated Successfully!!!");
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "success");
			return "popup";
		} else {
			errorMessage = "Following errors found in update\n" + errorMessage;
			map.addAttribute("user",new User());
			httpSession.setAttribute("msg", errorMessage);
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		
	}

}

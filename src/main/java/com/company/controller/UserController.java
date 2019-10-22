package com.company.controller;

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

import com.company.dao.UserDAO;
import com.company.daoimpl.UserDAOImpl;
import com.company.model.Organization;
import com.company.model.User;

@Controller
public class UserController {
	UserDAO userDao = new UserDAOImpl();

	@RequestMapping("/deletecontroller")
	public String deleteController(@RequestParam("userid") int userid) throws Exception {

//		Session session = DBConfig.getSession();
//		Transaction transaction = session.beginTransaction();
//		User user=new User();
//		user.setUserid(userid);
//		session.delete(user);
//		transaction.commit();
		
		User user=new User();
		user.setUserid(userid);
		userDao.deleteUser(user);
		return "redirect:/UserDetailsController";
	}
	
	
	@RequestMapping("/updateUserController")
	public String updateUser(@RequestParam("userid") String userid,
						      @RequestParam("username") String username,
						      @RequestParam("email") String email,
						      @RequestParam("mobileno") String mobileno,
						      @RequestParam("address") String address,
						      @RequestParam("organizationId") String organizationId,
						      HttpSession httpSession,
							  ModelMap map) throws Exception {
		
		System.out.println("userid is " + userid + " and username is " + username);
		

		User user = new User();
		user.setUserid(Integer.parseInt(userid));
		
		user = userDao.displayUserById(user);

		user.setUserid(Integer.parseInt(userid));
		user.setUsername(username);
		user.setEmail(email);
		user.setMobileno(mobileno);
		user.setAddress(address);
		Organization org = new Organization();
		org.setOrganizationID(Integer.parseInt(organizationId));
		user.setOrganization(org);
		
		
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // howtodoinjava
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
 
        boolean hasErrors = false;
        String errorMessage = "";
        
        //Show errors
        if (constraintViolations.size() > 0) {
        	hasErrors = true;
        	errorMessage += "Following errors found in update\n";
            for (ConstraintViolation<User> violation : constraintViolations) {
            	System.out.println("qpal in validating updateUser");
                System.out.println(violation.getMessage());
                System.out.println(violation.getPropertyPath());
                errorMessage += "\n" + violation.getPropertyPath() + ": " + violation.getMessage();
            }
        }
		
		
		if(userDao.isEmailDuplicate(user)) {
			hasErrors = true;
			errorMessage += "\nemail: " + "Email duplicate";
			System.out.println("hello qpal some eemamil duplicate erro");
		}
		
		if(userDao.isUsernameDuplicate(user)) {
			hasErrors = true;
			errorMessage += "\nusername: " + "UserName duplicate";
			System.out.println("hello some error username dulicate ");
		}
		
		
		userDao.updateUser(user);

		
		if(!hasErrors) {
			System.out.println("editing user");
			map.addAttribute("user",new User());
			httpSession.setAttribute("msg", "Data Updated Successfully!!!");
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "success");
			return "popup";
		} else {
			System.out.println("editing user");
			map.addAttribute("user",new User());
			httpSession.setAttribute("msg", errorMessage);
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "error");
			return "popup";
		}
		
	}

}

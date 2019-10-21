package com.company.controller;

import javax.servlet.http.HttpSession;

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
	public String updateUser(
		      @RequestParam("userid") String userid,
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
		

//		User user=new User(); //dont create, user user from query
		user.setUserid(Integer.parseInt(userid));
		user.setUsername(username);
		user.setEmail(email);
		user.setMobileno(mobileno);
		user.setAddress(address);
		Organization org = new Organization();
		org.setOrganizationID(Integer.parseInt(organizationId));
		user.setOrganization(org);
		
		userDao.updateUser(user);

		
		System.out.println("editing user");
//		Session session = DBConfig.getSession();
//		List<User> users=session.createQuery("from model.User ").getResultList();
		map.addAttribute("user",new User());
		httpSession.setAttribute("msg", "Data Updated Successfully!!!");
		httpSession.setAttribute("pagename", "UserDetailsController");
		httpSession.setAttribute("type", "success");
//		return "loginPretty";
		return "popup";
		}

}

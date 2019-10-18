package com.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dbconfig.DBConfig;
import com.company.email.Email;
import com.company.model.Organization;
import com.company.model.User;

@Controller
public class UserController {


	@RequestMapping("/deletecontroller")
	public String deleteController(@RequestParam("userid") int userid) throws Exception {

		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		User user=new User();
		user.setUserid(userid);
		session.delete(user);
		transaction.commit();
		return "redirect:/UserDetailsController";
	}
	
	
	@RequestMapping("/updateUserController")
	public String updateUser(
		      @RequestParam("userid") String userid,
		      @RequestParam("username") String username,
		      @RequestParam("email") String email,
		      @RequestParam("mobileno") String mobileno,
		      @RequestParam("address") String address,
		      @RequestParam("organizationId") String organizationId, HttpSession httpSession,
			ModelMap map) throws Exception {
		
		System.out.println("userid is " + userid + " and username is " + username);
		
		Session session1 = DBConfig.getSession();
		Query query1=session1.createQuery("from User where userid= :userid");
		query1.setParameter("userid", Integer.parseInt(userid));
		User user = (User)query1.getResultList().get(0);;
		
		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
//		User user=new User(); //dont create, user user from three lines before
		user.setUserid(Integer.parseInt(userid));
		user.setUsername(username);
		user.setEmail(email);
		user.setMobileno(mobileno);
		user.setAddress(address);
		Organization org = new Organization();
		org.setOrganizationID(Integer.parseInt(organizationId));
		user.setOrganization(org);
//		user.setAddress("kindia");
//		user.setEmail("k@gmail.com");
		session.update(user);
		transaction.commit();
//		
		
//		Session session = DBConfig.getSession();
//		Transaction transaction = session.beginTransaction();
//		Query query=session.createQuery("update model.User set username=: username where userid = :userid");
//		query.setParameter("userid", userid);
//		transaction.commit();
//		int result = query.executeUpdate();
//		System.out.println(result);
		
//		Transaction transaction = session.beginTransaction();
//		User user=new User();
//		user.setUserid(userid);
//		session.delete(user);
//		transaction.commit();
//		
//		Query query = session.createQuery("update Stock set stockName = :stockName" +
//				" where stockCode = :stockCode");
//		query.setParameter("stockName", "DIALOG1");
//		query.setParameter("stockCode", "7277");
//		
//		
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
	
//
//	@RequestMapping("/updatecontroller")
//	public String updateController(@RequestParam("userid") int userid,@RequestParam("username") String username,@RequestParam("email") String email,@RequestParam("mobileno") String mobileno, HttpSession httpSession) throws Exception {
//		
//
//		Session session = DBConfig.getSession();
//		Transaction transaction = session.beginTransaction();
//		User user=new User();
//		user.setUserid(userid);
//		user.setUsername(username);
//		user.setEmail(email);
//		user.setMobileno(mobileno);
//		session.update(user);
//		transaction.commit();
//		
//		httpSession.setAttribute("msg", "Data Updated Successfully!!!");
//		httpSession.setAttribute("pagename", "UserDetailsController");
//		httpSession.setAttribute("type", "success");
////		return "loginPretty";
//		return "popup";
//	}
//	
//	
//	

}

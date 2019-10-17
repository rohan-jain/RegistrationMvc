package controller;

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

import dbconfig.DBConfig;
import email.Email;
import model.Organization;
import model.User;

@Controller
public class UserController {
	
	@RequestMapping("/index")
	public String getIndexPage() {
		return "index";
	}
	
	@RequestMapping("/register")
	public String getRegistrationPage(ModelMap map) throws Exception {
		map.addAttribute("user", new User());
		Session session = DBConfig.getSession();
		List<Organization> organizations=session.createQuery("from model.Organization").list();
		map.addAttribute("organizations",organizations);
		return "registrationPretty";
	}
	
//	@RequestMapping("/login")
//	public String getLoginPage(ModelMap map) {
//		map.addAttribute("user",new User());
//		return "login";
//	}
//	
//	@RequestMapping("/login2")
//	public String getLoginPage2(ModelMap map) {
//		map.addAttribute("user",new User());
//		return "login2";
//	}
	
	@RequestMapping("/loginPretty")
	public String getLoginPagePretty(ModelMap map) {
		map.addAttribute("user",new User());
		return "loginPretty";
	}
	
	@RequestMapping("/emailController")
	public String sendEmailToUser(@RequestParam("emailId") String emailId, ModelMap map) throws Exception {
		

		
		Session session = DBConfig.getSession();
		Query query=session.createQuery("from model.User where email=: email");
		query.setParameter("email", emailId);
		
		if(!query.getResultList().isEmpty())
		{	
			System.out.println("email id in list");
			String password = ((User)query.getResultList().get(0)).getPassword();
			
			System.out.println("email is" + emailId);
			Email email=new Email(emailId, "Your password reset successfully", "Your password is " + password);
			email.sendEmail();
			
			map.addAttribute("user",new User());
			return "login";
		}
		else
		{
			return "emailReset";
		}
		
//		
//		
//  		map.addAttribute("user",new User());
//		return "login";
	}
	
	
	@RequestMapping("/welcome")
	public String getWelcomePage(ModelMap map) {
		return "welcome";
	}

	
	
	@RequestMapping("/forgotPassword")
	public String getResetPasswordFromEmailPage(ModelMap map) {
		return "emailPasswordForgotForm";
	}
	

	@RequestMapping("/error")
	public String getErrorPage(ModelMap map) {
		return "error";
	}
	
	@RequestMapping("/addorganization")
	public String getOrganizationPage(ModelMap map) {
		map.addAttribute("organization",new Organization());
		return "addorganization";
	}
	
	@RequestMapping("/UserDetailsController")
	private String getUserDetailsPage(ModelMap map) throws Exception {

		Session session = DBConfig.getSession();
		List<User> users=session.createQuery("from model.User ").getResultList();
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
	
	
	@RequestMapping("/logout")
	public String getLoginPagePretty(ModelMap map, HttpSession httpSession) {
		httpSession.invalidate();
		map.addAttribute("user",new User());
		return "loginPretty";
	}
	
	
	@RequestMapping("/loginController")
	public String loginControllerPage(@ModelAttribute("user") User user, HttpSession httpSession, ModelMap map) throws Exception {

		Session session = DBConfig.getSession();
		Query query=session.createQuery("from model.User where username= :user and password=: pass");
		query.setParameter("user", user.getUsername());
		query.setParameter("pass", user.getPassword());
		
		if(!query.getResultList().isEmpty())
		{	
			Query q = session.createQuery("from model.User where username= :user");
			q.setParameter("user", user.getUsername());
			User u = (User)q.getResultList().get(0);
			
			httpSession.setAttribute("u", u);
			httpSession.setMaxInactiveInterval(60*3);
			System.out.println(u.getRole());
			System.out.println("test");
			System.out.println("in list not empty");
//			return "redirect:/UserDetailsController";
			httpSession.setAttribute("msg", "Welcome " + u.getUsername());
			httpSession.setAttribute("pagename", "UserDetailsController");
			httpSession.setAttribute("type", "success");
			return "popup";
			
		}
		else
		{	
//			map.addAttribute("isInvalidLogin", true);
			httpSession.setAttribute("msg", "Username or password is incorrect");
			httpSession.setAttribute("pagename", "loginPretty");
			httpSession.setAttribute("type", "error");
//			return "loginPretty";
			return "popup";
		}
	}


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
		
		
		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		User user=new User();
		user.setUserid(18);
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
	

	@RequestMapping("/updatecontroller")
	public String updateController(@RequestParam("userid") int userid,@RequestParam("username") String username,@RequestParam("email") String email,@RequestParam("mobileno") String mobileno, HttpSession httpSession) throws Exception {
		

		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		User user=new User();
		user.setUserid(userid);
		user.setUsername(username);
		user.setEmail(email);
		user.setMobileno(mobileno);
		session.update(user);
		transaction.commit();
		
		httpSession.setAttribute("msg", "Data Updated Successfully!!!");
		httpSession.setAttribute("pagename", "UserDetailsController");
		httpSession.setAttribute("type", "success");
//		return "loginPretty";
		return "popup";
	}
	
	
	

}

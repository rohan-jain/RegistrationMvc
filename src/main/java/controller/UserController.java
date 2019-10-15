package controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dbconfig.DBConfig;
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
		return "registration";
	}
	
	@RequestMapping("/login")
	public String getLoginPage(ModelMap map) {
		map.addAttribute("user",new UserController());
		return "login";
	}
	
	@RequestMapping("/welcome")
	public String getWelcomePage(ModelMap map) {
		return "welcome";
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


	@RequestMapping("/organizatiocontroller")
	public String registrationControllerPage(@ModelAttribute("organization") Organization organization) throws Exception {
		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(organization);
		transaction.commit();
		return "redirect:/register";
	}

	@RequestMapping("/registrationController")
	public String registrationControllerPage(@ModelAttribute("user") User user) throws Exception {

		Session session = DBConfig.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			System.out.println("s ddfdsfds");
			System.out.println(user.getOrganization().getOrganizationID());
			user.setRole("user");
			session.save(user);
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			System.out.println(e);
		}
		return "login";
	}

	@RequestMapping("/loginController")
	public String loginControllerPage(@ModelAttribute("user") User user) throws Exception {

		Session session = DBConfig.getSession();
		Query query=session.createQuery("from model.User where username= :user and password=: pass");
		query.setParameter("user", user.getUsername());
		query.setParameter("pass", user.getPassword());
		if(!query.getResultList().isEmpty())
		{
			return "redirect:/welcome";
		}
		else
		{
			return "redirect:/error";
		}
	}

}

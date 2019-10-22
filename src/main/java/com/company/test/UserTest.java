package com.company.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.dao.UserDAO;
import com.company.daoimpl.UserDAOImpl;
import com.company.model.User;



public class UserTest 
{
    UserDAO userDAO=new UserDAOImpl();
	
	@Before
	public void setUp() throws Exception {
			
	}

	@After
	public void tearDown() throws Exception {
		userDAO = null;
	}
	
	@Test
	public void addUserDetails()
	{
		try
		{
//			
			User details=new User();
			details.setUsername("ROhan");
			details.setPassword("Pass@123");
			details.setMobileno("9856253268");
			
			assertEquals(true, userDAO.addUser(details));
			
		}
		catch (Exception e)	{
			System.out.println(e);
		}
	}
	@Ignore
	@Test
	public void updateUserDetails() 
	{
		try
		{
			User details=new User();
			details.setUserid(2);
			details.setUsername("rahul");
			details.setPassword("pass@1234");
			details.setMobileno("8856253268");
			

			assertEquals(true, userDAO.updateUser(details));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	@Ignore
	@Test
	public void deleteUserDetails()
	{
		try
		{
			User details=new User();
			details.setUserid(1);
			details.setUsername("rahul");
			details.setPassword("pass@1234");
			details.setMobileno("8856253268");
			
			

			assertEquals(true, userDAO.deleteUser(details));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	@Ignore
	@Test
	public void getUserDetailsById() 
	{
		try
		{
			User user=new User();
			user.setUserid(2);
			User userDetails=userDAO.displayUserById(user);
			
			if(userDetails!=null)
			assertTrue("data inserted",true);
			if(userDetails==null)
			assertFalse("data not inserted",false);
				
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@Ignore
	@Test
	public void getUserDetails()
	{
		try
		{

			List<User> userDetailsList=userDAO.displayUsers();
			
			for (User userDetails : userDetailsList) 
			{
				System.out.println("User Id: "+userDetails.getUserid());
				System.out.println("User Username: "+userDetails.getUsername());
				System.out.println("User Phone Number: "+userDetails.getMobileno());
				System.out.println("User Password: "+userDetails.getPassword());
				System.out.println();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}

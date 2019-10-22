package com.company.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.dao.UserDAO;
import com.company.dbconfig.DBConfig;
import com.company.model.User;

public class UserDAOImpl implements UserDAO 
{
	Transaction tx;
	Session session;
	
	@Override
	public boolean addUser(User user) 
	{
		try
		{
			session = DBConfig.getSession();
			tx=session.beginTransaction();
			session.save(user);
			tx.commit();
			return true;
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) 
	{
		try
		{
			session = DBConfig.getSession();
			tx=session.beginTransaction();
			session.update(user);
			tx.commit();
			return true;
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user)
	{
		try
		{
			session = DBConfig.getSession();
			tx=session.beginTransaction();
			session.delete(user);
			tx.commit();
			return true;
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e);
			return false;
		}	
	}

	@Override
	public List<User> displayUsers() 
	{
		try
		{
			session = DBConfig.getSession();
			return (List<User>)session.createQuery("from User").getResultList();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public User displayUserByName(User user) 
	{
		try
		{
			session = DBConfig.getSession();
			Query query=session.createQuery("from User where username= :user");
			query.setParameter("user", user.getUsername());
			return (User)query.getResultList().get(0);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public User displayUserByEmail(User user) 
	{
		try
		{
			session = DBConfig.getSession();
			Query query=session.createQuery("from User where email= :email");
			query.setParameter("email", user.getEmail());
			return (User)query.getResultList().get(0);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public User displayUserById(User user) {
		// TODO Auto-generated method stub
		
		try
		{
			session = DBConfig.getSession();
			Query query=session.createQuery("from User where userid= :userid");
			query.setParameter("userid", user.getUserid());
			return (User)query.getResultList().get(0);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public boolean isUsernameDuplicate(User user) {
		String username = user.getUsername();
		int userid = user.getUserid();
		try
		{
			session = DBConfig.getSession();
			Query query=session.createQuery("from User where username= :user");
			query.setParameter("user", username);
			List<User> userList = query.getResultList();
			
			for(User userToCheck: userList) { // find atleast one user whose id not same as provided user's id
				if(userToCheck.getUserid() != userid) {
					return true;
				}
			}		
			return false;

		}
		catch(Exception e)
		{
			return true;
		}
//		return false;
	}

	@Override
	public boolean isEmailDuplicate(User user) {
		String email = user.getEmail();
		int userid = user.getUserid();
		try
		{
			session = DBConfig.getSession();
			Query query=session.createQuery("from User where email= :email");
			query.setParameter("email", email);
			List<User> userList = query.getResultList();
			
			for(User userToCheck: userList) { // find atleast one user whose id not same as provided user's id
				if(userToCheck.getUserid() != userid) {
					return true;
				}
			}		
			return false;

		}
		catch(Exception e)
		{
			return true;
		}
	}

}

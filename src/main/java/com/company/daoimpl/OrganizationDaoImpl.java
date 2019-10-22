package com.company.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.dao.OrganizationDAO;
import com.company.dbconfig.DBConfig;
import com.company.model.Organization;
import com.company.model.User;

public class OrganizationDaoImpl implements OrganizationDAO{

	Transaction tx;
	Session session;
	
	@Override
	public boolean addOrganization(Organization organization) {
		try {
			session = DBConfig.getSession();
			tx = session.beginTransaction();
			session.save(organization);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Organization> displayOrganizations() {
		
		try {
			session = DBConfig.getSession();
			return session.createQuery("from Organization").getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

	@Override
	public boolean doesOrganizationExist(int organizationId) {
		try {
			session = DBConfig.getSession();
			Query query=session.createQuery("from Organization where organizationid= :organizationid");
			query.setParameter("organizationid", organizationId);
			
			if(!query.getResultList().isEmpty()) {
				return true;
			} else {
				return false;
			}	
				
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
//	@Override
//	public User displayUserByName(User user) 
//	{
//		try
//		{
//			session = DBConfig.getSession();
//			Query query=session.createQuery("from User where username= :user");
//			query.setParameter("user", user.getUsername());
//			return (User)query.getResultList().get(0);
//		}
//		catch(Exception e)
//		{
//			return null;
//		}
//	}
	
}

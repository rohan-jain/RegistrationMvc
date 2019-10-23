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

	@Override
	public boolean deleteOrganization(int organizationID) {
		
		try
		{
			Organization org = new Organization();
			org.setOrganizationID(organizationID);
			
			session = DBConfig.getSession();
			tx=session.beginTransaction();
			session.delete(org);
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
	public boolean updateOrganization(Organization organization) 
	{
		try
		{
			session = DBConfig.getSession();
			tx=session.beginTransaction();
			session.update(organization);
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

//
//	@Override
//	public boolean doesOrganizationExist() {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
//	@Override
//	public boolean isOrganizationNameDuplicate(Organization org) {
//		try {
//			session = DBConfig.getSession();
//			Query query=session.createQuery("from Organization where organizationname= :organizationName");
//			query.setParameter("organizationName", org.getOrganizationName());
//			
//			if(!query.getResultList().isEmpty()) {
//				return true;
//			} else {
//				return false;
//			}	
//				
//		} catch (Exception e) {
//			System.out.println(e);
//			return false;
//		}
//
//	}
	
	@Override
	public boolean isOrganizationNameDuplicate(Organization org) {
		String orgName = org.getOrganizationName();
		int orgId = org.getOrganizationID();
		try
		{
			session = DBConfig.getSession();
			Query query=session.createQuery("from Organization where organizationname= :orgName");
			query.setParameter("orgName", orgName);
			List<Organization> orgList = query.getResultList();
			
			for(Organization orgToCheck: orgList) { // find atleast one org whose id not same as provided Org's id
				if(orgToCheck.getOrganizationID() != orgId) {
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
	
}

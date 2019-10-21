package com.company.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.dao.OrganizationDAO;
import com.company.dbconfig.DBConfig;
import com.company.model.Organization;

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
	
}

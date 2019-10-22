package com.company.dbconfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.company.model.Organization;
import com.company.model.User;


public class DBConfig 
{
	private static SessionFactory factory=null;
	
	public static SessionFactory getFactory() {
		return factory;
	}

	public static void setFactory(SessionFactory factory) {
		DBConfig.factory = factory;
	}

	static
	{
		try
		{
			loadSessionFactory();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void loadSessionFactory()
	{
		Configuration configuration=new Configuration();
		configuration.configure("Hibernate.cfg.xml");
		configuration.addAnnotatedClass(Organization.class);
		configuration.addAnnotatedClass(User.class);
		
		ServiceRegistry registry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		setFactory(configuration.buildSessionFactory(registry));
	}
	
	public static Session getSession() throws Exception
	{
		Session session=getFactory().openSession();
		return session;
	}
}

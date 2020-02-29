package dss.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		try{
		if(sessionFactory==null)
		{
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return sessionFactory;
	}
	
	public static void destroySessionFactory() {
		if(sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}
	
}

package dss.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dss.util.HibernateUtil;

@WebListener
public class ContextListener implements ServletContextListener
 {

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.destroySessionFactory();
	}
}

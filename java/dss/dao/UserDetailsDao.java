package dss.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dss.dto.UserDetails;
import dss.entity.UserEntity;
import dss.util.HibernateUtil;

public class UserDetailsDao {
	
	
	/*public static void main(String[] args) {
		UserDetailsDao dao = new UserDetailsDao();
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("Paras");
		userDetails.setUserPass("Paras@123");
		dao.saveUser(userDetails);
	}*/
	
	public UserDetails authenticateUser(String userName) throws Exception{
		SessionFactory sessionFactory = null;
		Session session = null;
		
		UserDetails userDetails = null;
		try
		{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query catQuery = session.createQuery("from UserEntity where userName = ?1");
			catQuery.setParameter(1, userName);
			List<UserEntity> userEntityList =  catQuery.getResultList();
			if(userEntityList != null && userEntityList.size() > 0) {
				UserEntity userEntity = userEntityList.get(0);
				userDetails = new UserDetails();
				userDetails.setUserName(userEntity.getUserName());
				userDetails.setUserPass(userEntity.getUserPass());
			}
			return userDetails;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
			
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
	}
	
	
	public void saveUser(UserDetails userDetails){
		SessionFactory sessionFactory = null;
		Session session = null;
		try
		{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			UserEntity userentity = new UserEntity();
			
			userentity.setUserName(userDetails.getUserName());
			userentity.setUserPass(userDetails.getUserPass());
			Calendar calendar = Calendar.getInstance(); 
			Timestamp now = new Timestamp(calendar.getTime().getTime());
			userentity.setAccCreateTime(now);
			
			session.save(userentity);
			session.getTransaction().commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
			
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
	}

}

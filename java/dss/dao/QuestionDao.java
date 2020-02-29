package dss.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dss.dto.Category;
import dss.dto.Options;
import dss.dto.Question;
import dss.entity.CategoryEntity;
import dss.entity.OptionsEntity;
import dss.entity.QuestionEntity;
import dss.service.DssService;
import dss.service.locator.ServiceLocator;
import dss.util.HibernateUtil;

public class QuestionDao {

	public Category getCategorybyId(Integer categoryId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try
		{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query catQuery = session.createQuery(" from CategoryEntity where categoryId = ?1");
			catQuery.setParameter(1,categoryId);
			CategoryEntity categoryEntity = (CategoryEntity) catQuery.getSingleResult();
			Category category = new Category();
			category.setCategoryId(categoryEntity.getCategoryId());
			category.setCategoryName(categoryEntity.getCategoryName());
			return category;
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
	/*public static void main(String[] args) throws Exception {
		new QuestionDao().getQuestionList();
	}*/
	private static final int QUESTIONS_TO_DISPLAY_PER_CATEGORY = 2; 
	public List<Integer>loadCategoryIds(){
		SessionFactory sessionFactory = null;
		Session session = null;
		try
		{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query catQuery = session.createQuery("select categoryId from CategoryEntity");
			List<Integer>catList = catQuery.getResultList();
			return catList;
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
	
	public  List<Question> getQuestionList() 
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		try
		{
			List<Question>questionList = new ArrayList<>();
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Integer>questionIdList = new ArrayList<>();
			
			DssService dssService = ServiceLocator.getDssService();
			
			List<Integer>catList = dssService.getCategoryIds();
			for (Integer category : catList) {
				Query questQuery = session.createQuery("select questionId from QuestionEntity where category_Id=?1");
				questQuery.setParameter(1, category);
				List<Integer>list = questQuery.getResultList();
				Collections.shuffle(list);
				for (int i=0;i<QUESTIONS_TO_DISPLAY_PER_CATEGORY;i++) {
					questionIdList.add(list.get(i));
				}
			}
			Collections.shuffle(questionIdList);
			System.out.println("question list: "+questionIdList);
			System.out.println("question list size: "+questionIdList.size());
			Query questionListQuery = session.createQuery("from QuestionEntity where questionId in ?1");
			questionListQuery.setParameter(1, questionIdList);
			List<QuestionEntity>quesList = questionListQuery.getResultList();
			for (QuestionEntity questionEntity : quesList) {
				List<Options> optionsList = new ArrayList<Options>();
				
				Query optionListQuery = session.createQuery("from OptionsEntity where question_Id in ?1");
				optionListQuery.setParameter(1, questionEntity.getQuestionId());
				List<OptionsEntity>optionList = optionListQuery.getResultList();
				for (OptionsEntity optionEntity : optionList) {
					Options options = new Options();
					options.setOptionId(optionEntity.getOptionId());
					options.setOptionName(optionEntity.getOptionName());
					options.setQuestionId(optionEntity.getQuestionEntity().getQuestionId());
					optionsList.add(options);
				}
				
				System.out.println(questionEntity.getCategoryEntity().getCategoryName() + " - " + questionEntity.getQuestion());
				Question question = new Question();
				question.setQuestionId(questionEntity.getQuestionId());
				question.setQuestionName(questionEntity.getQuestion());
				question.setOptionsList(optionsList);
				question.setCategoryId(questionEntity.getCategoryEntity().getCategoryId());
				questionList.add(question);
			}
			return questionList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
			//throw new Exception(e);
		}
		finally{
			if(session!=null)
			{
				session.close();
			}
		}
	}
	
}

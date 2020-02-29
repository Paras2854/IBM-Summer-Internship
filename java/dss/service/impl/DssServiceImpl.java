package dss.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dss.dao.QuestionDao;
import dss.dao.UserDetailsDao;
import dss.dto.Category;
import dss.dto.CategoryInterest;
import dss.dto.Question;
import dss.dto.UserDetails;
import dss.service.DssService;
import dss.service.locator.ServiceLocator;

public class DssServiceImpl implements DssService{

	private static final int NO_OF_QUES_PER_CATEGORY = 2;
	private static final int NO_OF_OPTIONS_PER_QUESTION = 3;
	private static final int MAXIMUM_INTEREST = 100;
	private static final int SOME_INTEREST = 50;
	private static final int NO_INTEREST = 0;
	
	public List<Question>loadAllQuestions() throws Exception{
		try {
		QuestionDao questionDao = new QuestionDao();
		List<Question>questionList = questionDao.getQuestionList();
		if(questionList == null || questionList.size() == 0) {
			throw new Exception("Question List is empty");
		}
		return questionList;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<Integer> getCategoryIds() throws Exception{
		try {
		QuestionDao questionDao = new QuestionDao();
		List<Integer> idsList = questionDao.loadCategoryIds();
		if(idsList == null || idsList.size() == 0) {
			throw new Exception("Category Id List is empty");
		}
		return idsList;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<CategoryInterest>  calculateCategoryInterest(Map<Integer,List<String>>catIdQuestionIdsMap) throws Exception{
		List<Integer>catIds = ServiceLocator.getDssService().getCategoryIds();
		
		List<CategoryInterest>categoryInterestList = new ArrayList<CategoryInterest>();
		for (int i = 0;i < catIds.size(); i++) {
			Integer categoryId = catIds.get(i);
			List<String>questionAndOptionList = catIdQuestionIdsMap.get(categoryId);
			int totalInterestPerCategory = 0;
			for (String questionAndOption : questionAndOptionList) {
				String option = questionAndOption.split("_")[1];
				Integer optionId = Integer.parseInt(option);
				switch(optionId % NO_OF_OPTIONS_PER_QUESTION) {
				case 2:{
					totalInterestPerCategory += MAXIMUM_INTEREST;
					break;
				}
				case 0:{
					totalInterestPerCategory += SOME_INTEREST;
					break;
				}
				case 1:{
					totalInterestPerCategory += NO_INTEREST;
					break;
				}
				}
			}
			float percent = totalInterestPerCategory/NO_OF_QUES_PER_CATEGORY;
			CategoryInterest categoryInterest = new CategoryInterest();
			categoryInterest.setCategoryId(categoryId);
			QuestionDao dao = new QuestionDao();
			Category category = dao.getCategorybyId(categoryId);
			categoryInterest.setCategoryName(category.getCategoryName());
			categoryInterest.setInterest(percent);
			categoryInterestList.add(categoryInterest);
		}
		return categoryInterestList;
	}
	
	public void addUser(UserDetails userDetails) throws Exception {
		try {
			UserDetailsDao dao = new UserDetailsDao();
			dao.saveUser(userDetails);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	public void validateDuplicate(UserDetails userDetails) throws Exception{
		UserDetailsDao dao = new UserDetailsDao();
		UserDetails dbDetails = dao.authenticateUser(userDetails.getUserName());
		if(dbDetails != null) {
			throw new Exception("User name already exists!");
		}
	}
	
	public void validateLoginUser(UserDetails inputDetails ) throws Exception{
		try {
		UserDetailsDao dao = new UserDetailsDao();
		UserDetails dbDetails = dao.authenticateUser(inputDetails.getUserName());
		
		//validate username
		if(dbDetails == null) {
			throw new Exception("User is not registered");
		}
		
		//validating user password
		if(!(inputDetails.getUserPass().equals(dbDetails.getUserPass()))) {
			throw new Exception("Invalid Password!! Please try again");
		}
		
		
		}
		catch(Exception e) {
			throw e;
		}
	}
}

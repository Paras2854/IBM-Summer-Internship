package dss.service;

import java.util.List;
import java.util.Map;

import dss.dto.CategoryInterest;
import dss.dto.Question;
import dss.dto.UserDetails;

public interface DssService {

	public List<Question>loadAllQuestions() throws Exception;
	public List<Integer> getCategoryIds() throws Exception;
	public List<CategoryInterest>  calculateCategoryInterest(Map<Integer,List<String>>catIdQuestionIdsMap) throws Exception;
	
	public void addUser(UserDetails userDetails) throws Exception;
	public void validateLoginUser(UserDetails userDetails) throws Exception;
	public void validateDuplicate(UserDetails userDetails) throws Exception;
}

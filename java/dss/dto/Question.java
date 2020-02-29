package dss.dto;

import java.util.List;

public class Question {

	private Integer questionId;
	private String questionName;
	private List<Options> optionsList;
	private Integer categoryId;
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public List<Options> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<Options> optionsList) {
		this.optionsList = optionsList;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}

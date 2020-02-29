package dss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class QuestionEntity {
	@Id
	@Column(name="question_id")
	private Integer questionId;
	@Column(name="question_name")
	private String question;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntity;
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}
	
	
	
}

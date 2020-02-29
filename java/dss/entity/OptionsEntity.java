package dss.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table; 

@Entity
@Table(name="options")
public class OptionsEntity {
	@Id
	@Column(name="option_id")
    private Integer optionId;
	@Column(name="option_name")
	private String optionName;
    @OneToOne
    @JoinColumn(name="question_id")
    private QuestionEntity questionEntity;
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}
	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}
    
    
}

package dss.dto;

public class CategoryInterest {

	private Integer categoryId;
	private String categoryName;
	private float interest;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	} 
	
}

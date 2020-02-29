package dss.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
    @Entity
    @Table(name="user_details")
public class UserEntity {
    @Id
    @Column(name="user_id")	
    @SequenceGenerator(name="user_id_key",allocationSize=1,sequenceName="user_id_seq")
    @GeneratedValue(generator="user_id_key",strategy=GenerationType.SEQUENCE)
	private Integer userId;
    @Column(name="user_name")
	private String userName;
    @Column(name="user_password")
	private String userPass;
    @Column(name="acct_create_time")
	private Timestamp accCreateTime;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Timestamp getAccCreateTime() {
		return accCreateTime;
	}
	public void setAccCreateTime(Timestamp accCreateTime) {
		this.accCreateTime = accCreateTime;
	}
	
}

package tw.com.SF.bowlingWeb.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="achievementsType")
public class AchievementsType implements Serializable{
	
	@Id
	@Column(name="achievementsType_ID")
	private int achievementsTypeId;
	
	
	@Column(name="Achievements_ID")
	private String achievementsID;
	
	@Column(name="Achievements_Name")
	private String achievementsName;
	
	
	@Column(name="Achievements_Message")
	private String AchievementsMessage;

	@Column(name="orderType")
	private int orderType;

	public int getAchievementsTypeId() {
		return achievementsTypeId;
	}

	public void setAchievementsTypeId(int achievementsTypeId) {
		this.achievementsTypeId = achievementsTypeId;
	}

	public String getAchievementsID() {
		return achievementsID;
	}

	public void setAchievementsID(String achievementsID) {
		this.achievementsID = achievementsID;
	}

	public String getAchievementsName() {
		return achievementsName;
	}

	public void setAchievementsName(String achievementsName) {
		this.achievementsName = achievementsName;
	}

	public String getAchievementsMessage() {
		return AchievementsMessage;
	}

	public void setAchievementsMessage(String achievementsMessage) {
		AchievementsMessage = achievementsMessage;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	
	

}

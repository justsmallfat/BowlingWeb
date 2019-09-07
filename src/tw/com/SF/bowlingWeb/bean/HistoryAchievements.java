package tw.com.SF.bowlingWeb.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="historyachievements")
public class HistoryAchievements implements Serializable{
	

	@Id
	@Column(name="Achievements_ID")
	private String achievementsID;

	@Id
	@Column(name="teamID")
	private String teamID;
	
	@Id
	@Column(name="seasonID")
	private int seasonID;
	
	
	@Column(name="Achievements_Name")
	private String achievementsName;

	@Column(name="Player1_ID")
	private String player1Id;
	
	@Column(name="player1_Name")
	private String player1Name;

	@Column(name="player1_Message")
	private String player1Message;
	
	@Column(name="Player2_ID")
	private String player2Id;
	
	@Column(name="player2_Name")
	private String player2Name;

	@Column(name="player2_Message")
	private String player2Message;
	
	@Column(name="Player3_ID")
	private String player3Id;
	
	@Column(name="player3_Name")
	private String player3Name;

	@Column(name="player3_Message")
	private String player3Message;
	
	@Column(name="AchievementsMessage")
	private String achievementsMessage;

	@Column(name="Update_Date")
	private String updateDate;

	public String getAchievementsID() {
		return achievementsID;
	}

	public void setAchievementsID(String achievementsID) {
		this.achievementsID = achievementsID;
	}
	
	

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public int getSeasonID() {
		return seasonID;
	}

	public void setSeasonID(int seasonID) {
		this.seasonID = seasonID;
	}

	public String getAchievementsName() {
		return achievementsName;
	}

	public void setAchievementsName(String achievementsName) {
		this.achievementsName = achievementsName;
	}

	public String getPlayer1Id() {
		return player1Id;
	}

	public void setPlayer1Id(String player1Id) {
		this.player1Id = player1Id;
	}

	public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	public String getPlayer1Message() {
		return player1Message;
	}

	public void setPlayer1Message(String player1Message) {
		this.player1Message = player1Message;
	}

	public String getPlayer2Id() {
		return player2Id;
	}

	public void setPlayer2Id(String player2Id) {
		this.player2Id = player2Id;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}

	public String getPlayer2Message() {
		return player2Message;
	}

	public void setPlayer2Message(String player2Message) {
		this.player2Message = player2Message;
	}

	public String getPlayer3Id() {
		return player3Id;
	}

	public void setPlayer3Id(String player3Id) {
		this.player3Id = player3Id;
	}

	public String getPlayer3Name() {
		return player3Name;
	}

	public void setPlayer3Name(String player3Name) {
		this.player3Name = player3Name;
	}

	public String getPlayer3Message() {
		return player3Message;
	}

	public void setPlayer3Message(String player3Message) {
		this.player3Message = player3Message;
	}

	public String getAchievementsMessage() {
		return achievementsMessage;
	}

	public void setAchievementsMessage(String achievementsMessage) {
		this.achievementsMessage = achievementsMessage;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	

}

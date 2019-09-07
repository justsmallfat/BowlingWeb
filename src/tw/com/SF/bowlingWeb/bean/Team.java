package tw.com.SF.bowlingWeb.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="team")
public class Team implements Serializable{
	
	@Id
	@Column(name="teamID")
	private String teamID;
	
	@Column(name="teamName")
	private String teamName;

	@Id
	@Column(name="playerId")
	private String playerId;
	
	@Column(name="playerName")
	private String playerName;
	
	@Column(name="pasword")
	private String pasword;
	
	@Column(name="Creat_Date")
	private String Creat_Date;
	
	@Column(name="Update_Date")
	private String Update_Date;
	
	@Column(name="lastLoginTime")
	private String lastLoginTime;
	
	@Column(name="deviceToken")
	private String deviceToken;


	public String getTeamID() {
		return teamID;
	}


	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public String getPlayerId() {
		return playerId;
	}


	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public String getPasword() {
		return pasword;
	}


	public void setPasword(String pasword) {
		this.pasword = pasword;
	}


	public String getCreat_Date() {
		return Creat_Date;
	}


	public void setCreat_Date(String creat_Date) {
		Creat_Date = creat_Date;
	}


	public String getUpdate_Date() {
		return Update_Date;
	}


	public void setUpdate_Date(String update_Date) {
		Update_Date = update_Date;
	}


	public String getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	public String getDeviceToken() {
		return deviceToken;
	}


	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}


	@Override
	public String toString() {
		return "Team [teamID=" + teamID + ", teamName=" + teamName + ", playerId=" + playerId + ", playerName="
				+ playerName + ", pasword=" + pasword + ", Creat_Date=" + Creat_Date + ", Update_Date=" + Update_Date
				+ ", lastLoginTime=" + lastLoginTime + ", deviceToken=" + deviceToken + "]";
	}
	
	
	
}

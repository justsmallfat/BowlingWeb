package tw.com.SF.bowlingWeb.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="player")
public class Player implements Serializable{
	private static final long serialVersionUID = -9128999381149500218L;

	@Id
	@Column(name="Player_ID")
	private String playerId;
	
	@Column(name="Player_Name")
	private String playerName;
	
	@Column(name="Player_Title")
	private String playerTitle;

	@Id
	@Column(name="teamID")
	private String teamID;

	@Id
	@Column(name="seasonID")
	private int seasonID;
	
	
	@Column(name="Score_Highest")
	private String Score_Highest;

	
	@Column(name="Score_ave")
	private String Score_ave;
	
	
	@Column(name="Score_Low")
	private String Score_Low;
	
	@Column(name="money")
	private String money;
	
	@Column(name="stike_Count")
	private String stike_Count;
	
	@Column(name="spare_Count")
	private String spare_Count;
	
	@Column(name="stike_Rate")
	private String stike_Rate;

	@Column(name="spare_Rate")
	private String spare_Rate;
	
	@Column(name="strike_Ave")
	private String strike_Ave;
	
	@Column(name="spare_Ave")
	private String spare_Ave;
	
	@Column(name="box_1_Ave")
	private String box_1_Ave;
	
	@Column(name="box_2_Ave")
	private String box_2_Ave;
	
	@Column(name="box_3_Ave")
	private String box_3_Ave;
	
	@Column(name="box_4_Ave")
	private String box_4_Ave;
	
	@Column(name="box_5_Ave")
	private String box_5_Ave;
	
	@Column(name="box_6_Ave")
	private String box_6_Ave;
	
	@Column(name="box_7_Ave")
	private String box_7_Ave;
	
	@Column(name="box_8_Ave")
	private String box_8_Ave;
	
	@Column(name="box_9_Ave")
	private String box_9_Ave;
	
	@Column(name="box_10_Ave")
	private String box_10_Ave;
	
	@Column(name="everyGames")
	private String everyGames;
	
	@Column(name="gamesCount")
	private int gamesCount;
	

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

	public String getPlayerTitle() {
		return playerTitle;
	}

	public void setPlayerTitle(String playerTitle) {
		this.playerTitle = playerTitle;
	}

	public String getScore_Highest() {
		return Score_Highest;
	}

	public void setScore_Highest(String score_Highest) {
		Score_Highest = score_Highest;
	}

	public String getScore_ave() {
		return Score_ave;
	}

	public void setScore_ave(String score_ave) {
		Score_ave = score_ave;
	}

	public String getScore_Low() {
		return Score_Low;
	}

	public void setScore_Low(String score_Low) {
		Score_Low = score_Low;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStike_Count() {
		return stike_Count;
	}

	public void setStike_Count(String stike_Count) {
		this.stike_Count = stike_Count;
	}

	public String getSpare_Count() {
		return spare_Count;
	}

	public void setSpare_Count(String spare_Count) {
		this.spare_Count = spare_Count;
	}

	public String getStike_Rate() {
		return stike_Rate;
	}

	public void setStike_Rate(String stike_Rate) {
		this.stike_Rate = stike_Rate;
	}

	public String getSpare_Rate() {
		return spare_Rate;
	}

	public void setSpare_Rate(String spare_Rate) {
		this.spare_Rate = spare_Rate;
	}

	public String getStrike_Ave() {
		return strike_Ave;
	}

	public void setStrike_Ave(String strike_Ave) {
		this.strike_Ave = strike_Ave;
	}

	public String getSpare_Ave() {
		return spare_Ave;
	}

	public void setSpare_Ave(String spare_Ave) {
		this.spare_Ave = spare_Ave;
	}

	public String getBox_1_Ave() {
		return box_1_Ave;
	}

	public void setBox_1_Ave(String box_1_Ave) {
		this.box_1_Ave = box_1_Ave;
	}

	public String getBox_2_Ave() {
		return box_2_Ave;
	}

	public void setBox_2_Ave(String box_2_Ave) {
		this.box_2_Ave = box_2_Ave;
	}

	public String getBox_3_Ave() {
		return box_3_Ave;
	}

	public void setBox_3_Ave(String box_3_Ave) {
		this.box_3_Ave = box_3_Ave;
	}

	public String getBox_4_Ave() {
		return box_4_Ave;
	}

	public void setBox_4_Ave(String box_4_Ave) {
		this.box_4_Ave = box_4_Ave;
	}

	public String getBox_5_Ave() {
		return box_5_Ave;
	}

	public void setBox_5_Ave(String box_5_Ave) {
		this.box_5_Ave = box_5_Ave;
	}

	public String getBox_6_Ave() {
		return box_6_Ave;
	}

	public void setBox_6_Ave(String box_6_Ave) {
		this.box_6_Ave = box_6_Ave;
	}

	public String getBox_7_Ave() {
		return box_7_Ave;
	}

	public void setBox_7_Ave(String box_7_Ave) {
		this.box_7_Ave = box_7_Ave;
	}

	public String getBox_8_Ave() {
		return box_8_Ave;
	}

	public void setBox_8_Ave(String box_8_Ave) {
		this.box_8_Ave = box_8_Ave;
	}

	public String getBox_9_Ave() {
		return box_9_Ave;
	}

	public void setBox_9_Ave(String box_9_Ave) {
		this.box_9_Ave = box_9_Ave;
	}

	public String getBox_10_Ave() {
		return box_10_Ave;
	}

	public void setBox_10_Ave(String box_10_Ave) {
		this.box_10_Ave = box_10_Ave;
	}

	public String getEveryGames() {
		return everyGames;
	}

	public void setEveryGames(String everyGames) {
		this.everyGames = everyGames;
	}
	
	

	public int getGamesCount() {
		return gamesCount;
	}

	public void setGamesCount(int gamesCount) {
		this.gamesCount = gamesCount;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", playerTitle=" + playerTitle
				+ ", teamID=" + teamID + ", seasonID=" + seasonID + ", Score_Highest=" + Score_Highest + ", Score_ave="
				+ Score_ave + ", Score_Low=" + Score_Low + ", money=" + money + ", stike_Count=" + stike_Count
				+ ", spare_Count=" + spare_Count + ", stike_Rate=" + stike_Rate + ", spare_Rate=" + spare_Rate
				+ ", strike_Ave=" + strike_Ave + ", spare_Ave=" + spare_Ave + ", box_1_Ave=" + box_1_Ave
				+ ", box_2_Ave=" + box_2_Ave + ", box_3_Ave=" + box_3_Ave + ", box_4_Ave=" + box_4_Ave + ", box_5_Ave="
				+ box_5_Ave + ", box_6_Ave=" + box_6_Ave + ", box_7_Ave=" + box_7_Ave + ", box_8_Ave=" + box_8_Ave
				+ ", box_9_Ave=" + box_9_Ave + ", box_10_Ave=" + box_10_Ave + ", everyGames=" + everyGames + "]";
	}

	
}

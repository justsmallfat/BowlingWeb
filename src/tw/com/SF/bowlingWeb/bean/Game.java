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
@Table(name="game")
public class Game implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322556695773967791L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Game_ID")
	private int gameId;

	@Column(name="teamID")
	private String teamID;
	
	@Column(name="Game_Name")
	private String gameName;

	@Column(name="Player_ID")
	private String playerId;
	
	@Column(name="player_Name")
	private String playerName;
	
	@Column(name="Box_1")
	private String box1;
	

	@Column(name="Box_2")
	private String box2;
	

	@Column(name="Box_3")
	private String box3;
	

	@Column(name="Box_4")
	private String box4;
	

	@Column(name="Box_5")
	private String box5;
	

	@Column(name="Box_6")
	private String box6;
	

	@Column(name="Box_7")
	private String box7;
	

	@Column(name="Box_8")
	private String box8;
	

	@Column(name="Box_9")
	private String box9;
	

	@Column(name="Box_10")
	private String box10;
	
	
	@Column(name="Score")
	private int score;
	
	@Column(name="Rank")
	private String rank;
	
	@Column(name="Creat_Date")
	private String creatDate;
	
	@Column(name="Update_Date")
	private String updateDate;
	
	@Column(name="money")
	private String money;
	

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getBox1() {
		return box1;
	}

	public void setBox1(String box1) {
		this.box1 = box1;
	}

	public String getBox2() {
		return box2;
	}

	public void setBox2(String box2) {
		this.box2 = box2;
	}

	public String getBox3() {
		return box3;
	}

	public void setBox3(String box3) {
		this.box3 = box3;
	}

	public String getBox4() {
		return box4;
	}

	public void setBox4(String box4) {
		this.box4 = box4;
	}

	public String getBox5() {
		return box5;
	}

	public void setBox5(String box5) {
		this.box5 = box5;
	}

	public String getBox6() {
		return box6;
	}

	public void setBox6(String box6) {
		this.box6 = box6;
	}

	public String getBox7() {
		return box7;
	}

	public void setBox7(String box7) {
		this.box7 = box7;
	}

	public String getBox8() {
		return box8;
	}

	public void setBox8(String box8) {
		this.box8 = box8;
	}

	public String getBox9() {
		return box9;
	}

	public void setBox9(String box9) {
		this.box9 = box9;
	}

	public String getBox10() {
		return box10;
	}

	public void setBox10(String box10) {
		this.box10 = box10;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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
	
	
	

}

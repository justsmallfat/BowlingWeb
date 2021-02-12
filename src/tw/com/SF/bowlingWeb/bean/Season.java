package tw.com.SF.bowlingWeb.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Season")
public class Season implements Serializable{
	
	@Id
	@Column(name="seasonID")
	private long seasonID;

	@Id
	@Column(name="teamID")
	private String teamID;
	
	@Column(name="seasonName")
	private String seasonName;
	
	@Column(name="seasonStartDate")
	private String seasonStartDate;
	
	@Column(name="seasonEndDate")
	private String seasonEndDate;
	
	@Column(name="notes")
	private String notes;


	@Column(name="Update_Date")
	private String updateDate;

	public long getSeasonID() {
		return seasonID;
	}

	public void setSeasonID(long seasonID) {
		this.seasonID = seasonID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(String seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public String getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(String seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Season [seasonID=" + seasonID + ", seasonName=" + seasonName + ", seasonStartDate=" + seasonStartDate
				+ ", seasonEndDate=" + seasonEndDate + ", notes=" + notes + "]";
	}
	
	

}

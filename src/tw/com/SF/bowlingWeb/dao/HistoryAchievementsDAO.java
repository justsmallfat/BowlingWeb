package tw.com.SF.bowlingWeb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.com.SF.bowlingWeb.bean.HistoryAchievements;

@Repository
public class HistoryAchievementsDAO extends AbstractDAO<HistoryAchievements> {
	
	public List getAllHistoryAchievements(String teamId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId));
		return criteria.list();		
	}
	
	public List getSeasonAchievements(String teamId, int seasonId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId));
		criteria.add(Restrictions.eq("seasonID", seasonId));
		return criteria.list();		
	}
	
	public HistoryAchievements getHistoryAchievementsByID(String achID, String teamId) throws Exception {
//		
//		Criteria criteria = createCriteria();	
//		criteria.add(Restrictions.eq("achievementsID", achID));
//		criteria.add(Restrictions.eq("teamID", teamId));
//		HistoryAchievements returnHistoryAchievements = null;
//		if(criteria.list() != null && criteria.list().size()>0){
//			returnHistoryAchievements = (HistoryAchievements) criteria.list().get(0);
//		}else{
//			returnHistoryAchievements = new HistoryAchievements();
//			returnHistoryAchievements.setAchievementsID(achID);
//			returnHistoryAchievements.setTeamID(teamId);
//		}
		
		return getHistoryAchievementsByID(achID, teamId, 0);		
	}
	
	public HistoryAchievements getHistoryAchievementsByID(String achID, String teamId, int seasonId) throws Exception {				
		Criteria criteria = createCriteria();	
		criteria.add(Restrictions.eq("achievementsID", achID));
		criteria.add(Restrictions.eq("teamID", teamId));
		criteria.add(Restrictions.eq("seasonID", seasonId));
		HistoryAchievements returnHistoryAchievements = null;
		if(criteria.list() != null && criteria.list().size()>0){
			returnHistoryAchievements = (HistoryAchievements) criteria.list().get(0);
		}else{
			returnHistoryAchievements = new HistoryAchievements();
			returnHistoryAchievements.setAchievementsID(achID);
			returnHistoryAchievements.setTeamID(teamId);
			returnHistoryAchievements.setSeasonID(seasonId);
		}
		
		return returnHistoryAchievements;		
	}
	
	public void setHistoryAchievements(HistoryAchievements historyAchievements) throws Exception {
		this.insertOrUpdate(historyAchievements);
	}
	
}

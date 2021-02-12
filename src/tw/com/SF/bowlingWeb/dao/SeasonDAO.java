package tw.com.SF.bowlingWeb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.com.SF.bowlingWeb.bean.AchievementsType;
import tw.com.SF.bowlingWeb.bean.Game;
import tw.com.SF.bowlingWeb.bean.Season;

@Repository
public class SeasonDAO extends AbstractDAO<Season> {
	
	public List getAllSeasons() throws Exception {				
		Criteria criteria = createCriteria();	
		return criteria.list();		
	}
	
	public List getTeamSeasons(String teamId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId)).addOrder(Order.desc("seasonID"));	
		return criteria.list();		
	}

	public Season getSeasonByTeamID(long seasonID, String teamId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId)).add(Restrictions.eq("seasonID", seasonID));	
		return (Season) criteria.list().get(0);		
	}
	
}

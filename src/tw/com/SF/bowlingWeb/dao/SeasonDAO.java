package tw.com.SF.bowlingWeb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.com.SF.bowlingWeb.bean.AchievementsType;
import tw.com.SF.bowlingWeb.bean.Season;

@Repository
public class SeasonDAO extends AbstractDAO<Season> {
	
	public List getAllSeasons() throws Exception {				
		Criteria criteria = createCriteria();	
		return criteria.list();		
	}
	
	public List getTeamSeasons(String teamId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId));	
		return criteria.list();		
	}
	
}

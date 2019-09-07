package tw.com.SF.bowlingWeb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import tw.com.SF.bowlingWeb.bean.AchievementsType;

@Repository
public class AchievementsTypeDAO extends AbstractDAO<AchievementsType> {
	
	public List getAllAchievementsTypes() throws Exception {				
		Criteria criteria = createCriteria();	
		return criteria.list();		
	}
	
}

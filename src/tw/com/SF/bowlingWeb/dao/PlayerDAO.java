package tw.com.SF.bowlingWeb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import tw.com.SF.bowlingWeb.bean.Player;

@Repository
public class PlayerDAO extends AbstractDAO<Player> {
	
	public Player getPlayerByIdAndTeam(String playerId,String teamId,int seasonID) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("playerId", playerId));
		criteria.add(Restrictions.eq("teamID", teamId));	
		criteria.add(Restrictions.eq("seasonID", seasonID));	
		Player player = null;
		if(criteria.list().size()>0){
			player = (Player) criteria.list().get(0); 
		}else{

		}
		return player;		
	}
	
	public Player getOrCreatSeasonPlayerByIdAndTeam(String playerId,String teamId,int seasonID) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("playerId", playerId));
		criteria.add(Restrictions.eq("teamID", teamId));	
		criteria.add(Restrictions.eq("seasonID", seasonID));	
		Player player = null;
		if(criteria.list().size()>0){
			player = (Player) criteria.list().get(0); 
		}else{
			//新賽季重整
			//可能取不到資料變成取生涯資訊，所以需要再設定季ID
			player = new Player();
			Player playerLife = getPlayerByIdAndTeam(playerId,teamId, 0);
			player.setPlayerId(playerLife.getPlayerId());
			player.setPlayerName(playerLife.getPlayerName());
			player.setTeamID(playerLife.getTeamID());
			player.setSeasonID(seasonID);
		}
		return player;		
	}
	
	public List getPlayerById(String playerId,String teamId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("playerId", playerId));
		criteria.add(Restrictions.eq("teamID", teamId));	
		return criteria.list();		
	}
	
	public List getPlayerByOrder(String order,int orderType, String teamId) throws Exception {				
		return getPlayerByOrder(order, orderType, teamId, 0);		
	}
	
	public List getPlayerByOrder(String order,int orderType, String teamId, int seasonId) throws Exception {				
		Criteria criteria = createCriteria();
		if(orderType>0){
			criteria.addOrder(Order.desc(order));
		}else{
			criteria.addOrder(Order.asc(order));
		}
		criteria.add(Restrictions.eq("teamID", teamId));
		criteria.add(Restrictions.eq("seasonID", seasonId));
		
		logger.info("dao : "+criteria.list().size());		
		return criteria.list();		
	}
	
	public List getAllPlayer() throws Exception {				
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.desc("gamesCount"));
		criteria.addOrder(Order.asc("teamID"));
		criteria.add(Restrictions.eq("seasonID", 0));
		logger.info("dao : "+criteria.list().size());		
		return criteria.list();		
	}
	
	public List getTeamPlayer(String teamId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId));
		criteria.add(Restrictions.eq("seasonID", 0));
		criteria.addOrder(Order.desc("gamesCount"));
		logger.info("dao : "+criteria.list().size());		
		return criteria.list();		
	}
	
	public Player getTeamSeasonData(String teamId, String playerId, int seasonId) throws Exception {				
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId));
		criteria.add(Restrictions.eq("playerId", playerId));
		criteria.add(Restrictions.eq("seasonID", seasonId));
		criteria.addOrder(Order.desc("gamesCount"));
		criteria.addOrder(Order.desc("seasonID"));
		Player player = null;
		if(criteria.list()!=null && criteria.list().size()>0){
			player = (Player) criteria.list().get(0); 
		}else{
			//新賽季重整
			//可能取不到資料變成取生涯資訊，所以需要再設定季ID
			player = new Player();
			Player playerLife = getPlayerByIdAndTeam(playerId,teamId, 0);
			player.setPlayerId(playerLife.getPlayerId());
			player.setPlayerName(playerLife.getPlayerName());
			player.setTeamID(playerLife.getTeamID());
			player.setSeasonID(seasonId);
		}
		return player;		
	}
	
	public void setPlayer(Player player) throws Exception {
		this.insertOrUpdate(player);
	}
	
}

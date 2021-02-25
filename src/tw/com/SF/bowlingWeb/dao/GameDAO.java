package tw.com.SF.bowlingWeb.dao;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.com.SF.bowlingWeb.bean.Game;
import tw.com.SF.bowlingWeb.util.StringUtils;

@Repository
public class GameDAO extends AbstractDAO<Game> {
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<Game> getGamesByPlayer(String playerId, String teamId) throws Exception {		
		Criteria criteria = createCriteria();
		if(StringUtils.hasText(playerId)){
			criteria.add(Restrictions.eq("playerId", playerId));
			criteria.add(Restrictions.eq("teamID", teamId));
			
		}
		logger.info("daoget : "+criteria);
		return criteria.list();
		
	}
	
	public List<Game> getGamesByPlayerAndDate(String playerId, String teamId, String startDate, String endDate) throws Exception {		
		Criteria criteria = createCriteria();
		if(StringUtils.hasText(playerId)){
			criteria.add(Restrictions.eq("playerId", playerId));
			criteria.add(Restrictions.eq("teamID", teamId));
		}
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			criteria.add(Restrictions.between("creatDate", startDate, endDate));
		}
		criteria.addOrder(Order.asc("creatDate"));
		return criteria.list();
		
	}
	
	public List<String> getAllGamesNamesByPlayerAndDate(String teamId, String startDate, String endDate) throws Exception {		
		Criteria criteria = createCriteria();
		if(StringUtils.hasText(teamId)){
			criteria.add(Restrictions.eq("teamID", teamId));
		}
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			criteria.add(Restrictions.between("creatDate", startDate, endDate));
		}
		
		criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("gameName"))));
		return criteria.list();
		
	}
	
	public Game getGamesById(String gameId) throws Exception {		
		Criteria criteria = createCriteria();
		if(StringUtils.hasText(gameId)){
			criteria.add(Restrictions.eq("gameId",  Integer.parseInt(gameId)));
		}
		return (Game) criteria.list().get(0);
		
	}
	public Game getGamesByNameAndPlayer(String gameName, String playerId, String teamId) throws Exception {		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("gameName", gameName));
		criteria.add(Restrictions.eq("playerId",  playerId));
		criteria.add(Restrictions.eq("teamID",  teamId));
		if(criteria.list().size()>0){
			return (Game) criteria.list().get(0);
		}else{
			Game game = new Game();
			game.setPlayerId(playerId);
			game.setGameName(gameName);
			game.setTeamID(teamId);
			return game;
		}
		
	}
	
	public List<Game> getTeamGamesByByDates(String startDate, String endDate, String teamId) throws Exception {		
		Criteria criteria = createCriteria();
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			criteria.add(Restrictions.between("creatDate", startDate, endDate));
			criteria.add(Restrictions.eq("teamID", teamId));
		}
		return criteria.list();
		
	}
	
	public List<Game> getGamesByNumber(String startDate, String endDate, String gameNumber) throws Exception {		
		Criteria criteria = createCriteria();
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			criteria.add(Restrictions.between("creatDate", startDate, endDate));
		}
		if(StringUtils.hasText(gameNumber)){
			criteria.add(Restrictions.ilike("gameName", "%_"+gameNumber+"%"));
		}
		return criteria.list();
		
	}
	
	public String getGamesNumber(String gameDate) throws Exception {		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.ilike("gameName", gameDate+"%"));
		
		List<Game> list = criteria.list();
		int newNumber = 1;
		for(Game game: list){
			String number = game.getGameName().replace(gameDate + "_", "");
			int tempNum = Integer.valueOf(number);
			if(newNumber <= tempNum){
				newNumber = tempNum + 1;
			}
		}
		
		String returnName = gameDate + "_" + newNumber;
		return returnName;
		
	}
	
	public void setGames(Game game) throws Exception {
		
		this.insertOrUpdate(game);
	}
	
	public Map<String, String> getGamesAVGs(String playerId, String startDate, String endDate) throws Exception {
		
		Map<String, String> map = new HashMap<>();
		
		String tempString = "";
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("playerId", playerId));
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			criteria.add(Restrictions.between("creatDate", startDate, endDate));
		}
		criteria.setProjection(Projections.avg("score"));
		List list = criteria.list();
				
		if(list.size()>0){
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);    //小數後兩位

			if(list.get(0)!=null){
				tempString = nf.format(list.get(0));
			}else{
				tempString = "0.0";
			}
			map.put("avg", tempString);
		}
		
		criteria.setProjection(Projections.max("score"));
		list = criteria.list();
		if(list.size()>0){
			if(list.get(0)!=null){
				tempString = list.get(0).toString();
			}else{
				tempString = "0";
			}
			map.put("max", tempString);
		}
		
		criteria.setProjection(Projections.min("score"));
		list = criteria.list();
		if(list.size()>0){
			if(list.get(0)!=null){
				tempString = list.get(0).toString();
			}else{
				tempString = "0";
			}
			map.put("min", tempString);
		}
		
		criteria.setProjection(Projections.sum("money"));
		list = criteria.list();
		if(list.size()>0){
			if(list.get(0)!=null){
				tempString = list.get(0).toString();
			}else{
				tempString = "0";
			}
			map.put("money", tempString);
		}
		
		return map;
	}
	
	public String getTop10GamesAVGs(String playerId, String startDate, String endDate) throws Exception {

		StringBuilder stringSB = new StringBuilder();
		for(int i=1;i<10;i++){
			String tempString = "0.0";

			Criteria criteria = createCriteria();
			criteria.add(Restrictions.eq("playerId", playerId));
			
			criteria.add(Restrictions.ilike("gameName", "%_"+i));
			if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
				criteria.add(Restrictions.between("creatDate", startDate, endDate));
			}
			criteria.setProjection(Projections.avg("score"));
			List list = criteria.list();
			
			if(list.size()>0){
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(1);    //小數後兩位

				if(list.get(0)!=null){
					tempString = nf.format(list.get(0));
				}else{
					tempString = "0.0";
				}
			}else{
//				map.put(""+i, tempString);
			}
			if(i==1){
				stringSB.append(tempString);
			}else{
				stringSB.append(",").append(tempString);
			}
			
		}
		
		return stringSB.toString();
	}
	
}

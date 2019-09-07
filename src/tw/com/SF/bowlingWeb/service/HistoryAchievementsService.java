package tw.com.SF.bowlingWeb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.SF.bowlingWeb.bean.AchievementsType;
import tw.com.SF.bowlingWeb.bean.HistoryAchievements;
import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.dao.AchievementsTypeDAO;
import tw.com.SF.bowlingWeb.dao.GameDAO;
import tw.com.SF.bowlingWeb.dao.HistoryAchievementsDAO;
import tw.com.SF.bowlingWeb.dao.PlayerDAO;
@Service
public class HistoryAchievementsService  extends AbstractService{
	@Resource private HistoryAchievementsDAO historyAchievementsDAO;
	@Resource private GameDAO gameDAO;
	@Resource private PlayerDAO playerDAO;
	@Resource private AchievementsTypeDAO achievementsTypeDAO;
	
	public List getAllHistoryAchievements(String teamId) throws Exception {
		refreshAllPlayerAchievements(teamId);
		return  historyAchievementsDAO.getAllHistoryAchievements(teamId);
	}
	
	public List getSeasonAchievements(String teamId, int seasonId) throws Exception {
		refreshSeasonAchievements(teamId, seasonId);
		return  historyAchievementsDAO.getSeasonAchievements(teamId, seasonId);
	}
	
	public Boolean refreshAllPlayerAchievements(String teamId) throws Exception {
		refreshSeasonAchievements(teamId, 0);
		return true;
	}
	
	public Boolean refreshSeasonAchievements(String teamId, int seasonId) throws Exception {
		
		List<AchievementsType> achievementsTypes = achievementsTypeDAO.getAllAchievementsTypes();
		
		for(AchievementsType achievementsType:achievementsTypes){

			String nowAchID = achievementsType.getAchievementsID();
			String nowAchName = achievementsType.getAchievementsName();
			String nowAchMessage = achievementsType.getAchievementsMessage();
			int orderType = achievementsType.getOrderType();
			
			List<Player> allPlayer = playerDAO.getPlayerByOrder(nowAchID,orderType,teamId, seasonId);
			
			HistoryAchievements historyAchievements = historyAchievementsDAO.getHistoryAchievementsByID(nowAchID, teamId, seasonId);
			
			historyAchievements.setAchievementsName(nowAchName);
			historyAchievements.setPlayer1Id(allPlayer.get(0).getPlayerId());
			historyAchievements.setPlayer1Name(allPlayer.get(0).getPlayerName());
			String tempValue = getValueByType(nowAchID, allPlayer.get(0));
			String message = nowAchMessage.replaceAll("qq", tempValue);
			historyAchievements.setPlayer1Message(message);
			
			historyAchievements.setPlayer2Id(allPlayer.get(1).getPlayerId());
			historyAchievements.setPlayer2Name(allPlayer.get(1).getPlayerName());
			nowAchMessage = achievementsType.getAchievementsMessage();
			tempValue = getValueByType(nowAchID, allPlayer.get(1));
			message = nowAchMessage.replaceAll("qq", tempValue);
			historyAchievements.setPlayer2Message(message);
			
			historyAchievements.setPlayer3Id(allPlayer.get(2).getPlayerId());
			historyAchievements.setPlayer3Name(allPlayer.get(2).getPlayerName());
			nowAchMessage = achievementsType.getAchievementsMessage();
			tempValue = getValueByType(nowAchID, allPlayer.get(2));
			message = nowAchMessage.replaceAll("qq", tempValue);
			historyAchievements.setPlayer3Message(message);
			
			historyAchievementsDAO.setHistoryAchievements(historyAchievements);
		}
		return true;
	}
	
	public String getValueByType(String type, Player player) throws Exception {
		String returnString = "0"; 
		
		switch(type) { 
        case "Score_Highest": 
        	returnString = player.getScore_Highest();
            break; 
        case "Score_ave": 
        	returnString = player.getScore_ave();
            break; 
        case "Score_Low": 
        	returnString = player.getScore_Low();
            break; 
        case "stike_Count": 
        	returnString = player.getStike_Count();
            break; 
        case "spare_Count": 
        	returnString = player.getSpare_Count();
        case "stike_Rate": 
        	returnString = player.getStike_Rate();
            break; 
        case "spare_Rate": 
        	returnString = player.getSpare_Rate();
            break; 
        default: 
            System.out.println("得E(不及格)"); 
    }
		
		return returnString;
	}
	
	
//	public void refreshAllPlayerAchievements() throws Exception {
//		List<Game> allGames = gameDAO.getGamesByPlayer("")
//	}
}

package tw.com.SF.bowlingWeb.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.SF.bowlingWeb.bean.Game;
import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.bean.Season;
import tw.com.SF.bowlingWeb.dao.GameDAO;
import tw.com.SF.bowlingWeb.dao.PlayerDAO;
import tw.com.SF.bowlingWeb.dao.SeasonDAO;
import tw.com.SF.bowlingWeb.dao.TeamDAO;
@Service
public class PlayerService  extends AbstractService{
	@Resource private PlayerDAO playerDAO;
	@Resource private GameDAO gameDAO;
	@Resource private SeasonDAO seasonDAO;
	@Resource private TeamDAO teamDAO;
	
	public Player getPlayerLifeByPlayerId(String playerId, String teamId) throws Exception {
		refreshPlayLifeData(playerId,teamId);
		Player player = (Player) playerDAO.getPlayerByIdAndTeam(playerId,teamId, 0);
		
		return  player;
	}
	
	public Player getSeasonPlayerByPlayerId(String playerId, String teamId, long seasonId) throws Exception {
		refreshPlaySeasonData(playerId,teamId, seasonId);
		Player player = (Player) playerDAO.getPlayerByIdAndTeam(playerId, teamId, seasonId);
		return  player;
	}
	
	public List getAllPlayer() throws Exception {
		
		return  playerDAO.getAllPlayer();
	}
	
	public List getTeamPlayer(String teamId) throws Exception {
		List<Player> players = playerDAO.getTeamPlayer(teamId);
		for(Player player:players){
			refreshPlaySeasonData(player.getPlayerId(),teamId, 0);
		}
		
		return  players;
	}
	
	public List getTeamPlayerDuring(String startDate, String endDate, String teamId) throws Exception {
		List<Player> players = playerDAO.getTeamPlayer(teamId);
		List<Player> refreshPlayers = new ArrayList();
		for(Player player:players){
			Player rp = refreshPlayDuringData(player, startDate, endDate);
			if(rp!=null){
				refreshPlayers.add(rp);
			}
		}
		
		
		return  refreshPlayers;
	}
	
	public void refreshPlayLifeData(String playerId, String teamId) throws Exception {

		refreshPlaySeasonData(playerId, teamId, 0);
		
	}
	
	public void refreshTeamAllPlayerSeasonData(String teamId, long seasonId) throws Exception {
		List<Player> players = playerDAO.getTeamPlayer(teamId);
		for(Player player:players){
			refreshPlaySeasonData(player.getPlayerId(), teamId, seasonId);
		}
	}
	
	public void refreshPlaySeasonData(String playerId, String teamId, long seasonId) throws Exception {
		Season season = seasonDAO.getSeasonByTeamID(seasonId, teamId);
		Player player = playerDAO.getOrCreatSeasonPlayerByIdAndTeam(playerId, teamId, seasonId);
		Player refreshPlayer = refreshPlayDuringData(player, season.getSeasonStartDate(), season.getSeasonEndDate());
		
		
//		if(!teamDAO.alivePlayer(playerId, teamId)){
//			return;
//		}
//		
//		List<Game> games =  gameDAO.getGamesByPlayerAndDate(playerId, teamId, season.getSeasonStartDate(), season.getSeasonEndDate());
//		int gamesCount = games.size();
//		int tempCount = player.getGamesCount();
//		
//		if(gamesCount !=0 && tempCount==gamesCount){
//			return;
//		}
//
//		
//		NumberFormat nf = NumberFormat.getInstance();
//		nf.setMaximumFractionDigits(1);    //小數後兩位
//		StringBuilder boxesStringSB = new StringBuilder();
//
//		double[] boxScorearr = new double[10];
//		String[] boxScoreAvg = new String[10];
//		
//		double totalStrike = 0;
//		double totalSpare = 0;
//		
//		for(Game tempGame : games){
//			boxesStringSB.append(tempGame.getBox1()).append(tempGame.getBox2()).append(tempGame.getBox3()).append(tempGame.getBox4()).append(tempGame.getBox5())
//						.append(tempGame.getBox6()).append(tempGame.getBox7()).append(tempGame.getBox8()).append(tempGame.getBox9()).append(tempGame.getBox10()) ;
//			int nowScore = getThisBoxScore(tempGame.getBox1(),tempGame.getBox2(),tempGame.getBox3(),false);
//			boxScorearr[0] = boxScorearr[0] + nowScore;
//			if(tempGame.getBox1().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox1().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore = getThisBoxScore(tempGame.getBox2(),tempGame.getBox3(),tempGame.getBox4(),false);
//			boxScorearr[1] = boxScorearr[1] + nowScore;
//			if(tempGame.getBox2().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox2().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore =  getThisBoxScore(tempGame.getBox3(),tempGame.getBox4(),tempGame.getBox5(),false);
//			boxScorearr[2] = boxScorearr[2] + nowScore;
//			if(tempGame.getBox3().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox3().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore = getThisBoxScore(tempGame.getBox4(),tempGame.getBox5(),tempGame.getBox6(),false);
//			boxScorearr[3] = boxScorearr[3] + nowScore;
//			if(tempGame.getBox4().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox4().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore = getThisBoxScore(tempGame.getBox5(),tempGame.getBox6(),tempGame.getBox7(),false);
//			boxScorearr[4] = boxScorearr[4] + nowScore;
//			if(tempGame.getBox5().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox5().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//
//			nowScore = getThisBoxScore(tempGame.getBox6(),tempGame.getBox7(),tempGame.getBox8(),false);
//			boxScorearr[5] = boxScorearr[5] + nowScore;
//			if(tempGame.getBox6().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox6().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore = getThisBoxScore(tempGame.getBox7(),tempGame.getBox8(),tempGame.getBox9(),false);
//			boxScorearr[6] = boxScorearr[6] + nowScore;
//			if(tempGame.getBox7().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox7().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore = getThisBoxScore(tempGame.getBox8(),tempGame.getBox9(),tempGame.getBox10(),false);
//			boxScorearr[7] = boxScorearr[7] + nowScore;
//			if(tempGame.getBox8().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox8().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			
//			nowScore = getThisBoxScore(tempGame.getBox9(),tempGame.getBox10().substring(0, 2),tempGame.getBox10().substring(1, 2),false);
//			boxScorearr[8] = boxScorearr[8] + nowScore;
//			if(tempGame.getBox9().indexOf('X') >=0 ){
//				totalStrike = totalStrike + nowScore;
//			}else if(tempGame.getBox9().indexOf('/') >=0 ){
//				totalSpare = totalSpare + nowScore;
//			}
//			boxScorearr[9] = boxScorearr[9] + getThisBoxScore(tempGame.getBox10().substring(0, 2),tempGame.getBox10().substring(1, 3),tempGame.getBox10().substring(2, 3),true);
//		}
//		
//		for(int i =0;i<10;i++){
//			
//			if(gamesCount!=0){
//				boxScoreAvg[i] = nf.format(boxScorearr[i]/gamesCount);
//			}else{
//				boxScoreAvg[i] = "0.0";
//			}
//			
//			logger.info("boxScorearr "+i+" : "+boxScorearr[i]);
//			logger.info("boxScoreAvg "+i+" : "+boxScoreAvg[i]);
//		}
//		player.setBox_1_Ave(""+boxScoreAvg[0]);
//		player.setBox_2_Ave(""+boxScoreAvg[1]);
//		player.setBox_3_Ave(""+boxScoreAvg[2]);
//		player.setBox_4_Ave(""+boxScoreAvg[3]);
//		player.setBox_5_Ave(""+boxScoreAvg[4]);
//		player.setBox_6_Ave(""+boxScoreAvg[5]);
//		player.setBox_7_Ave(""+boxScoreAvg[6]);
//		player.setBox_8_Ave(""+boxScoreAvg[7]);
//		player.setBox_9_Ave(""+boxScoreAvg[8]);
//		player.setBox_10_Ave(""+boxScoreAvg[9]);
//		
//		
//		String boxesString = boxesStringSB.toString();
//		
//		double xCount = boxesString.length() - boxesString.replace("X", "").length();
//		player.setStike_Count(""+xCount);
//		String xRateString = nf.format(xCount / gamesCount);
//		if(gamesCount==0){
//			xRateString = "0.0";
//		}
//		player.setStike_Rate(xRateString);
//		String strikeAvg = nf.format(totalStrike / xCount);
//		if(xCount==0){
//			strikeAvg = "0.0";
//		}
//		player.setStrike_Ave(strikeAvg);
//		
//		double spareCount = boxesString.length() - boxesString.replace("/", "").length();
//		player.setSpare_Count(""+spareCount);
//		String spareRateString = nf.format(spareCount / gamesCount);
//		if(gamesCount==0){
//			spareRateString = "0.0";
//		}
//		player.setSpare_Rate(spareRateString);
//		String spareAvg = nf.format(totalSpare / spareCount);
//		if(spareCount==0){
//			spareAvg = "0.0";
//		}
//		player.setSpare_Ave(spareAvg);
//		
//		Map map = gameDAO.getGamesAVGs(playerId, season.getSeasonStartDate(), season.getSeasonEndDate());
//		
//		String avg = (String) map.get("avg");
//		String max = (String) map.get("max");
//		String min = (String) map.get("min");
//		
//		player.setScore_ave(avg);
//		player.setScore_Highest(max);
//		player.setScore_Low(min);
//		player.setMoney((String) map.get("money"));
//		String everyGames = gameDAO.getTop10GamesAVGs(playerId, season.getSeasonStartDate(), season.getSeasonEndDate());
//		logger.info("everyGames : "+everyGames);
//		player.setEveryGames(everyGames);
//
//		player.setGamesCount(gamesCount);
//		logger.info("getPlayerByPlayerId2 : "+player);
		if(refreshPlayer!=null){
			playerDAO.setPlayer(refreshPlayer);
		}
	}
	
	public Player refreshPlayDuringData(Player player, String startDate, String endDate) throws Exception {
		
		if(!teamDAO.alivePlayer(player.getPlayerId(), player.getTeamID())){
			return null;
		}
		
		List<Game> games =  gameDAO.getGamesByPlayerAndDate(player.getPlayerId(), player.getTeamID(), startDate, endDate);
		int gamesCount = games.size();
		int tempCount = player.getGamesCount();
		
		if(gamesCount !=0 && tempCount==gamesCount){
			return null;
		}

		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(1);    //小數後兩位
		StringBuilder boxesStringSB = new StringBuilder();

		double[] boxScorearr = new double[10];
		String[] boxScoreAvg = new String[10];
		
		double totalStrike = 0;
		double totalSpare = 0;
		
		for(Game tempGame : games){
			boxesStringSB.append(tempGame.getBox1()).append(tempGame.getBox2()).append(tempGame.getBox3()).append(tempGame.getBox4()).append(tempGame.getBox5())
						.append(tempGame.getBox6()).append(tempGame.getBox7()).append(tempGame.getBox8()).append(tempGame.getBox9()).append(tempGame.getBox10()) ;
			int nowScore = getThisBoxScore(tempGame.getBox1(),tempGame.getBox2(),tempGame.getBox3(),false);
			boxScorearr[0] = boxScorearr[0] + nowScore;
			if(tempGame.getBox1().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox1().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore = getThisBoxScore(tempGame.getBox2(),tempGame.getBox3(),tempGame.getBox4(),false);
			boxScorearr[1] = boxScorearr[1] + nowScore;
			if(tempGame.getBox2().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox2().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore =  getThisBoxScore(tempGame.getBox3(),tempGame.getBox4(),tempGame.getBox5(),false);
			boxScorearr[2] = boxScorearr[2] + nowScore;
			if(tempGame.getBox3().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox3().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore = getThisBoxScore(tempGame.getBox4(),tempGame.getBox5(),tempGame.getBox6(),false);
			boxScorearr[3] = boxScorearr[3] + nowScore;
			if(tempGame.getBox4().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox4().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore = getThisBoxScore(tempGame.getBox5(),tempGame.getBox6(),tempGame.getBox7(),false);
			boxScorearr[4] = boxScorearr[4] + nowScore;
			if(tempGame.getBox5().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox5().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}

			nowScore = getThisBoxScore(tempGame.getBox6(),tempGame.getBox7(),tempGame.getBox8(),false);
			boxScorearr[5] = boxScorearr[5] + nowScore;
			if(tempGame.getBox6().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox6().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore = getThisBoxScore(tempGame.getBox7(),tempGame.getBox8(),tempGame.getBox9(),false);
			boxScorearr[6] = boxScorearr[6] + nowScore;
			if(tempGame.getBox7().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox7().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore = getThisBoxScore(tempGame.getBox8(),tempGame.getBox9(),tempGame.getBox10(),false);
			boxScorearr[7] = boxScorearr[7] + nowScore;
			if(tempGame.getBox8().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox8().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			
			nowScore = getThisBoxScore(tempGame.getBox9(),tempGame.getBox10().substring(0, 2),tempGame.getBox10().substring(1, 2),false);
			boxScorearr[8] = boxScorearr[8] + nowScore;
			if(tempGame.getBox9().indexOf('X') >=0 ){
				totalStrike = totalStrike + nowScore;
			}else if(tempGame.getBox9().indexOf('/') >=0 ){
				totalSpare = totalSpare + nowScore;
			}
			boxScorearr[9] = boxScorearr[9] + getThisBoxScore(tempGame.getBox10().substring(0, 2),tempGame.getBox10().substring(1, 3),tempGame.getBox10().substring(2, 3),true);
		}
		
		for(int i =0;i<10;i++){
			
			if(gamesCount!=0){
				boxScoreAvg[i] = nf.format(boxScorearr[i]/gamesCount);
			}else{
				boxScoreAvg[i] = "0.0";
			}
			
			logger.info("boxScorearr "+i+" : "+boxScorearr[i]);
			logger.info("boxScoreAvg "+i+" : "+boxScoreAvg[i]);
		}
		player.setBox_1_Ave(""+boxScoreAvg[0]);
		player.setBox_2_Ave(""+boxScoreAvg[1]);
		player.setBox_3_Ave(""+boxScoreAvg[2]);
		player.setBox_4_Ave(""+boxScoreAvg[3]);
		player.setBox_5_Ave(""+boxScoreAvg[4]);
		player.setBox_6_Ave(""+boxScoreAvg[5]);
		player.setBox_7_Ave(""+boxScoreAvg[6]);
		player.setBox_8_Ave(""+boxScoreAvg[7]);
		player.setBox_9_Ave(""+boxScoreAvg[8]);
		player.setBox_10_Ave(""+boxScoreAvg[9]);
		
		
		String boxesString = boxesStringSB.toString();
		
		double xCount = boxesString.length() - boxesString.replace("X", "").length();
		player.setStike_Count(""+xCount);
		String xRateString = nf.format(xCount / gamesCount);
		if(gamesCount==0){
			xRateString = "0.0";
		}
		player.setStike_Rate(xRateString);
		String strikeAvg = nf.format(totalStrike / xCount);
		if(xCount==0){
			strikeAvg = "0.0";
		}
		player.setStrike_Ave(strikeAvg);
		
		double spareCount = boxesString.length() - boxesString.replace("/", "").length();
		player.setSpare_Count(""+spareCount);
		String spareRateString = nf.format(spareCount / gamesCount);
		if(gamesCount==0){
			spareRateString = "0.0";
		}
		player.setSpare_Rate(spareRateString);
		String spareAvg = nf.format(totalSpare / spareCount);
		if(spareCount==0){
			spareAvg = "0.0";
		}
		player.setSpare_Ave(spareAvg);
		
		Map map = gameDAO.getGamesAVGs(player.getPlayerId(), startDate, endDate);
		
		String avg = (String) map.get("avg");
		String max = (String) map.get("max");
		String min = (String) map.get("min");
		
		player.setScore_ave(avg);
		player.setScore_Highest(max);
		player.setScore_Low(min);
		player.setMoney((String) map.get("money"));
		String everyGames = gameDAO.getTop10GamesAVGs(player.getPlayerId(), startDate, endDate);
		logger.info("everyGames : "+everyGames);
		player.setEveryGames(everyGames);

		player.setGamesCount(gamesCount);
		logger.info("getPlayerByPlayerId2 : "+player);
		
		return player;
	}
	
	
	public int getThisBoxScore(String thsiBox,String NextiBox,String NextNextiBox, boolean lastBox) throws Exception {
		int thisBoxScore = 0;
		if(thsiBox.indexOf("X")==0){
			thisBoxScore = thisBoxScore +10;
			if(NextiBox.indexOf("X")==0){
				thisBoxScore = thisBoxScore+10;
				if(NextNextiBox.indexOf("X")==0){
					thisBoxScore = thisBoxScore+10;
				}else{
					int NextNextiBoxScore = Integer.valueOf(NextNextiBox.substring(0,1));
					thisBoxScore = thisBoxScore+NextNextiBoxScore;
				}
			}else if(NextiBox.indexOf("/")>0){
				thisBoxScore = thisBoxScore+10;
			}else{
				if(lastBox){
					int NextiBoxScore_1 = Integer.valueOf(NextiBox.substring(0,1));
					int NextiBoxScore_2 = Integer.valueOf(NextNextiBox.substring(0,1));
					thisBoxScore = thisBoxScore +NextiBoxScore_1+NextiBoxScore_2;
				}else{
					int NextiBoxScore_1 = Integer.valueOf(NextiBox.substring(0,1));
//					logger.info("substring(1) : "+NextiBox.substring(1,2));
					int NextiBoxScore_2 = Integer.valueOf(NextiBox.substring(1,2));
					thisBoxScore = thisBoxScore+NextiBoxScore_1+NextiBoxScore_2;
				}
			}
		}else if(thsiBox.indexOf("/")>0){
			thisBoxScore = thisBoxScore +10;
			if(NextiBox.indexOf("X")==0){
				thisBoxScore = thisBoxScore +10;
			}else{
				if(lastBox){
					if(NextNextiBox.indexOf("X")==0){
						thisBoxScore = thisBoxScore +10;
					}else{
						int NextiBoxScore_1 = Integer.valueOf(NextNextiBox.substring(0,1));
						thisBoxScore = thisBoxScore +NextiBoxScore_1;
					}
				}else{
//					logger.info("substring(0) : "+NextiBox.substring(0,1));
					int NextiBoxScore_1 = Integer.valueOf(NextiBox.substring(0,1));
					thisBoxScore = thisBoxScore +NextiBoxScore_1;
				}
			}
		}else{
			int thisBoxScore_1 = Integer.valueOf(thsiBox.substring(0,1));
			int thisBoxScore_2 = Integer.valueOf(thsiBox.substring(1,2));
			thisBoxScore = thisBoxScore_1+thisBoxScore_2;
		}
		return thisBoxScore;
	}
	
}

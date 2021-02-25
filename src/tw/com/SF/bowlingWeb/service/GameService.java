package tw.com.SF.bowlingWeb.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.SF.bowlingWeb.bean.AchievementsType;
import tw.com.SF.bowlingWeb.bean.Game;
import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.bean.Season;
import tw.com.SF.bowlingWeb.dao.GameDAO;
import tw.com.SF.bowlingWeb.dao.PlayerDAO;
import tw.com.SF.bowlingWeb.dao.SeasonDAO;
@Service
public class GameService extends AbstractService{
	
	@Resource private GameDAO gameDAO;
	@Resource private PlayerDAO playerDAO;
	@Resource private SeasonDAO seasonDAO;
	
	public List<Game> getPlayerGamesByIdAndSeason(String playerId,String teamId,long seasonId) throws Exception {
		Season season = seasonDAO.getSeasonByTeamID(seasonId, teamId);
		logger.info("season : "+season);
		
		return  gameDAO.getGamesByPlayerAndDate(playerId, teamId, season.getSeasonStartDate(), season.getSeasonEndDate());
	}
	
	public List<Game> getGamesByDates(String startDate, String endDate, String teamId) throws Exception {
		return  gameDAO.getTeamGamesByByDates(startDate, endDate, teamId);
	}
	
	public List<Map<String, Object>> getTeamAllGamesForChart(long seasonId, String teamId) throws Exception {
		Season season = seasonDAO.getSeasonByTeamID(seasonId, teamId);
		List<Map<String, Object>> data = new ArrayList<>();
		List<Player> teamPlayers = playerDAO.getTeamPlayer(teamId);
		logger.info("teamPlayers : "+teamPlayers.size());
		List<String> gameNames = gameDAO.getAllGamesNamesByPlayerAndDate(teamId, season.getSeasonStartDate(), season.getSeasonEndDate());
		logger.info("gameNames : "+gameNames.size());
		
		for(Player player:teamPlayers){
			List games = new ArrayList();
			for(String gameName:gameNames){
				games.add(gameDAO.getGamesByNameAndPlayer(gameName, player.getPlayerId(), player.getTeamID()));
			}
			logger.info(player.getPlayerId() + " games.size : "+games.size());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(player.getPlayerId(), games);
			data.add(map);
		}
//		
//		for(Player player:teamPlayers){
//			List games = gameDAO.getGamesByPlayerAndDate(player.getPlayerId(), teamId, season.getSeasonStartDate(), season.getSeasonEndDate());
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put(player.getPlayerId(), games);
//			data.add(map);
//		}
		
		return  data;
	}
	
	public List<Map<String, Object>> getTeamDuringGamesForChart(String startDate, String endDate, String teamId) throws Exception {
		List<Map<String, Object>> data = new ArrayList<>();
		List<Player> teamPlayers = playerDAO.getTeamPlayer(teamId);
		logger.info("teamPlayers : "+teamPlayers.size());
		List<String> gameNames = gameDAO.getAllGamesNamesByPlayerAndDate(teamId, startDate, endDate);
		logger.info("gameNames : "+gameNames.size());
		
		for(Player player:teamPlayers){
			List games = new ArrayList();
			for(String gameName:gameNames){
				games.add(gameDAO.getGamesByNameAndPlayer(gameName, player.getPlayerId(), player.getTeamID()));
			}
			logger.info(player.getPlayerId() + " games.size : "+games.size());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(player.getPlayerId(), games);
			data.add(map);
		}
		
		return  data;
	}
	
	public List<Game> getAllGames() throws Exception {
		return  gameDAO.getGamesByPlayer("","");
	}
	public List<Game> getAllGamesForChart() throws Exception {
		return  gameDAO.getGamesByPlayer("","");
	}
	
	public Boolean deleteGameById(String gameId) throws Exception {
		Game game = gameDAO.getGamesById(gameId);
		gameDAO.delete(game);
		return  true;
	}
	
	public String getGameName(String gameDate) throws Exception {
		return  gameDAO.getGamesNumber(gameDate);
	}
	
	public void setGame(Game game, String boxes) throws Exception {

		String[] boxArray = boxes.split(",");
		game.setBox1(boxArray[0]);
		game.setBox2(boxArray[1]);
		game.setBox3(boxArray[2]);
		game.setBox4(boxArray[3]);
		game.setBox5(boxArray[4]);
		game.setBox6(boxArray[5]);
		game.setBox7(boxArray[6]);
		game.setBox8(boxArray[7]);
		game.setBox9(boxArray[8]);
		game.setBox10(boxArray[9]);

		String playerId = game.getPlayerId();
		String teamId = game.getTeamID();
		Player player = playerDAO.getPlayerByIdAndTeam(playerId, teamId, 0);
		game.setPlayerName(player.getPlayerName());
		logger.info("add games : "+game.toString());

		gameDAO.setGames(game);
	}
	
		
	
}

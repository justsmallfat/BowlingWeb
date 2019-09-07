package tw.com.SF.bowlingWeb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.bean.Season;
import tw.com.SF.bowlingWeb.dao.PlayerDAO;
import tw.com.SF.bowlingWeb.dao.SeasonDAO;
@Service
public class SeasonService  extends AbstractService{
	@Resource private SeasonDAO seasonDAO;
	@Resource private PlayerDAO playerDAO;
	public  List getAllSeasons() throws Exception {
		return  seasonDAO.getAllSeasons();
	}
	
	public List getTeamSeasons(String teamId) throws Exception {
		return  seasonDAO.getTeamSeasons(teamId);
	}
	
	public List getTeamSeasonData(String teamId) throws Exception {
		
		List<Map<String, Object>> datas = new ArrayList<>();
		List<Player> teamPlayers = playerDAO.getTeamPlayer(teamId);
		logger.info("teamPlayers : "+teamPlayers.size());
		List<Season> seasons = seasonDAO.getTeamSeasons(teamId);
		logger.info("seasons : "+seasons.size());

		for(Player player:teamPlayers){
			List players = new ArrayList();
			for(Season season:seasons){
				players.add(playerDAO.getTeamSeasonData(teamId, player.getPlayerId(), season.getSeasonID()));
			}
			logger.info(player.getPlayerId() + " players.size : "+players.size());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(player.getPlayerId(), players);
			datas.add(map);
		}
		logger.info("datas : "+datas.size());
		return  datas;
	}
	
}

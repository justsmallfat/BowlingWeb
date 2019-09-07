package tw.com.SF.bowlingWeb.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.bean.Season;
import tw.com.SF.bowlingWeb.bean.Team;
import tw.com.SF.bowlingWeb.dao.PlayerDAO;
import tw.com.SF.bowlingWeb.dao.SeasonDAO;
import tw.com.SF.bowlingWeb.dao.TeamDAO;
@Service
public class TeamService  extends AbstractService{
	@Resource private TeamDAO teamDAO;
	@Resource private PlayerDAO playerDAO;
	@Resource private SeasonDAO seasonDAO;
	public  Map<String,Object> loginByTeam(Team team) throws Exception {
		return  teamDAO.login(team);
	}
	
	public  Map<String,Object> creatTeam(Team team, Player player) throws Exception {
		Map<String,Object> result =  new HashMap<String, Object>();

		logger.info("team : "+team + " player : "+player);
		result = teamDAO.creat(team);
		if(result.get("result").equals("true")){
			playerDAO.setPlayer(player);
			Season newLife = new Season();
			
			newLife.setSeasonStartDate(dateFormat.format(new Date()));
			newLife.setSeasonEndDate("2119-06-01 00:00:00");
			newLife.setSeasonName("生涯");
			newLife.setTeamID(team.getTeamID());
			seasonDAO.insert(newLife);
		}
		playerDAO.setPlayer(player);
		return result;
	}
	
}

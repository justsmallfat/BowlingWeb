package tw.com.SF.bowlingWeb.Controller.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.SF.bowlingWeb.Controller.AbstractController;
import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.service.GameService;
import tw.com.SF.bowlingWeb.service.PlayerService;
import tw.com.SF.bowlingWeb.util.StringUtils;


@Controller
@RequestMapping("/mobile")
public class PlayerAPI extends AbstractController{
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Resource private PlayerService playerService;
	@Resource private GameService gameService;
	
	@RequestMapping("/getPlayerLife")
	public @ResponseBody Map<String, Object> getPlayerLife(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		Player player =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("playerId")) &&
					StringUtils.hasText(req.getParameter("teamId"))) {
				String Id = req.getParameter("playerId");
				String teamId =  req.getParameter("teamId");
				logger.info("Id : "+Id);
				player =  playerService.getPlayerLifeByPlayerId(Id, teamId);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",player);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",player);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",player);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getPlayerWithSeason")
	public @ResponseBody Map<String, Object> getPlayerWithSeason(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		Player player =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("playerId")) &&
					StringUtils.hasText(req.getParameter("teamId"))  &&
					StringUtils.hasText(req.getParameter("seasonId"))) {
				String Id = req.getParameter("playerId");
				String teamId =  req.getParameter("teamId");
				String seasonId =  req.getParameter("seasonId");
				logger.info("Id : "+Id);
				player =  playerService.getSeasonPlayerByPlayerId(Id, teamId, (long)Integer.parseInt(seasonId));

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",player);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",player);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",player);
			return jsonMap;
		}
	}
	

	@RequestMapping("/getAllPlayersWithSeason")
	public @ResponseBody Map<String, Object> getAllPlayersWithSeason(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List players =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("teamId"))  &&
					StringUtils.hasText(req.getParameter("seasonId"))) {
				String teamId =  req.getParameter("teamId");
				String seasonId =  req.getParameter("seasonId");
				players =  playerService.getTeamSeasonPlayers(teamId, (long)Integer.parseInt(seasonId));

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("players",players);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",players);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",players);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getAllPlayer")
	public @ResponseBody Map<String, Object> getAllPlay(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("APP Login GetPlayerController start()");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List players =  null;
		try	{
			String Id = req.getParameter("playerId");
			players =  playerService.getAllPlayer();

			jsonMap.put("success", "true");
			jsonMap.put("errmsg", message);
			jsonMap.put("players",players);
			
			return jsonMap;
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",players);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getTeamPlayer")
	public @ResponseBody Map<String, Object> getTeamPlayer(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("APP Login GetPlayerController start()");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List players =  null;
		try	{
			if(StringUtils.hasText(req.getParameter("teamId"))) {
				String teamId = req.getParameter("teamId");
				players =  playerService.getTeamPlayer(teamId);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("players",players);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",players);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",players);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getTeamPlayerDuringData")
	public @ResponseBody Map<String, Object> getTeamPlayerDuringData(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("APP Login GetPlayerController start()");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List players =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("startDate")) && 
					StringUtils.hasText(req.getParameter("endDate")) &&
					StringUtils.hasText(req.getParameter("teamId")) ) {
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				String teamId = req.getParameter("teamId");
				logger.info("teamId : "+teamId);
				players =  playerService.getTeamPlayerDuring(startDate, endDate, teamId);
				logger.info("player : "+players);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("players",players);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",players);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",players);
			return jsonMap;
		}
	}
	
//	@RequestMapping("/refreshAllPlayer")
//	public @ResponseBody Map<String, Object> refreshAllPlayerData(HttpServletRequest req, HttpServletResponse resp) throws Exception{
//		logger.info("APP Login GetPlayerController start()");
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		String message = null;
//		List<Player> players =  null;
//		try	{
//			players =  playerService.getAllPlayer();
//			for(Player player:players){
//				String playerId = player.getPlayerId();
//				String teamId = player.getTeamID();
//				playerService.refreshPlayLifeData(playerId,teamId);
//			}
//
//			jsonMap.put("success", "true");
//			jsonMap.put("errmsg", message);
//			jsonMap.put("players",players);
//			
//			return jsonMap;
//		}
//		catch(Exception e){
//			logger.error("AuthenticateController Exception", e);
//			e.printStackTrace();
//			message = "未知的錯誤 : \n"+getExceptionMessage(e);
//			jsonMap.put("success", "false");
//			jsonMap.put("errmsg", message);
//			jsonMap.put("player",players);
//			return jsonMap;
//		}
//	}
	
}

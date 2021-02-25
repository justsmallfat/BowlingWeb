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
import tw.com.SF.bowlingWeb.service.GameService;
import tw.com.SF.bowlingWeb.service.PlayerService;
import tw.com.SF.bowlingWeb.util.StringUtils;


@Controller
@RequestMapping("/mobile")
public class GetGamesAPI extends AbstractController{
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Resource private GameService gameService;
	
	@RequestMapping("/getGamesByPlayer")
	public @ResponseBody Map<String, Object> getgamesByPlayer(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List games =  null;
		try	{
			if(StringUtils.hasText(req.getParameter("playerId")) &&
					StringUtils.hasText(req.getParameter("teamId")) ) {
				String Id = req.getParameter("playerId");
				String teamId = req.getParameter("teamId");
				
				logger.info("Id : "+Id);
				games =  gameService.getPlayerGamesByIdAndSeason(Id, teamId, 0);
				logger.info("games : "+games);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("playerGames",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",games);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",games);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getPlayerGamesBySeason")
	public @ResponseBody Map<String, Object> getPlayerGamesBySeason(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List games =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("playerId")) &&
					StringUtils.hasText(req.getParameter("teamId"))  &&
					StringUtils.hasText(req.getParameter("seasonId")) ) {
				String Id = req.getParameter("playerId");
				String teamId = req.getParameter("teamId");
				String seasonId = req.getParameter("seasonId");
				games =  gameService.getPlayerGamesByIdAndSeason(Id, teamId,Integer.parseInt(seasonId));
				logger.info("games : "+games);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("playerGames",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",games);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",games);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getgamesByDates")
	public @ResponseBody Map<String, Object> getgamesByDate(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List games =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("startDate")) && 
					StringUtils.hasText(req.getParameter("endDate")) &&
					StringUtils.hasText(req.getParameter("teamId")) ) {
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				String teamId = req.getParameter("teamId");
				games =  gameService.getGamesByDates(startDate, endDate, teamId);
				logger.info("games : "+games);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("playerGames",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",games);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",games);
			return jsonMap;
		}
	}
//	
//	@RequestMapping("/getAllgames")
//	public @ResponseBody Map<String, Object> getAllgames(HttpServletRequest req, HttpServletResponse resp) throws Exception{
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		String message = null;
//		List games =  null;
//		try	{
//			games =  gameService.getAllGames();
//			logger.info("games : "+games);
//
//			jsonMap.put("success", "true");
//			jsonMap.put("errmsg", message);
//			jsonMap.put("playerGames",games);
//			return jsonMap;
//		}
//		catch(Exception e){
//			logger.error("AuthenticateController Exception", e);
//			e.printStackTrace();
//			message = "未知的錯誤 : \n"+getExceptionMessage(e);
//			jsonMap.put("success", "false");
//			jsonMap.put("errmsg", message);
//			jsonMap.put("player",games);
//			return jsonMap;
//		}
//	}
	
	@RequestMapping("/getTeamAllGamesForChart")
	public @ResponseBody Map<String, Object> getTeamAllGamesForChart(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List games =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("seasonId")) && 
					StringUtils.hasText(req.getParameter("teamId")) ) {
				String seasonId = req.getParameter("seasonId");
				String teamId = req.getParameter("teamId");
				games =  gameService.getTeamAllGamesForChart(Long.parseLong(seasonId, 10), teamId);
				logger.info("games : "+games);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("playerGames",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",games);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",games);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getTeamDuringGamesForChart")
	public @ResponseBody Map<String, Object> getTeamDuringGamesForChart(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List games =  null;
		try	{
			if(		StringUtils.hasText(req.getParameter("startDate")) && 
					StringUtils.hasText(req.getParameter("endDate")) && 
					StringUtils.hasText(req.getParameter("teamId")) ) {
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				String teamId = req.getParameter("teamId");
				games =  gameService.getTeamDuringGamesForChart(startDate, endDate, teamId);
				logger.info("games : "+games);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("playerGames",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",games);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",games);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getGameName")
	public @ResponseBody Map<String, Object> getGameName(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		String gameName =  "";
		try	{
			if(StringUtils.hasText(req.getParameter("gameDate"))) {
				String gameDate = req.getParameter("gameDate");
				gameName =  gameService.getGameName(gameDate);
				logger.info("gameName : "+gameName);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("gameName",gameName);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("gameName",gameName);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("gameName",gameName);
			return jsonMap;
		}
	}
	
}

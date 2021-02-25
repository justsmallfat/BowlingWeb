package tw.com.SF.bowlingWeb.Controller.api;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import tw.com.SF.bowlingWeb.bean.Game;
import tw.com.SF.bowlingWeb.service.GameService;
import tw.com.SF.bowlingWeb.util.StringUtils;


@Controller
@RequestMapping("/mobile")
public class SetGamesAPI extends AbstractController{
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Resource private GameService gameService;
//	private Game game;

    
	@RequestMapping("/addgame")
	public @ResponseBody Map<String, Object> list(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("APP Login SetPlayerController start()");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String message = null;
		List games =  null;
		Game game = new Game();
		try	{
			if(StringUtils.hasText(req.getParameter("playerId")) && 
					StringUtils.hasText(req.getParameter("score")) && 
					StringUtils.hasText(req.getParameter("rank")) && 
					StringUtils.hasText(req.getParameter("creatDate")) &&
					StringUtils.hasText(req.getParameter("boxes"))  &&
					StringUtils.hasText(req.getParameter("gameName"))&&
					StringUtils.hasText(req.getParameter("money"))&&
					StringUtils.hasText(req.getParameter("teamId"))) {
				
				String gameName = req.getParameter("gameName");
				String playerId = req.getParameter("playerId");
				String score = req.getParameter("score");
				String rank = req.getParameter("rank");
				String creatDate = req.getParameter("creatDate");
				String boxes = req.getParameter("boxes");
				String updateTime = sdf.format(new Date());
				String money = req.getParameter("money");
				String teamId = req.getParameter("teamId");
				
				
				
				
				game.setPlayerId(playerId);				
				game.setCreatDate(creatDate);
				game.setScore(Integer.valueOf(score));
				game.setRank(rank);
				game.setUpdateDate(updateTime);
				game.setMoney(money);
				game.setGameName(gameName);
				game.setTeamID(teamId);
				
				gameService.setGame(game, boxes);
				
				message = "資料新增成功";
				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("games",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("game",game);
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
	
	
	@RequestMapping("/deleteGame")
	public @ResponseBody Map<String, Object> Map(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = "";
		String message = null;
		List games =  null;
		Game game = new Game();
		try	{
			if(StringUtils.hasText(req.getParameter("gameId"))) {
				
				String gameId = req.getParameter("gameId");
				Boolean res = gameService.deleteGameById(gameId);
				
				if(res){
					result = "true";
					message = "資料刪除成功";
				}else{
					result = "false";
					message = "資料刪除失敗";
				}
				
				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("games",games);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("game",game);
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
	
}

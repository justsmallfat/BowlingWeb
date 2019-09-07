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
import tw.com.SF.bowlingWeb.service.HistoryAchievementsService;
import tw.com.SF.bowlingWeb.service.PlayerService;
import tw.com.SF.bowlingWeb.util.StringUtils;


@Controller
@RequestMapping("/mobile")
public class HistoryAchievementsAPI extends AbstractController{
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	@Resource private HistoryAchievementsService historyAchievementsService;
	@Resource private PlayerService playerService;
	
	
	@RequestMapping("/getAllHistoryAchievements")
	public @ResponseBody Map<String, Object> getAllHistoryAchievements(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("APP Login GetPlayerController start()");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String message = null;
		List achievements =  null;
		try	{

			if(StringUtils.hasText(req.getParameter("teamId"))) {
				String teamId = req.getParameter("teamId");
				
				
				achievements =  historyAchievementsService.getAllHistoryAchievements(teamId);
				logger.info("achievements : "+achievements);

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("achievements",achievements);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("achievements",achievements);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",achievements);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getSeasonAchievements")
	public @ResponseBody Map<String, Object> getSeasonAchievements(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("APP Login GetPlayerController start()");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String message = null;
		List achievements =  null;
		try	{

			if(		StringUtils.hasText(req.getParameter("teamId")) ||
					StringUtils.hasText(req.getParameter("seasonId"))) {
				String teamId = req.getParameter("teamId");
				String seasonId = req.getParameter("seasonId");

				playerService.refreshTeamAllPlayerSeasonData(teamId, Integer.parseInt(seasonId));
				achievements =  historyAchievementsService.getSeasonAchievements(teamId, Integer.parseInt(seasonId));

				jsonMap.put("success", "true");
				jsonMap.put("errmsg", message);
				jsonMap.put("achievements",achievements);
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("achievements",achievements);
				return jsonMap;
			}
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("player",achievements);
			return jsonMap;
		}
	}
//	
//	@RequestMapping("/refreshAllHistoryAchievements")
//	public @ResponseBody Map<String, Object> refreshAllHistoryAchievements(HttpServletRequest req, HttpServletResponse resp) throws Exception{
//		logger.info("APP Login GetPlayerController start()");
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		String message = null;
//		List achievements =  null;
//		try	{
//			if(StringUtils.hasText(req.getParameter("teamId"))) {
//				String teamId = req.getParameter("teamId");
//				Boolean res =  historyAchievementsService.refreshAllPlayerAchievements(teamId);
//				logger.info("res : "+res);
//
//				jsonMap.put("success", "true");
//				jsonMap.put("errmsg", message);
//				jsonMap.put("achievements",achievements);
//				
//				return jsonMap;
//			}else {
//				message = "資料格式錯誤";
//				jsonMap.put("success", "false");
//				jsonMap.put("errmsg", message);
//				jsonMap.put("achievements",achievements);
//				return jsonMap;
//			}
//			
//		}
//		catch(Exception e){
//			logger.error("AuthenticateController Exception", e);
//			e.printStackTrace();
//			message = "未知的錯誤 : \n"+getExceptionMessage(e);
//			jsonMap.put("success", "false");
//			jsonMap.put("errmsg", message);
//			jsonMap.put("player",achievements);
//			return jsonMap;
//		}
//	}
//	
//	
}

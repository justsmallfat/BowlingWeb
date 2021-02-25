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
import tw.com.SF.bowlingWeb.bean.Season;
import tw.com.SF.bowlingWeb.bean.Team;
import tw.com.SF.bowlingWeb.service.SeasonService;
import tw.com.SF.bowlingWeb.util.StringUtils;


@Controller
@RequestMapping("/mobile")
public class SeasonAPI extends AbstractController{
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Resource private SeasonService seasonService;
	
	@RequestMapping("/getAllSeasons")
	public @ResponseBody Map<String, Object> getAllSeasons(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List<Season> seasons =  null;
		try	{
			
			if(StringUtils.hasText(req.getParameter("teamId"))) {
				String teamId = req.getParameter("teamId");
				logger.info("teamId : "+teamId);

				seasons = seasonService.getTeamSeasons(teamId);
				jsonMap.put("success", "true");
				jsonMap.put("errmsg", "");
				jsonMap.put("seasons",seasons);
					
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("player",seasons);
				return jsonMap;
			}
			
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("seasons",seasons);
			return jsonMap;
		}
	}
	
	@RequestMapping("/getTeamAllSeasonsForChart")
	public @ResponseBody Map<String, Object> getTeamAllSeasonsForChart(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		List datas =  null;
		try	{
			
			if(	StringUtils.hasText(req.getParameter("teamId"))) {
				String teamId = req.getParameter("teamId");
				logger.info("teamId : "+teamId);
				datas = seasonService.getTeamSeasonData(teamId);
				jsonMap.put("success", "true");
				jsonMap.put("errmsg", "");
				jsonMap.put("datas",datas);
					
				
				return jsonMap;
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("datas",datas);
				return jsonMap;
			}
			
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("seasons",datas);
			return jsonMap;
		}
	}
	
}

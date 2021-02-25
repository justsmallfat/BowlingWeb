package tw.com.SF.bowlingWeb.Controller.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.SF.bowlingWeb.Controller.AbstractController;
import tw.com.SF.bowlingWeb.bean.Player;
import tw.com.SF.bowlingWeb.bean.Team;
import tw.com.SF.bowlingWeb.service.TeamService;
import tw.com.SF.bowlingWeb.util.StringUtils;


@Controller
@RequestMapping("/mobile")
public class TeamAPI extends AbstractController{
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Resource private TeamService teamService;
	
	@RequestMapping("/login")
	public @ResponseBody Map<String, Object> login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		Team account =  null;
		try	{
			
			if(StringUtils.hasText(req.getParameter("teamId")) && 
					StringUtils.hasText(req.getParameter("playerId")) && 
					StringUtils.hasText(req.getParameter("password"))) {

				String teamId = req.getParameter("teamId");
				String playerId = req.getParameter("playerId");
				String password = req.getParameter("password");
				logger.info("playerId : "+playerId + "teamId : "+teamId + " password : "+password);
				
				Team loginTeam = new Team();
				loginTeam.setTeamID(teamId.trim());
				loginTeam.setPlayerId(playerId.trim());
				loginTeam.setPasword(password.trim());
				
				Map<String,Object> loginResult =  teamService.loginByTeam(loginTeam);
				jsonMap.put("success", loginResult.get("result"));
				jsonMap.put("errmsg", loginResult.get("message"));
				jsonMap.put("account",account);
				
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("account",account);
			}
			
			return jsonMap;
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("account",account);
			return jsonMap;
		}
	}
	
	@RequestMapping("/register")
	public @ResponseBody Map<String, Object> register(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String message = null;
		Team account =  null;
		try	{
			
			if(		StringUtils.hasText(req.getParameter("teamId")) && 
					StringUtils.hasText(req.getParameter("teamName")) && 
					StringUtils.hasText(req.getParameter("playerName")) && 
					StringUtils.hasText(req.getParameter("playerId")) && 
					StringUtils.hasText(req.getParameter("password"))) {

				String teamId = req.getParameter("teamId");
				String teamName = req.getParameter("teamName");
				String playerName = req.getParameter("playerName");
				String playerId = req.getParameter("playerId");
				String password = req.getParameter("password");
				logger.info("playerId : "+playerId + " teamId : "+teamId + " password : "+password);
				
				Team registerTeam = new Team();
				registerTeam.setTeamID(teamId.trim());
				registerTeam.setTeamName(teamName.trim());
				registerTeam.setPlayerId(playerId.trim());
				registerTeam.setPasword(password.trim());
				
				Player registerPlayer = new Player();
				registerPlayer.setTeamID(teamId);
				registerPlayer.setPlayerId(playerId);
				registerPlayer.setPlayerName(playerName);
				
				Map<String,Object> loginResult =  teamService.creatTeam(registerTeam, registerPlayer);
				jsonMap.put("success", loginResult.get("result"));
				jsonMap.put("errmsg", loginResult.get("message"));
				jsonMap.put("account",account);
				
			}else {
				message = "資料格式錯誤";
				jsonMap.put("success", "false");
				jsonMap.put("errmsg", message);
				jsonMap.put("account",account);
			}
			
			return jsonMap;
		}
		catch(Exception e){
			logger.error("AuthenticateController Exception", e);
			e.printStackTrace();
			message = "未知的錯誤 : \n"+getExceptionMessage(e);
			jsonMap.put("success", "false");
			jsonMap.put("errmsg", message);
			jsonMap.put("account",account);
			return jsonMap;
		}
	}
	
}

package tw.com.SF.bowlingWeb.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.com.SF.bowlingWeb.bean.Team;

@Repository
public class TeamDAO extends AbstractDAO<Team> {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Team getTeamByTeam(Team team) throws Exception {	
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", team.getTeamID()));
		criteria.add(Restrictions.eq("playerId", team.getPlayerId()));
		
		Team returnAccount = null;
		
		if(criteria.list()!=null && criteria.list().size()>0){
			criteria.add(Restrictions.eq("pasword", team.getPasword()));
			returnAccount = (Team) criteria.list().get(0);
		}else{
			
		}
		
		return returnAccount;		
	}
	
	public boolean alivePlayer(String playerId, String teamId) throws Exception {	
		boolean res = false;
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", teamId));
		criteria.add(Restrictions.eq("playerId", playerId));
		
		Team returnAccount = null;
		
		if(criteria.list()!=null && criteria.list().size()>0){
			returnAccount = (Team) criteria.list().get(0);
			Date now = new Date();
			Date lastLogin = sdf.parse(returnAccount.getLastLoginTime());
			
			if(now.getMonth() == lastLogin.getMonth()){
				res = true;
			}
		}else{
			
		}
		
		return res;		
	}
	
	public Map<String,Object> login(Team team) throws Exception {	
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", team.getTeamID()));
		criteria.add(Restrictions.eq("playerId", team.getPlayerId()));
		
		Map<String,Object> result =  new HashMap<String, Object>();
		
		if(criteria.list()!=null && criteria.list().size()>0){
			criteria.add(Restrictions.eq("pasword", team.getPasword()));
			if(criteria.list()!=null && criteria.list().size()>0){
				result.put("result", "true");	
				result.put("message", "登入成功!");	
				
				Team returnTeam = null;
				returnTeam = (Team) criteria.list().get(0);
				String loginTime = sdf.format(new Date());
				returnTeam.setLastLoginTime(loginTime);
				this.insertOrUpdate(returnTeam);
				result.put("account", returnTeam);			
			}else{
				result.put("result", "false");	
				result.put("message", "密碼錯誤!");			
			}
		}else{
			result.put("result", "false");	
			result.put("message", "查無資料!");			
		}
		
		return result;		
	}
	
	public Map<String,Object> creat(Team team) throws Exception {	
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("teamID", team.getTeamID()));
		Map<String,Object> result =  new HashMap<String, Object>();
		
		if(criteria.list()!=null && criteria.list().size()>0){
			criteria.add(Restrictions.eq("playerId", team.getPlayerId()));
			if(criteria.list()!=null && criteria.list().size()>0){
				result.put("result", "false");	
				result.put("message", "球員已存在!");	
				
				Team returnTeam = null;
				returnTeam = (Team) criteria.list().get(0);
				String loginTime = sdf.format(new Date());
				returnTeam.setLastLoginTime(loginTime);
				this.insertOrUpdate(returnTeam);
				result.put("account", returnTeam);			
			}else{
				result.put("result", "false");	
				result.put("message", "隊伍已存在!");			
			}
		}else{
			String now = sdf.format(new Date());
			team.setLastLoginTime(now);
			team.setUpdate_Date(now);
			team.setCreat_Date(now);
			insertOrUpdate(team);
			result.put("result", "true");	
			result.put("message", "隊伍建立成功!");			
		}
		
		return result;		
	}
	
}

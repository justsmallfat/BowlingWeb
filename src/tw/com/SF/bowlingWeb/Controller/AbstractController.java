package tw.com.SF.bowlingWeb.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import tw.com.SF.bowlingWeb.bean.Player;

import org.apache.log4j.Logger;


public abstract class AbstractController {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	
	public void returnJsonMap(HttpServletRequest req, HttpServletResponse resp, Map<Object, Object> map) {
		resp.setHeader("ContentType", "text/html");
		resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8"); 
		PrintWriter out = null;
		JSONObject o = null;
		o = JSONObject.fromObject(map);
		
		try {
			out = resp.getWriter();
			out.print(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Object, Object> createResponseMsg(boolean success, Object successObject, Object errorObject) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", success);
		if (success)	map.put("message", successObject);
		else	map.put("message", errorObject);
		
		return map;
	}
	
	/**
	 * ??��?�Exception翻譯訊息<br/>
	 * ?��增失败�?��?��?��?��??  "ORA-00001/DB2 SQL error: SQLCODE: -803"<br/>
	 * ?��增失败�?��?�段过长  "ORA-12899"<br/>
	 * 系�?��?��?�路繁�?��?�请稍�?��?��?? "?��他�?�?"<br/>
	 * */
	public String getExceptionMessage(Exception e){
		String msg = e.getMessage();
		int index = -1;
		if((index=msg.indexOf("ORA-00001"))!=-1 || (index=msg.indexOf("DB2 SQL error: SQLCODE: -803"))!=-1) {
			msg = "?��增失??��?��?��?��?��??(-803)";
		} else if((index=msg.indexOf("ORA-12899"))!=-1|| (index=msg.indexOf("DB2 SQL error: SQLCODE: -302"))!=-1) {	//欄�?��?�長
			msg = "?��?��失�?��?�自段�?�長(-302)";
		} else {
//			msg = Constant.NETWORK_BUSY;
			
		}
		
		return msg;
	}
}

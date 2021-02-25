package tw.com.SF.bowlingWeb.service;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class AbstractService {
	protected Logger logger = Logger.getLogger(this.getClass());
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** 紀錄系統操作紀錄 */
	
	
}

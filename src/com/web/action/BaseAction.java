package com.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	public static final String PARAMS = "params";
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String BLANK = "";
	
	
	// 获取Request
	public HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}
	
	// 获取Parameter
	public String getParameter(String name)
	{
		//FIXME 避免无法识别客户端请求环境
		ActionContext.getContext().setLocale(Locale.CHINA);
		setSessions("WW_TRANS_I18N_LOCALE", Locale.CHINA);
		return getRequest().getParameter(name);
	}
			
	public void setSessions(String name, Object value)
	{
		getRequest().getSession().setAttribute(name, value);
	}
		
	public static Map<String,String> getJsonMap(){
	    Map<String, String> jsonMap = new HashMap<String, String>();
	 	jsonMap.put(STATUS, SUCCESS);
	 	jsonMap.put(MESSAGE, BLANK);
	 	return jsonMap;
	}
	
	public static Map<String,String> getErrorMap(Map<String,String> jsonMap,String msg){
    	if(null == jsonMap)
    		jsonMap = new HashMap<String, String>();
 	    jsonMap.put(STATUS, ERROR);
 	    jsonMap.put(MESSAGE, msg);
 	    return jsonMap;
    }
	
	// 根据Map输出JSON，返回null
	public String ajaxJson(Map<String, String> jsonMap)
	{
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/html");
	}
	
	// AJAX输出，返回null
	public static String ajax(String content, String type)
	{
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

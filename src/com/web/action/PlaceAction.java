package com.web.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.PlaceBiz;
import com.web.biz.UserBiz;
import com.web.entity.Department;
import com.web.entity.Place;
import com.web.entity.PlaceOpenTime;
import com.web.entity.Student;
import com.web.entity.UsePlace;

public class PlaceAction extends BaseAction implements RequestAware, SessionAware{
	private UserBiz userBiz;
	private PlaceBiz placeBiz;
	private String type;
	Map<String, Object> request;
	Map<String, Object> session;
	private int clickTypeID;
	private String clickName;
	private UsePlace usePlace;
	private String searchDate;
	private String searchDepartmentName;
	
	public UserBiz getUserBiz() {return userBiz;}
	public void setUserBiz(UserBiz userBiz) {this.userBiz = userBiz;}
	public PlaceBiz getPlaceBiz() {return placeBiz;}
	public void setPlaceBiz(PlaceBiz placeBiz) {this.placeBiz = placeBiz;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public int getClickTypeID() {return clickTypeID;}
	public void setClickTypeID(int clickTypeID) {this.clickTypeID = clickTypeID;}
	public String getClickName() {return clickName;}
	public void setClickName(String clickName) throws UnsupportedEncodingException {
		String s = new String(clickName.getBytes("ISO8859-1"),"UTF-8");
		this.clickName = s;
	}
	public UsePlace getUsePlace() {return usePlace;}
	public void setUsePlace(UsePlace usePlace) {this.usePlace = usePlace;}
	public String getSearchDate() {return searchDate;}
	public void setSearchDate(String searchDate) {this.searchDate = searchDate;}
	public String getSearchDepartmentName() {return searchDepartmentName;}
	public void setSearchDepartmentName(String searchDepartmentName) throws UnsupportedEncodingException {
		String s = new String(searchDepartmentName.getBytes("ISO8859-1"),"UTF-8");
		this.searchDepartmentName = s;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public String ChangeType(){
		if(session.get("student") == null){return "login";}
		if(type.equals("APP")){
			
		}else if(type.equals("WEB")){
			try{
				System.out.println(clickTypeID);
				session.put("clickTypeID", clickTypeID);
				session.put("clickTypeName", placeBiz.GetPlaceTypeName(clickTypeID));
				return "success";
			}catch(Exception e){
				System.out.println(e);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String Sreach(){
		SimpleDateFormat sdf01 = new SimpleDateFormat(DATE_FORMAT_NOW);
		if(type.equals("APP")){
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				int placeTypeID = Integer.parseInt(json.getString("PlaceTypeID"));
				String dateS = json.getString("SearchDate");
				Date date = sdf01.parse(dateS);
				List<UsePlace> usePlaceList = new ArrayList<>();
				usePlaceList = placeBiz.GetRecordPlaceWithoutD(date, placeTypeID);
				if(usePlaceList.size() == 0){
					jsonMap = getErrorMap(jsonMap, "查無列表");
					return ajaxJson(jsonMap);
				}
				else{
					result = com.alibaba.fastjson.JSON.toJSONString(usePlaceList);
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "查場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}else if(type.equals("WEB")){
			if(session.get("student") == null){return "login";}
			System.out.println(searchDepartmentName + "  " + searchDate );
			try {
				if(searchDate != null){
					Date date = sdf01.parse(searchDate);
					String day = changeDay(date.getDay());
					session.put("searchDate", searchDate);
					session.put("searchDay", day);
					if(day==null){return "success";}
					if(!searchDepartmentName.equals("0")){
						session.put("searchList", placeBiz.GetRecordPlace(date, Integer.parseInt(session.get("clickTypeID").toString()), searchDepartmentName));
						session.put("openTime", placeBiz.GetOpenTime(Integer.parseInt(session.get("clickTypeID").toString()), day));
					}
					else{
						session.put("searchList", placeBiz.GetRecordPlaceWithoutD(date, Integer.parseInt(session.get("clickTypeID").toString())));
						session.put("openTime", placeBiz.GetOpenTime(Integer.parseInt(session.get("clickTypeID").toString()), day));
					}
				}else{
					return "success";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "success";
		}
		return null;
	}
	
	public String Record(){
		SimpleDateFormat sdf01 = new SimpleDateFormat(DATE_FORMAT_NOW);
		if(type.equals("APP")){
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				String s = json.getString("RecordPlace");
				UsePlace usePlace = com.alibaba.fastjson.JSONObject.parseObject(s, UsePlace.class);
				placeBiz.RecordPlace(usePlace);
				List<UsePlace> usePlaceList = new ArrayList<>();
				usePlaceList = placeBiz.GetRecordPlaceWithoutD(usePlace.getPlaceDate(), usePlace.getPlaceTypeID());
				if(usePlaceList.size() == 0){
					jsonMap = getErrorMap(jsonMap, "查無列表");
					return ajaxJson(jsonMap);
				}
				else{
					result = com.alibaba.fastjson.JSON.toJSONString(usePlaceList);
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "查場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}else if(type.equals("WEB")){
			if(session.get("student") == null){return "login";}
			try {
				if(usePlace.getDepartmentName() == null 
						|| usePlace.getStudentName() == null
						|| !hasPlaceTime(usePlace)){
					return "success";
				}
				Date date = new Date();
				UsePlace usePlace = this.usePlace;
				usePlace.setStudentAccount(((Student)session.get("student")).getStudentAccount());
				usePlace.setPlaceTypeID(Integer.parseInt(session.get("clickTypeID").toString()));
				usePlace.setStatus(0);
				usePlace.setPlaceDate(sdf01.parse(session.get("searchDate").toString()));
				usePlace.setRecordDate(date);
				placeBiz.RecordPlace(usePlace);
				session.put("searchList", placeBiz.GetRecordPlaceWithoutD(sdf01.parse(session.get("searchDate").toString()), Integer.parseInt(session.get("clickTypeID").toString())));
				setPlaceTime();
				return "success";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String GetDepartment(){
		Map<String, String> jsonMap = null;
		String result = "";
		try {
			jsonMap = getJsonMap();
			String params = getParameter(PARAMS);
			JSONObject json = JSONObject.fromObject(params);
			List<Department> departmentList = placeBiz.GetDepartment();
			result = com.alibaba.fastjson.JSON.toJSONString(departmentList);
			if(departmentList == null){
				jsonMap = getErrorMap(jsonMap, "查無列表");
			}
			else{
				jsonMap.put("value", result);
				jsonMap.put("status", "success");
			}
		} catch (Exception e) {
			jsonMap = getErrorMap(jsonMap, "登入失敗:" + e.getMessage());
		}
		return ajaxJson(jsonMap);
	}
	
	private String changeDay(int day){
		if(day == 1){return "星期一";}
		else if(day == 2){return "星期二";}
		else if(day == 3){return "星期三";}
		else if(day == 4){return "星期四";}
		else if(day == 5){return "星期五";}
		return null;
	}
	
	private void setPlaceTime(){
		this.usePlace = null;
	}
	
	private boolean hasPlaceTime(UsePlace usePlace){
		if(usePlace.getA0700() != null){return true;}
		else if(usePlace.getA0730()!= null){return true;}
		else if(usePlace.getA0800()!= null){return true;}
		else if(usePlace.getA0830()!= null){return true;}
		else if(usePlace.getA0900()!= null){return true;}
		else if(usePlace.getA0930()!= null){return true;}
		else if(usePlace.getA1000() != null){return true;}
		else if(usePlace.getA1030() != null){return true;}
		else if(usePlace.getA1100() != null){return true;}
		else if(usePlace.getA1130() != null){return true;}
		else if(usePlace.getP1200() != null){return true;}
		else if(usePlace.getP1230() != null){return true;}
		else if(usePlace.getP1300() != null){return true;}
		else if(usePlace.getP1330() != null){return true;}
		else if(usePlace.getP1400() != null){return true;}
		else if(usePlace.getP1430() != null){return true;}
		else if(usePlace.getP1500() != null){return true;}
		else if(usePlace.getP1530() != null){return true;}
		else if(usePlace.getP1600() != null){return true;}
		else if(usePlace.getP1630() != null){return true;}
		else if(usePlace.getP1700() != null){return true;}
		else if(usePlace.getP1730() != null){return true;}
		else if(usePlace.getP1800() != null){return true;}
		else if(usePlace.getP1830() != null){return true;}
		else if(usePlace.getP1900() != null){return true;}
		else if(usePlace.getP1930() != null){return true;}
		else if(usePlace.getP2000() != null){return true;}
		else if(usePlace.getP2030() != null){return true;}
		else if(usePlace.getP2100() != null){return true;}
		else if(usePlace.getP2130() != null){return true;}
		return false;
	}
	
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
}

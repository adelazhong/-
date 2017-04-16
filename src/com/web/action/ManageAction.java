package com.web.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSONArray;
import com.web.biz.PlaceBiz;
import com.web.biz.UserBiz;
import com.web.entity.Place;
import com.web.entity.PlaceOpenTime;
import com.web.entity.PlaceType;
import com.web.entity.Student;
import com.web.entity.UsePlace;

public class ManageAction extends BaseAction implements RequestAware, SessionAware{
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;
	private PlaceBiz placeBiz;
	private List<PlaceOpenTime> placeOpenList;
	private String type;
	private UsePlace usePlace;
	private Place place;
	Map<String, Object> session;
	Map<String, Object> request;
	private int clickTypeID;
	private String clickName;
	private String day;
	private String time;
	
	public UserBiz getUserBiz() {return userBiz;}
	public void setUserBiz(UserBiz userBiz) {this.userBiz = userBiz;}
	public PlaceBiz getPlaceBiz() {return placeBiz;}
	public void setPlaceBiz(PlaceBiz placeBiz) {this.placeBiz = placeBiz;}
	public List<PlaceOpenTime> getPlaceOpenList() {return placeOpenList;}
	public void setPlaceOpenList(List<PlaceOpenTime> placeOpenList) {this.placeOpenList = placeOpenList;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public UsePlace getUsePlace() {return usePlace;}
	public void setUsePlace(UsePlace usePlace) {this.usePlace = usePlace;}
	public Place getPlace() {return place;}
	public void setPlace(Place place) {this.place = place;}
	public int getClickTypeID() {return clickTypeID;}
	public void setClickTypeID(int clickTypeID) {this.clickTypeID = clickTypeID;}
	public String getClickName() {return clickName;}
	public void setClickName(String clickName) throws UnsupportedEncodingException {
		String s = new String(clickName.getBytes("ISO8859-1"),"UTF-8");
		this.clickName = s;
	}
	public String getDay() {return day;}
	public void setDay(String day) throws UnsupportedEncodingException {
		String s = new String(day.getBytes("ISO8859-1"),"UTF-8");
		this.day = s;
	}
	public String getTime() {return time;}
	public void setTime(String time) throws UnsupportedEncodingException {
		String s = new String(time.getBytes("ISO8859-1"),"UTF-8");
		this.time = s;
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
	
	/**
	 * 取得所有場地
	 * **/
	public String ManageGetPlace(){
		if(type.equals("APP")){
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				int placeTypeID = Integer.parseInt(json.getString("PlaceTypeID"));
				List<Place> placeSetList = placeBiz.ManageGetPlace(placeTypeID);
				result = com.alibaba.fastjson.JSON.toJSONString(placeSetList);
				if(placeSetList == null){
					jsonMap = getErrorMap(jsonMap, "查無列表");
				}
				else{
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "取得所有場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}
		else if(type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			try{
				List<PlaceType> list = (List<PlaceType>)session.get("placeType");
				session.put("clickTypeID", list.get(0).getPlaceTypeID());
				session.put("clickTypeName", list.get(0).getPlaceTypeName());
				List<Place> placeSetList = placeBiz.ManageGetPlace(Integer.parseInt(session.get("clickTypeID").toString()));
				session.put("placeSetList", placeSetList);
			}catch(Exception e){
				System.out.println(e);
				return "error";
			}
			return "success";
		}
		return "error";
	}
	
	/**
	 * 增加類別下新場地
	 * **/
	public String ManageSetPlace(){
		if(type.equals("APP")){
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				System.out.println("doAction");
				String s = json.getString("newPlace");
				System.out.println(s);
				Place newPlace = com.alibaba.fastjson.JSONObject.parseObject(s, Place.class);
				System.out.println(newPlace.getPlaceName() + newPlace.getTypeID());
				placeBiz.ManageSetPlace(newPlace);
				List<Place> placeSetList = placeBiz.ManageGetPlace(json.getInt("PlaceTypeID"));
				result = com.alibaba.fastjson.JSON.toJSONString(placeSetList);
				if(placeSetList == null){
					jsonMap = getErrorMap(jsonMap, "查無列表");
				}
				else{
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "增加類別下新場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}else if(type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			try{
				placeBiz.ManageSetPlace(place);
				List<Place> placeSetList = placeBiz.ManageGetPlace(Integer.parseInt(session.get("clickTypeID").toString()));
				session.put("placeSetList", placeSetList);
			}catch(Exception e){
				System.out.println(e);
				return "error";
			}
			return "success";
		}
		return null;
	}
	
	/**
	 * 刪除場地
	 * **/
	public String ManageDeletePlace(){
		if(type.equals("APP")){
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				String deletePlaceName = json.getString("deletePlaceName");
				int placeTypeID = Integer.parseInt(json.getString("PlaceTypeID"));
				placeBiz.ManageDeletePlace(placeTypeID, deletePlaceName);
				List<Place> placeSetList = placeBiz.ManageGetPlace(placeTypeID);
				result = com.alibaba.fastjson.JSON.toJSONString(placeSetList);
				if(placeSetList == null){
					jsonMap = getErrorMap(jsonMap, "查無列表");
				}
				else{
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "刪除場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}else if(type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			try{
				placeBiz.ManageDeletePlace(clickTypeID, clickName);
				List<Place> placeSetList = placeBiz.ManageGetPlace(clickTypeID);
				session.put("placeSetList", placeSetList);
			}catch(Exception e){
				System.out.println(e);
				return "error";
			}
			return "success";
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String GetOpenTime(){
		if(type.equals("APP")){
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				int placeTypeID = Integer.parseInt(json.getString("PlaceTypeID"));
				ArrayList<PlaceOpenTime> openTimeList = new ArrayList<>();
				openTimeList = (ArrayList<PlaceOpenTime>) placeBiz.ManageGetOpenTime(placeTypeID);
				if(openTimeList == null){
					jsonMap = getErrorMap(jsonMap, "查無列表");
				}
				else{
					result = com.alibaba.fastjson.JSON.toJSONString(openTimeList);
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "刪除場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}else if(type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			List<PlaceType> list = (List<PlaceType>)session.get("placeType");
			session.put("clickTypeID", list.get(0).getPlaceTypeID());
			session.put("clickTypeName", list.get(0).getPlaceTypeName());
			placeOpenList = placeBiz.ManageGetOpenTime(Integer.parseInt(session.get("clickTypeID").toString()));
			session.put("placeOpenList", placeOpenList);
			return "success";
		}
		return null;
	}
	
	public String SetOpenTime(){
		System.out.println("SetOpenTime");
		if(type.equals("APP")){
			System.out.println("App");
			Map<String, String> jsonMap = null;
			String result = "";
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				int placeTypeID = Integer.parseInt(json.getString("PlaceTypeID"));
				ArrayList<PlaceOpenTime> openTimeList = new ArrayList<>();
				openTimeList = (ArrayList<PlaceOpenTime>) com.alibaba.fastjson.JSON.parseArray(json.getString("PlaceOpenTimeList"), PlaceOpenTime.class);
				placeBiz.AppSetOpenTime(placeTypeID, openTimeList);
				openTimeList = (ArrayList<PlaceOpenTime>) placeBiz.ManageGetOpenTime(placeTypeID);
				if(openTimeList == null){
					jsonMap = getErrorMap(jsonMap, "查無列表");
				}
				else{
					result = com.alibaba.fastjson.JSON.toJSONString(openTimeList);
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			}catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "刪除場地失敗" + e.getMessage());
			}
			return ajaxJson(jsonMap);
		}else if(type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			System.out.println(clickTypeID + day + time);
			placeBiz.ManageSetOpenTime(clickTypeID, day, time);
			placeOpenList = placeBiz.ManageGetOpenTime(Integer.parseInt(session.get("clickTypeID").toString()));
			session.put("placeOpenList", placeOpenList);
			return "success";
		}
		return null;
	}
	
	public String SetPlaceType(){
		if(type.equals("APP")){
			
		}else if (type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			System.out.println("place setType " + clickTypeID);
			List<Place> placeSetList = placeBiz.ManageGetPlace(clickTypeID);
			session.put("placeSetList", placeSetList);
			session.put("clickTypeID", clickTypeID);
			session.put("clickTypeName", placeBiz.GetPlaceTypeName(clickTypeID));
			return "success";
		}
		return null;
	}
	
	public String SetTimeType(){
		if(type.equals("APP")){
			
		}else if (type.equals("WEB")){
			if(session.get("manager") == null){return "login";}
			System.out.println("time setType " + clickTypeID);
			List<PlaceType> placeOpenList = placeBiz.ManageGetOpenTime(clickTypeID);
			session.put("placeOpenList", placeOpenList);
			session.put("clickTypeID", clickTypeID);
			session.put("clickTypeName", placeBiz.GetPlaceTypeName(clickTypeID));
			return "success";
		}
		return null;
	}
}

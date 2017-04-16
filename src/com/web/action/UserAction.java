package com.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import net.sf.json.JSONObject;

import com.web.entity.Department;
import com.web.entity.Manager;
import com.web.entity.Place;
import com.web.entity.PlaceType;
import com.web.entity.Student;
import com.web.biz.PlaceBiz;
import com.web.biz.UserBiz;

public class UserAction extends BaseAction implements RequestAware, SessionAware{
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;
	private Student student;
	private Manager manager;
	private Place place;
	private String type;
	private PlaceBiz placeBiz;
	private List<PlaceType> pTypeList;
	private List<Place> placeSetList;
	Map<String, Object> request;
	Map<String, Object> session;
	
	public UserBiz getUserBiz() {return userBiz;}
	public void setUserBiz(UserBiz userBiz) {this.userBiz = userBiz;}
	public Student getStudent() {return student;}
	public void setStudent(Student student) {this.student = student;}
	public Manager getManager() {return manager;}
	public void setManager(Manager manager) {this.manager = manager;}
	public String getType(){return type;}
	public void setType(String type) {this.type = type;}
	public Place getPlace() {return place;}
	public void setPlace(Place place) {this.place = place;}
	public PlaceBiz getPlaceBiz() {return placeBiz;}
	public void setPlaceBiz(PlaceBiz placeBiz) {this.placeBiz = placeBiz;}
	public List<PlaceType> getpTypelist() {return pTypeList;}
	public void setpTypelist(List<PlaceType> pTypelist) {this.pTypeList = pTypelist;}
	public List<PlaceType> getpTypeList() {return pTypeList;}
	public void setpTypeList(List<PlaceType> pTypeList) {this.pTypeList = pTypeList;}
	public List<Place> getPlaceSetList() {return placeSetList;}
	public void setPlaceSetList(List<Place> placeSetList) {this.placeSetList = placeSetList;}
	
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
	
	/****使用者登入****/
	public String MemberLogin() {
		if (type.equals("APP")) {
			Map<String, String> jsonMap = null;
			String result = null;
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				String Account = json.getString("Account");
				String Password = json.getString("Password");
				Student s = new Student();
				s.setStudentAccount(Account);
				s.setStudentPassword(Password);
				Student ss = userBiz.getStudent(s);
				System.out.println("ss:" + ss);
				if(ss == null){
					jsonMap = getErrorMap(jsonMap, "帳號不存在或是密碼錯誤");
					return ajaxJson(jsonMap);
				}
				else{
					result = com.alibaba.fastjson.JSON.toJSONString(ss);
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "登入失敗:" + e.getMessage());
			}
			// add by hanly
			return ajaxJson(jsonMap);
		}
		else if(type.equals("WEB")){
			try{
				Student s =userBiz.getStudent(student);
				if(s != null)
				{
					session.put("student", s);
					pTypeList = placeBiz.GetPlaceType();
					session.put("placeType", pTypeList);
					List<Department> department = placeBiz.GetDepartment();
					session.put("departmentList", department);
					session.put("clickTypeID", pTypeList.get(0).getPlaceTypeID());
					session.put("clickTypeName", pTypeList.get(0).getPlaceTypeName());
					return "success"; 
				}
				else
				{
					return "error";
				}
			}catch(Exception e){
				System.out.println(e);
				return "error";
			}
		}
		return "error";
	}
	
	/****使用者登出****/
	public String MemberLogout(){
		if (type.equals("APP")){
			
		}else if(type.equals("WEB")){
			session.clear();
			return "logout";
		}
		return null;
	}
	
	/****管理者登入****/
	public String ManagerLogin() {
		if (type.equals("APP")) {
			Map<String, String> jsonMap = null;
			String result = null;
			try {
				jsonMap = getJsonMap();
				String params = getParameter(PARAMS);
				JSONObject json = JSONObject.fromObject(params);
				System.out.println("json:" + json);
				String Account = json.getString("Account");
				String Password = json.getString("Password");
				Manager m = new Manager();
				m.setManagerAccount(Account);
				m.setManagerPassword(Password);
				Manager mm = userBiz.getManager(m);
				System.out.println("mm:" + mm);
				if(mm == null){
					jsonMap = getErrorMap(jsonMap, "帳號不存在或是密碼錯誤");
					return ajaxJson(jsonMap);
				}
				else{
					result = com.alibaba.fastjson.JSONObject.toJSONString(mm);
					jsonMap.put("value", result);
					jsonMap.put("status", "success");
				}
			} catch (Exception e) {
				jsonMap = getErrorMap(jsonMap, "登入失敗:" + e.getMessage());
			}
			// add by hanly
			return ajaxJson(jsonMap);
		}
		else if(type.equals("WEB")){
			try{
				Manager m = userBiz.getManager(manager);
				if(m != null)
				{
					session.put("manager", m); 
					pTypeList = placeBiz.GetPlaceType();
					session.put("placeType", pTypeList);
					placeSetList = placeBiz.ManageGetPlace(pTypeList.get(0).getPlaceTypeID());
					session.put("placeSetList", placeSetList);
					session.put("clickTypeID", pTypeList.get(0).getPlaceTypeID());
					session.put("clickTypeName", pTypeList.get(0).getPlaceTypeName());
					return "success"; 
				}
				else
				{
					return "error";
				}
			}catch(Exception e){
				System.out.println(e);
				return "error";
			}
		}
		return "error";
	}
	
	/****使用者登出****/
	public String ManagerLogout(){
		if (type.equals("APP")){
			
		}else if(type.equals("WEB")){
			session.clear();
			return "logout";
		}
		return null;
	}
}

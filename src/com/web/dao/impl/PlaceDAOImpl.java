package com.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.web.dao.PlaceDAO;
import com.web.entity.Department;
import com.web.entity.Place;
import com.web.entity.PlaceOpenTime;
import com.web.entity.PlaceType;
import com.web.entity.Student;
import com.web.entity.UsePlace;

public class PlaceDAOImpl extends HibernateDaoSupport implements PlaceDAO{

	/****通用****/
	@Override
	public List GetPlaceType() {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql="from PlaceType order by placeTypeID";
		Query query = session.createQuery(hql);
		List placeType = query.list();
		Iterator iterator =  placeType.iterator();
		while(iterator.hasNext()) {
		    PlaceType p= (PlaceType) iterator.next();
		    System.out.println(p.getPlaceTypeID() + "  " + p.getPlaceTypeName());
		}
		return placeType;
	}
	
	@Override
	public String GetPlaceTypeName(int PlaceTypePK) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql="from PlaceType as p where p.placeTypeID=:placeTypeID";
		Query query = session.createQuery(hql);
		query.setInteger("placeTypeID", PlaceTypePK);
		return ((PlaceType)query.uniqueResult()).getPlaceTypeName();
	}
	
	/****管理****/
	@Override
	public void ManageSetPlace(Place place) {
		// TODO Auto-generated method stub
		System.out.println("do DAO " + place.getPlaceName());
		Session session = this.getSessionFactory().openSession();
		session.save(place);
	}

	@Override
	public List ManageGetPlace(int placeTypePK) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql="from Place as p where p.typeID=:typeID";
		Query query = session.createQuery(hql);
		query.setInteger("typeID", placeTypePK);
		List place = query.list();
		Iterator iterator =  place.iterator();
		while(iterator.hasNext()) {
		    Place p= (Place) iterator.next();
		    System.out.println(p.getPlaceID() + "  " + p.getPlaceName());
		}
		return place;
	}

	@Override
	public void ManageDeletePlace(int placeTypePK, String deletePlaceName) {
		// TODO Auto-generated method stub
		System.out.println("delete " + deletePlaceName + " " + placeTypePK);
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery("delete Place as p where p.placeName=:placeName and p.typeID=:typeID");
		query.setString("placeName", deletePlaceName);
		query.setInteger("typeID", placeTypePK);
		query.executeUpdate();
	}

	@Override
	public void ManageSetOpenTime(int placeTypePK, String day, String time) {
		// TODO Auto-generated method stub
		System.out.println("update " + placeTypePK + " " + day + " " + time);
		Session session = this.getSessionFactory().openSession();
		String hql="from PlaceOpenTime as p where p.placeTypeID=:placeTypeID and p.day=:day";
		Query query = session.createQuery(hql);
		query.setInteger("placeTypeID", placeTypePK);
		query.setString("day", day);
		String s = setParamTime((PlaceOpenTime)query.uniqueResult(), time);
		query = session.createQuery(s);
		query.setInteger("placeTypeID", placeTypePK);
		query.setString("day", day);
		query.executeUpdate();
	}
	
	@Override
	public void AppSetOpenTime(int placeTypePK,
			ArrayList<PlaceOpenTime> placeOpenTimeList) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		try{
		for(int i =0;i<placeOpenTimeList.size();i++){
			String hql="from PlaceOpenTime as p where p.placeTypeID=:placeTypeID and p.day=:day";
			Query query = session.createQuery(hql);
			query.setInteger("placeTypeID", placeTypePK);
			query.setString("day", placeOpenTimeList.get(i).getDay());
			String s = APPSetParamTime(placeOpenTimeList.get(i), (PlaceOpenTime)query.uniqueResult());
			System.out.println("s : " + s);
			if("".equals(s)) continue;
			query = session.createQuery(s);
			query.setInteger("placeTypeID", placeTypePK);
			query.setString("day", placeOpenTimeList.get(i).getDay());
			query.executeUpdate();
		}
		}catch(RuntimeException e){
			System.out.println(e);
		}
	}

	@Override
	public List ManageGetOpenTime(int placeTypePK) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql="from PlaceOpenTime as p where p.placeTypeID=:placeTypeID";
		Query query = session.createQuery(hql);
		query.setInteger("placeTypeID", placeTypePK);
		List placeOpenTimeList = query.list();
		Iterator iterator =  placeOpenTimeList.iterator();
		while(iterator.hasNext()) {
			PlaceOpenTime p= (PlaceOpenTime) iterator.next();
		    System.out.println(p.getPlaceOpenTimeID() + " " + p.getDay()
		    		+ " a0700:" + p.getA0700() + " a0730:" + p.getA0730() + " a0800:" + p.getA0800()
		    		+ " a0830:" + p.getA0830() + " a0900:" + p.getA0900() + " a0930:" + p.getA0930()
		    		+ " a1000:" + p.getA1000() + " a1030:" + p.getA1030() + " a1100:" + p.getA1100()
		    		+ " a1130:" + p.getA1130() + " p1200:" + p.getP1200() + " p1230:" + p.getP1230()
		    		+ " p1300:" + p.getP1300() + " p1330:" + p.getP1330() + " p1400:" + p.getP1400()
		    		+ " p1430:" + p.getP1430() + " p1500:" + p.getP1500() + " p1530:" + p.getP1530()
		    		+ " p1600:" + p.getP1600() + " p1630:" + p.getP1630() + " p1700:" + p.getP1700()
		    		+ " p1730:" + p.getP1730() + " p1800:" + p.getP1800() + " p1830:" + p.getP1830() 
		    		+ " p1900:" + p.getP1900() + " p1930:" + p.getP1930() + " p2000:" + p.getP2000() 
		    		+ " p2030:" + p.getP2030() + " p2100:" + p.getP2100() + " p2130:" + p.getP2130());
		}
		return placeOpenTimeList;
	}
	
	/****使用者****/
	@Override
	public List GetDepartment() {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql="from Department order by departmentID";
		Query query = session.createQuery(hql);
		List departmentList = query.list();
		Iterator iterator =  departmentList.iterator();
		while(iterator.hasNext()) {
			Department d= (Department) iterator.next();
		    System.out.println(d.getDepartmentID() + "  " + d.getDepartmentName());
		}
		return departmentList;
	}
	
	@Override
	public int GetDepartmentPK(String departmentName) {
		// TODO Auto-generated method stub
		String hql="from Department as d where d.departmentName=:departmentName";
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString("departmentName", departmentName);
		System.out.println("GetDepartmentPK:" + ((Department)query.uniqueResult()).getDepartmentID());
		return  ((Department)query.uniqueResult()).getDepartmentID();
	}
	
	@Override
	public List GetRecordPlace(Date date, int placeTypePK, String departmentName) {
		// TODO Auto-generated method stub
		System.out.println("GetRecordPlace : " + date + " " + placeTypePK + " " + departmentName);
		Session session = this.getSessionFactory().openSession();
		String sql="from UsePlace as u WHERE u.placeTypeID=:placeTypeID and u.placeDate=:placeDate and u.departmentName=:departmentName";
		Query query = session.createQuery(sql);
		query.setInteger("placeTypeID", placeTypePK);
		query.setDate("placeDate", date);
		query.setString("departmentName", departmentName);
		List usePlaceList = query.list();
		Iterator iterator =  usePlaceList.iterator();
		while(iterator.hasNext()) {
			UsePlace u = (UsePlace) iterator.next();
		    System.out.println(u.getStudentName());
		}
		return usePlaceList;
	}
	
	@Override
	public List GetRecordPlaceWithoutD(Date date, int placeTypePK) {
		// TODO Auto-generated method stub
		System.out.println("GetRecordPlaceWithoutD : " + date + " " + placeTypePK );
		Session session = this.getSessionFactory().openSession();
		String sql="from UsePlace as u WHERE u.placeTypeID=:placeTypeID and u.placeDate=:placeDate ";
		Query query = session.createQuery(sql);
		query.setInteger("placeTypeID", placeTypePK);
		query.setDate("placeDate", date);
		List usePlaceList = query.list();
		Iterator iterator =  usePlaceList.iterator();
		while(iterator.hasNext()) {
			UsePlace u = (UsePlace) iterator.next();
		    System.out.println(u.getStudentName());
		}
		return usePlaceList;
	}
	
	@Override
	public PlaceOpenTime GetOpenTime(int placeTypePK, String day) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String sql="from PlaceOpenTime as p WHERE p.placeTypeID=:placeTypeID and p.day=:day";
		Query query = session.createQuery(sql);
		query.setInteger("placeTypeID", placeTypePK);
		query.setString("day", day);
		return  ((PlaceOpenTime)query.uniqueResult());
	}
	
	@Override
	public void RecordPlace(UsePlace usePlace) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.save(usePlace);
	}
	
	private String setParamTime(PlaceOpenTime placeOpenTime, String time){
		String sql_1 = "update PlaceOpenTime p set ";
		String sql_2 = " where p.placeTypeID=:placeTypeID and p.day=:day";
		String s = "";
		if(time.equals("a0700")){
			s = "p.a0700 =";
			if(placeOpenTime.getA0700().equals("")){
				s = s + " 'checked'";
			}
			else if(placeOpenTime.getA0700().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a0730")){
			s = "p.a0730 =";
			if(placeOpenTime.getA0730().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA0730().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a0800")){
			s = "p.a0800 =";
			if(placeOpenTime.getA0800().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA0800().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a0830")){
			s = "p.a0830 =";
			if(placeOpenTime.getA0830().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA0830().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a0900")){
			s = "p.a0900 =";
			if(placeOpenTime.getA0900().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA0900().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a0930")){
			s = "p.a0930 =";
			if(placeOpenTime.getA0930().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA0930().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a1000")){
			s = "p.a1000 =";
			if(placeOpenTime.getA1000().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA1000().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a1030")){
			s = "p.a1030 =";
			if(placeOpenTime.getA1030().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA1030().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a1100")){
			s = "p.a1100 =";
			if(placeOpenTime.getA1100().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA1100().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("a1130")){
			s = "p.a1130 =";
			if(placeOpenTime.getA1130().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getA1130().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1200")){
			s = "p.p1200 =";
			if(placeOpenTime.getP1200().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1200().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1230")){
			s = "p.p1230 =";
			if(placeOpenTime.getP1230().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1230().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1300")){
			s = "p.p1300 =";
			if(placeOpenTime.getP1300().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1300().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1330")){
			s = "p.p1330 =";
			if(placeOpenTime.getP1330().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1330().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1400")){
			s = "p.p1400 =";
			if(placeOpenTime.getP1400().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1400().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1430")){
			s = "p.p1430 =";
			if(placeOpenTime.getP1430().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1430().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1500")){
			s = "p.p1500 =";
			if(placeOpenTime.getP1500().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1500().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1530")){
			s = "p.p1530 =";
			if(placeOpenTime.getP1530().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1530().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1600")){
			s = "p.p1600 =";
			if(placeOpenTime.getP1600().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1600().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1630")){
			s = "p.p1630 =";
			if(placeOpenTime.getP1630().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1630().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1700")){
			s = "p.p1700 =";
			if(placeOpenTime.getP1700().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1700().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1730")){
			s = "p.p1730 =";
			if(placeOpenTime.getP1730().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1730().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1800")){
			s = "p.p1800 =";
			if(placeOpenTime.getP1800().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1800().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1830")){
			s = "p.p1830 =";
			if(placeOpenTime.getP1830().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1830().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1900")){
			s = "p.p1900 =";
			if(placeOpenTime.getP1900().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1900().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p1930")){
			s = "p.p1930 =";
			if(placeOpenTime.getP1930().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP1930().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p2000")){
			s = "p.p2000 =";
			if(placeOpenTime.getP2000().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP2000().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p2030")){
			s = "p.p2030 =";
			if(placeOpenTime.getP2030().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP2030().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p2100")){
			s = "p.p2100 =";
			if(placeOpenTime.getP2100().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP2100().equals("checked")){
				s = s + " ''";
			}
		}
		else if(time.equals("p2130")){
			s = "p.p2130 =";
			if(placeOpenTime.getP2130().equals("")){
				s = s + " 'checked'";
			}else if(placeOpenTime.getP2130().equals("checked")){
				s = s + " ''";
			}
		}
		String sql = sql_1 + s + sql_2;
		System.out.println(sql);
		return sql;
	}
	
	
	private String APPSetParamTime(PlaceOpenTime newPlaceOpenTime, PlaceOpenTime oldPlaceOpenTime){
		String sql_1 = "update PlaceOpenTime p set ";
		String sql_2 = " where p.placeTypeID=:placeTypeID and p.day=:day";
		String s = " ";
		
		if(!newPlaceOpenTime.getA0700().equals(oldPlaceOpenTime.getA0700()))
			{s = s + "p.a0700 =";s = s + " '"+ newPlaceOpenTime.getA0700() +"' , ";}
		if(!newPlaceOpenTime.getA0730().equals(oldPlaceOpenTime.getA0730()))
			{s = s + "p.a0730 =";s = s + " '"+ newPlaceOpenTime.getA0730() +"' , ";}
		if(!newPlaceOpenTime.getA0800().equals(oldPlaceOpenTime.getA0800()))
			{s = s + "p.a0800 =";s = s + " '"+ newPlaceOpenTime.getA0800() +"' , ";}
		if(!newPlaceOpenTime.getA0830().equals(oldPlaceOpenTime.getA0830()))
			{s = s + "p.a0830 =";s = s + " '"+ newPlaceOpenTime.getA0830() +"' , ";}
		if(!newPlaceOpenTime.getA0900().equals(oldPlaceOpenTime.getA0900()))
			{s = s + "p.a0900 =";s = s + " '"+ newPlaceOpenTime.getA0900() +"' , ";}
		if(!newPlaceOpenTime.getA0930().equals(oldPlaceOpenTime.getA0930()))
			{s = s + "p.a0930 =";s = s + " '"+ newPlaceOpenTime.getA0930() +"' , ";}
		if(!newPlaceOpenTime.getA1000().equals(oldPlaceOpenTime.getA1000()))
			{s = s + "p.a1000 =";s = s + " '"+ newPlaceOpenTime.getA1000() +"' , ";}
		if(!newPlaceOpenTime.getA1030().equals(oldPlaceOpenTime.getA1030()))
			{s = s + "p.a1030 =";s = s + " '"+ newPlaceOpenTime.getA1030() +"' , ";}
		if(!newPlaceOpenTime.getA1100().equals(oldPlaceOpenTime.getA1100()))
			{s = s + "p.a1100 =";s = s + " '"+ newPlaceOpenTime.getA1100() +"' , ";}
		if(!newPlaceOpenTime.getA1130().equals(oldPlaceOpenTime.getA1130()))
			{s = s + "p.a1130 =";s = s + " '"+ newPlaceOpenTime.getA1130() +"' , ";}
		
		System.out.println("s :" + s + "length:" + s.length());
		if(s.length()>1){
			s = s.substring(0, s.length()-3);
		}
		else return "";
		String sql = sql_1 + s + sql_2;
		System.out.println(sql);
		return sql;
	}
}

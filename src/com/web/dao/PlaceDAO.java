package com.web.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web.entity.Place;
import com.web.entity.PlaceOpenTime;
import com.web.entity.UsePlace;

public interface PlaceDAO {
	
	/****通用****/
	public List GetPlaceType();
	public String GetPlaceTypeName(int PlaceTypePK);
	
	/****管理****/
	public List ManageGetPlace(int placeTypePK);
	public void ManageSetPlace(Place place);
	public void ManageDeletePlace(int placeTypePK, String deletePlaceName);
	public void ManageSetOpenTime(int placeTypePK, String day, String time);
	public void AppSetOpenTime(int placeTypePK, ArrayList<PlaceOpenTime> placeOpenTimeList);
	public List ManageGetOpenTime(int placeTypePK); 
	
	/****使用者****/
	public List GetDepartment();
	public int  GetDepartmentPK(String departmentName);
	public List GetRecordPlace(Date date, int placeTypePK, String departmentName);
	public List GetRecordPlaceWithoutD(Date date, int placeTypePK);
	public PlaceOpenTime GetOpenTime(int placeTypePK, String day);
	public void RecordPlace(UsePlace usePlace);
}
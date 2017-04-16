package com.web.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web.biz.PlaceBiz;
import com.web.dao.PlaceDAO;
import com.web.entity.Place;
import com.web.entity.PlaceOpenTime;
import com.web.entity.UsePlace;

public class PlaceBizImpl implements PlaceBiz{
	private PlaceDAO placeDAO;
	
	public PlaceDAO getPlaceDAO() {
		return placeDAO;
	}

	public void setPlaceDAO(PlaceDAO placeDAO) {
		this.placeDAO = placeDAO;
	}
	
	/****通用****/
	@Override
	public List GetPlaceType() {
		// TODO Auto-generated method stub
		return placeDAO.GetPlaceType();
	}
	
	@Override
	public String GetPlaceTypeName(int PlaceTypePK) {
		// TODO Auto-generated method stub
		return placeDAO.GetPlaceTypeName(PlaceTypePK);
	}

	/****管理****/
	@Override
	public void ManageSetPlace(Place place) {
		placeDAO.ManageSetPlace(place);
		// TODO Auto-generated method stub
	}

	@Override
	public List ManageGetPlace(int placeTypePK) {
		// TODO Auto-generated method stub
		return placeDAO.ManageGetPlace(placeTypePK);
	}

	@Override
	public void ManageDeletePlace(int placeTypePK, String deletePlaceName) {
		// TODO Auto-generated method stub
		placeDAO.ManageDeletePlace(placeTypePK, deletePlaceName);
	}

	@Override
	public void ManageSetOpenTime(int placeTypePK, String day, String time) {/****未實作****/
		// TODO Auto-generated method stub
		placeDAO.ManageSetOpenTime(placeTypePK, day, time);
	}
	
	@Override
	public void AppSetOpenTime(int placeTypePK,
			ArrayList<PlaceOpenTime> placeOpenTimeList) {
		// TODO Auto-generated method stub
		placeDAO.AppSetOpenTime(placeTypePK, placeOpenTimeList);
	}

	@Override
	public List ManageGetOpenTime(int placeTypePK) {
		// TODO Auto-generated method stub
		return placeDAO.ManageGetOpenTime(placeTypePK); /****未實作****/
	}

	/****使用者****/
	@Override
	public List GetDepartment() {
		// TODO Auto-generated method stub
		return placeDAO.GetDepartment();
	}
	
	@Override
	public int GetDepartmentPK(String departmentName) {
		// TODO Auto-generated method stub
		return placeDAO.GetDepartmentPK(departmentName);
	}

	@Override
	public List GetRecordPlace(Date date, int placeTypePK, String departmentName) {
		// TODO Auto-generated method stub
		return placeDAO.GetRecordPlace(date, placeTypePK, departmentName);
	}

	@Override
	public List GetRecordPlaceWithoutD(Date date, int placeTypePK) {
		// TODO Auto-generated method stub
		return placeDAO.GetRecordPlaceWithoutD(date, placeTypePK);
	}

	@Override
	public PlaceOpenTime GetOpenTime(int placeTypePK, String day) {
		// TODO Auto-generated method stub
		return placeDAO.GetOpenTime(placeTypePK, day);
	}

	@Override
	public void RecordPlace(UsePlace usePlace) {
		// TODO Auto-generated method stub
		placeDAO.RecordPlace(usePlace);
	}

}

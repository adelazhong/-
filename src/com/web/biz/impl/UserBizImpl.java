package com.web.biz.impl;

import java.util.Iterator;
import java.util.List;

import com.web.biz.UserBiz;
import com.web.entity.Manager;
import com.web.entity.Student;
import com.web.dao.UserDAO;

public class UserBizImpl implements UserBiz{
	private UserDAO userDAO;

	public void setUserDAO(com.web.dao.UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Student getStudent(Student student) {
		// TODO Auto-generated method stub
		Student s= userDAO.StudentLogin(student);
		return s;
	}

	@Override
	public Manager getManager(Manager manager) {
		// TODO Auto-generated method stub
		Manager m= userDAO.ManagerLogin(manager);
		return m;
	}
	
	
}

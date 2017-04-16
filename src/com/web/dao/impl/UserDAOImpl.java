package com.web.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.web.dao.UserDAO;
import com.web.entity.Manager;
import com.web.entity.Student;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO{

	@Override
	public Student StudentLogin(Student student) {
		String hql="from Student as s where s.studentAccount=:studentAccount and s.studentPassword=:studentPassword";
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString("studentAccount", student.getStudentAccount());
		query.setString("studentPassword", student.getStudentPassword());
		return  (Student)query.uniqueResult();
	}

	@Override
	public Manager ManagerLogin(Manager manager) {
		// TODO Auto-generated method stub
		String hql="from Manager as m where m.managerAccount=:managerAccount and m.managerPassword=:managerPassword";
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString("managerAccount", manager.getManagerAccount());
		query.setString("managerPassword", manager.getManagerPassword());
		return  (Manager)query.uniqueResult();
	}
}

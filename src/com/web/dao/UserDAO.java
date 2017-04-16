package com.web.dao;

import com.web.entity.Manager;
import com.web.entity.Student;

public interface UserDAO {
	public Student StudentLogin(Student student);
	public Manager ManagerLogin(Manager manager);
}

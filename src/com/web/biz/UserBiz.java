package com.web.biz;

import com.web.entity.Manager;
import com.web.entity.Student;

public interface UserBiz {
	public Student getStudent(Student student);
	public Manager getManager(Manager manager);
}

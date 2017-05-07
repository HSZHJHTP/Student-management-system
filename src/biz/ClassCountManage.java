package biz;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dao.ClassDao;
import entity.ClassCount;

public class ClassCountManage {
	ClassDao classDao = new ClassDao();
	public  ClassCount findByClass(String classname) {
		return classDao.findByClass(classname);
	}
}

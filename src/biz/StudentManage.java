package biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import dao.StudentDao;

import entity.Student;

public class StudentManage {
	StudentDao studentDao = new StudentDao();
	Scanner scanner = new Scanner(System.in);
	List<Student> list = new ArrayList<Student>();

	public List<Student> findAll() {
		return studentDao.findAll();
	}

	public Student findById(int id) {
		return studentDao.findById(id);
	}

	public List<Student> findByCondition(Student student) {
		return studentDao.findByCondition(student);
	}

	public boolean add(Student student) {

		int rs = studentDao.insert(student);
		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean modify(Student student) {

		int rs = studentDao.update(student);
		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void delete(Student student) {
		studentDao.delete(student);

	}

}

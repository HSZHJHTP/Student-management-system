package day20;

import java.io.Serializable;

public class Student implements Serializable {
	String name;
	String sex;
	int age;

	public Student() {

	}

	public void study() {
		System.out.println("我会学习");
	}

	public void show() {
		System.out.println(name);
		System.out.println(sex);
		System.out.println(age);
	}

	public Student(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

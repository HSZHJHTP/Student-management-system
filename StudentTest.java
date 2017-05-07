package day20;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentTest {
	Scanner scanner = new Scanner(System.in);
	List<Student> list = new ArrayList<Student>();

	public static void main(String[] args) {

		StudentTest studentTest = new StudentTest();
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------欢迎使用学生管理系统-------------");
		while (true) {

			System.out.println("--------------------------------------");
			System.out.println("1   录入" + "\t" + "2   展示" + "\t" + "3   删除"
					+ "\t" + "4   查找" + "\t" + "0   退出");
			int num1 = scanner.nextInt();
			if (num1 == 1) {
				studentTest.addStuInfo();
			} else if (num1 == 2) {
				studentTest.showStuInfo();
			} else if (num1 == 3) {
				studentTest.deleteaStuInfo();
			} else if (num1 == 4) {
				studentTest.searchStuInfo();
			} else if (num1 == 0) {
				break;
			}

		}

	}

	public void deleteaStuInfo() {
		System.out.println("--------------------------------------");
		System.out.println("请输入要删除的姓名");
		String name = scanner.next();
		for (int i = 0; i < list.size(); i++) {
			if (name.equals(list.get(i).getName())) {
				list.remove(i);
			}
		}
		write();

	}

	public void showStuInfo() {
		System.out.println("--------------------------------------");
		System.out.println("输入的学生信息为");
		read();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("姓名：" + list.get(i).getName());
			System.out.println("性别：" + list.get(i).getSex());
			System.out.println("年龄：" + list.get(i).getAge());

		}
	}

	public void addStuInfo() {
		System.out.println("请输入学生人数");
		int num2 = scanner.nextInt();

		for (int i = 0; i < num2; i++) {
			Student student = new Student();
			System.out.println("请输入姓名");
			student.name = scanner.next();
			System.out.println("请输入性别");
			student.sex = scanner.next();
			System.out.println("请输入年龄");
			student.age = scanner.nextInt();
			list.add(student);
		}
		write();
	}

	public void searchStuInfo() {
		System.out.println("--------------------------------------");
		System.out
				.println("1 根据姓名查找" + "\t" + "2  根据性别查找" + "\t" + "3  根据年龄查找");
		int num3 = scanner.nextInt();
		switch (num3) {
		case 1:
			searchByName();
			break;
		case 2:
			searchBySex();
			break;
		case 3:
			searchByAge();
			break;

		}
	}

	public void searchByAge() {
		System.out.println("--------------------------------------");
		System.out.println("请输入年龄");
		int age = scanner.nextInt();
		for (int i = 0; i < list.size(); i++) {
			if (age == list.get(i).getAge()) {
				System.out.println("姓名：" + list.get(i).getName());
				System.out.println("性别：" + list.get(i).getSex());
				System.out.println("年龄：" + list.get(i).getAge());
			}

		}
	}

	public void searchBySex() {
		System.out.println("--------------------------------------");
		System.out.println("请输入性别");
		String sex = scanner.next();
		for (int i = 0; i < list.size(); i++) {
			if (sex.endsWith(list.get(i).getSex())) {

				System.out.println("姓名：" + list.get(i).getName());
				System.out.println("性别：" + list.get(i).getSex());
				System.out.println("年龄：" + list.get(i).getAge());
			}

		}
	}

	public void searchByName() {
		System.out.println("--------------------------------------");
		System.out.println("请输入姓名");
		String name = scanner.next();
		for (int i = 0; i < list.size(); i++) {
			if (name.endsWith(list.get(i).getName())) {
				System.out.println("姓名：" + list.get(i).getName());
				System.out.println("性别：" + list.get(i).getSex());
				System.out.println("年龄：" + list.get(i).getAge());
			}

		}
	}

	public void write() {
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			outputStream = new FileOutputStream("f:/java.txt");
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void read() {
		File file = new File("f:/java.txt");
		if (file.exists()) {
			InputStream inputStream = null;
			ObjectInputStream objectInputStream = null;
			try {
				inputStream = new FileInputStream("f:/java.txt");
				objectInputStream = new ObjectInputStream(inputStream);
				list = (List<Student>) objectInputStream.readObject();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

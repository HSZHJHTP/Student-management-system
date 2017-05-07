package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import entity.ClassCount;
import entity.Student;

public class StudentDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	ClassDao classDao = new ClassDao();

	public void getConnection() {

		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取连接对象
			connection = DriverManager.getConnection(

			"jdbc:mysql://localhost:3306/studentsql", "root", "123");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Student> findAll() {
		List<Student> list = new ArrayList<Student>();

		try {
			getConnection();
			// 3.创建Statement
			statement = connection.createStatement();
			// 4.准备Sql
			String sql = "SELECT s.id,s.name,s.sex,s.age,c.classname FROM studo AS s INNER JOIN class AS c ON s.class_id=c.id;";
			// 5.执行Sql语句
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student student = new Student();
				student.setName(resultSet.getString("name"));
				student.setSex(resultSet.getString("sex"));
				student.setAge(resultSet.getInt("age"));
				student.setId(resultSet.getInt("id"));
				student.setClassname(resultSet.getString("classname"));
				list.add(student);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}

		}
		return list;
	}

	public List<Student> findByCondition(Student condition) {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM studo WHERE 1=1";
		if (!condition.getName().equals("")) {
			sql += " and name like  '%" + condition.getName() + "%'";

		}
		if (!condition.getSex().equals("")) {

			sql += " and sex = '" + condition.getSex() + "'";

		}
		if (condition.getAge() > 0) {
			sql += " and age = '" + condition.getAge() + "'";
		}
		try {
			getConnection();
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				student.setSex(resultSet.getString("sex"));
				student.setAge(resultSet.getInt("age"));
				student.setId(resultSet.getInt("id"));
				list.add(student);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}

		}
		return list;
	}

	public Student findById(int id) {
		Student student = new Student();

		try {
			getConnection();
			String sql = "SELECT s.id,s.name,s.sex,s.age,c.classname FROM studo AS s INNER JOIN class AS c ON s.class_id=c.id WHERE s.id = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				student.setSex(resultSet.getString("sex"));
				student.setAge(resultSet.getInt("age"));
				student.setClassname(resultSet.getString("classname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
		}
		return student;

	}

	public int insert(Student student) {
		int rs = 0;
		ClassCount classCount = new ClassCount();
		classCount = classDao.findByClass(student.getClassname());
		try {

			getConnection();
			String sql = "INSERT INTO studo(name,sex,age,class_id) VALUES(?,?,?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSex());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setInt(4, classCount.getId());
			rs = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	public int update(Student student) {
		int rs = 0;
		ClassCount classCount = new ClassCount();
		classCount = classDao.findByClass(student.getClassname());
		try {

			getConnection();
			String sql = "UPDATE studo SET name=?,sex=?,age=?, class_id=? WHERE id=? ;";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSex());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setInt(4, classCount.getId());
			preparedStatement.setInt(5, student.getId());
			rs = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rs;

	}

	public void delete(Student student) {

		try {
			getConnection();
			String sql = "DELETE FROM studo WHERE name = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setNString(1, student.getName());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}

		}
	}
}

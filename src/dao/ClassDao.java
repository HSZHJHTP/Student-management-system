package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.ClassCount;
import entity.Student;

public class ClassDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;

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

	public ClassCount findByClass(String classname) {
		ClassCount classCount = new ClassCount();
		try {
			getConnection();
			String sql = "SELECT id FROM class WHERE classname = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, classname);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				classCount.setId(resultSet.getInt("id"));

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
		return classCount;
	}
}

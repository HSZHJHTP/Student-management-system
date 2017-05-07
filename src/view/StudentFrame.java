package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import util.CallBack;

import com.sun.org.apache.bcel.internal.generic.NEW;

import entity.Student;

import biz.StudentManage;

import model.StudentTableModel;

public class StudentFrame {
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JTextField classnameText;
	JPanel panel2;
	JTable table;
	JButton addButton;
	JScrollPane scrollPane;
	StudentTableModel studentTableModel;
	StudentManage studentManage = new StudentManage();
	List<Student> list = new ArrayList<Student>();

	public void init() {
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("欢迎使用学生管理系统");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 点击关闭后关闭程序
		JPanel mainPanel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(layout);

		// 最上部的panel

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		// 姓名

		JLabel nameLabel = new JLabel("姓名：");
		panel1.add(nameLabel);
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(90, 30));
		panel1.add(nameText);

		// 性别

		JLabel sexLabel = new JLabel("性别：");
		panel1.add(sexLabel);
		sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(90, 30));
		panel1.add(sexText);

		// 年龄

		JLabel ageLabel = new JLabel("年龄:");
		panel1.add(ageLabel);
		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(90, 30));
		panel1.add(ageText);

		// 班级

		JLabel classnameLabel = new JLabel("班级:");
		panel1.add(classnameLabel);
		classnameText = new JTextField();
		classnameText.setPreferredSize(new Dimension(90, 30));
		panel1.add(classnameText);

		// 查找

		JButton saveButton = new JButton("查找");
		saveButton.setPreferredSize(new Dimension(60, 30));
		panel1.add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String sex = sexText.getText();
				int age = -1;
				if (!ageText.getText().equals("")) {
					age = Integer.parseInt(ageText.getText());
				}
				String classname = classnameText.getText();

				Student student = new Student();
				student.setName(name);
				student.setSex(sex);
				student.setAge(age);
				student.setClassname(classname);
				list = studentManage.findByCondition(student);
				refreshTable(list);

			}
		});

		mainPanel.add(panel1);

		// 中间部的panel

		panel2 = new JPanel();
		list = new ArrayList<Student>();
		list = studentManage.findAll();
		studentTableModel = new StudentTableModel(list);
		table = new JTable(studentTableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500, 300));
		panel2.add(scrollPane);
		mainPanel.add(panel2);

		// 最下部的panel

		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));

		// 新增

		addButton = new JButton("新增");
		saveButton.setPreferredSize(new Dimension(60, 30));
		panel3.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				AddStudentFrame addStudentFrame = new AddStudentFrame(
						new CallBack() {

							@Override
							public void callBack() {
								refreshTable();
							}
						});
				addStudentFrame.init();

			}
		});

		// 修改

		JButton modifyButton = new JButton("修改");
		modifyButton.setPreferredSize(new Dimension(60, 30));
		panel3.add(modifyButton);
		modifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					Student student = list.get(index);
					ModifyStudentFrame modifyStudentFrame = new ModifyStudentFrame(
							new CallBack() {

								@Override
								public void callBack() {
									refreshTable();
								}
							}, student.getId());

					modifyStudentFrame.init();
				} else {
					JOptionPane.showMessageDialog(null, "请选中要修改的学生");
				}
			}
		});

		// 删除

		JButton deleteButton = new JButton("删除");
		deleteButton.setPreferredSize(new Dimension(60, 30));
		panel3.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index > -1) {
					Student student = list.get(index);
				} else {
					JOptionPane.showMessageDialog(null, "请选中要删除的学生");
				}
				Student student = list.get(index);

				studentManage.delete(student);

				refreshTable();
			}
		});

		// 刷新

		JButton searchAllButton = new JButton("刷新");
		searchAllButton.setPreferredSize(new Dimension(60, 30));
		panel3.add(searchAllButton);
		searchAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();

			}
		});

		mainPanel.add(panel3);

		frame.setVisible(true);

	}

	public void refreshTable() {
		list = studentManage.findAll();
		studentTableModel.setData(list);// 刷新表格
	}

	public void refreshTable(List<Student> list) {
		studentTableModel.setData(list);// 刷新表格
	}

}

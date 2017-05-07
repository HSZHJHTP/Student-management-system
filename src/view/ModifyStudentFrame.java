package view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import util.CallBack;

import model.StudentTableModel;

import biz.ClassCountManage;
import biz.StudentManage;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.glassfish.external.amx.MBeanListener.Callback;

import dao.ClassDao;

import entity.ClassCount;
import entity.Student;

public class ModifyStudentFrame {
	StudentManage studentManage = new StudentManage();
	CallBack callBack;
	int id;
	JFrame frame;
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JComboBox comboBox;
	JRadioButton manRadioButton;
	JRadioButton womanRadioButton;
	String sex;
	ClassCount classCount = new ClassCount();
	ClassCountManage classCountManage = new ClassCountManage();

	public ModifyStudentFrame(CallBack callBack, int id) {
		this.callBack = callBack;
		this.id = id;
	}

	public void init() {
		Student student = studentManage.findById(id);
		frame = new JFrame();
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setTitle("修改学生");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 点击关闭后销毁它
		JPanel mainPanel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		// 姓名

		JLabel nameLabel = new JLabel("姓名：");
		panel1.add(nameLabel);
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(90, 30));
		nameText.setText(student.getName());
		panel1.add(nameText);

		// 性别

		JLabel sexLabel = new JLabel("性别：");
		panel2.add(sexLabel);
		manRadioButton = new JRadioButton("男");
		manRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sex = manRadioButton.getText();
			}
		});
		womanRadioButton = new JRadioButton("女");
		womanRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sex = womanRadioButton.getText();
			}
		});
		if (student.getSex().equals("男")) {
			manRadioButton.setSelected(true);

		} else if (student.getSex().equals("女")) {

			womanRadioButton.setSelected(true);
		}

		ButtonGroup sexRadiobButtonGroup = new ButtonGroup();
		sexRadiobButtonGroup.add(manRadioButton);
		sexRadiobButtonGroup.add(womanRadioButton);
		panel2.add(manRadioButton);
		panel2.add(womanRadioButton);

		// 年龄

		JLabel ageLabel = new JLabel("年龄:");
		panel3.add(ageLabel);

		ageText = new JTextField();
		ageText.setText(String.valueOf(student.getAge()));
		ageText.setPreferredSize(new Dimension(90, 30));
		panel3.add(ageText);

		// 班级

		JLabel classnameLabel = new JLabel("班级:");
		panel4.add(classnameLabel);
		String[] str = { "java1703", "ui1703", "h51703" };
		comboBox = new JComboBox(str);
		classCount = classCountManage.findByClass(student.getClassname());
		comboBox.setSelectedIndex(classCount.getId() - 1);
		panel4.add(comboBox);

		JButton saveButton = new JButton("保存");
		saveButton.setPreferredSize(new Dimension(60, 30));
		panel5.add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student.setName(nameText.getText());
				student.setAge(Integer.parseInt(ageText.getText()));
				student.setSex(sex);
				student.setId(id);
				student.setClassname(comboBox.getSelectedItem().toString());

				boolean flag = studentManage.modify(student);
				String message = "修改成功";
				if (!flag) {
					message = "修改失败";
				}
				JOptionPane.showMessageDialog(null, message);
				callBack.callBack();
				frame.dispose();
			}
		});

		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		frame.setVisible(true);
	}
}

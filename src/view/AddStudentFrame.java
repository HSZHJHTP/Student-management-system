package view;

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
import javax.swing.JTextField;

import util.CallBack;

import model.StudentTableModel;

import biz.StudentManage;

import com.sun.org.glassfish.external.amx.MBeanListener.Callback;

import entity.Student;

public class AddStudentFrame {
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JTextField classnameText;
	JComboBox comboBox;
	CallBack callBack;
	JRadioButton manRadioButton;
	JRadioButton womanRadioButton;
	String sex;
	StudentManage studentManage = new StudentManage();

	public AddStudentFrame(CallBack callBack) {
		this.callBack = callBack;
	}

	public void init() {
		final JFrame frame = new JFrame();
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setTitle("新增学生");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 点击关闭后销毁它
		JPanel mainPanel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JLabel nameLabel = new JLabel("姓名：");
		panel1.add(nameLabel);
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(90, 30));
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
		ButtonGroup sexRadiobButtonGroup = new ButtonGroup();
		sexRadiobButtonGroup.add(manRadioButton);
		sexRadiobButtonGroup.add(womanRadioButton);
		panel2.add(manRadioButton);
		panel2.add(womanRadioButton);

		// sexText = new JTextField();
		// sexText.setPreferredSize(new Dimension(90, 30));
		// panel2.add(sexText);
		// 年龄
		JLabel ageLabel = new JLabel("年龄:");
		panel3.add(ageLabel);
		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(90, 30));
		panel3.add(ageText);
		JLabel classnameLabel = new JLabel("班级:");
		panel4.add(classnameLabel);
//		classnameText = new JTextField();
//		classnameText.setPreferredSize(new Dimension(90, 30));
//		panel4.add(classnameText);
		String[] str = { "java1703", "ui1703", "h51703" };
		comboBox = new JComboBox(str);
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
				student.setClassname(comboBox.getSelectedItem().toString());
				List<Student> list = studentManage.findAll();

				boolean flag = studentManage.add(student);
				String message = "保存成功";
				if (!flag) {
					message = "保存失败";
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

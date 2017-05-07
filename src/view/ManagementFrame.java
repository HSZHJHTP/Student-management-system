package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagementFrame {
	public static void main(String[] args) {
		ManagementFrame managementFrame = new ManagementFrame();
		managementFrame.init();
	}

	public void init() {
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("欢迎使用学生管理系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击关闭后关闭程序
		JPanel mainPanel = (JPanel) frame.getContentPane();

		GridLayout layout = new GridLayout(2, 2);
		mainPanel.setLayout(layout);

		JButton studentButton = new JButton("学生管理");
		studentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentFrame studentFrame = new StudentFrame();
				studentFrame.init();
			}
		});
		JButton classButton = new JButton("班级管理");
		JButton subjectButton = new JButton("科目管理");
		JButton resultsButton = new JButton("成绩管理");
		mainPanel.add(studentButton);
		mainPanel.add(classButton);
		mainPanel.add(subjectButton);
		mainPanel.add(resultsButton);

		frame.setVisible(true);

	}
}

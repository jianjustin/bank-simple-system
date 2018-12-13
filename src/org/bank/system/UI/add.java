package org.bank.system.UI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class add extends JFrame implements ActionListener {
	JPanel n1 = new JPanel();// JPanel为javax.swing包中的面板容器，可以加到JFrame中，可以把其他compont加到里面
	JLabel lb1 = new JLabel("加数1:");
	JLabel lb2 = new JLabel("加数2:");
	JLabel b1 = new JLabel("");
	JLabel b2 = new JLabel("");
	JTextField jt1 = new JTextField("");
	JTextField jt2 = new JTextField("");
	JTextField jt3 = new JTextField("");
	JButton t1 = new JButton("求和");
	JButton t2 = new JButton("清除");

	public add() {
		setTitle("求和");
		setSize(300, 200);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(n1);
		n1.setLayout(new GridLayout(3, 4));
		n1.add(lb1);
		n1.add(jt1);
		n1.add(b1);
		n1.add(lb2);
		n1.add(jt2);
		n1.add(b2);
		n1.add(t1);
		n1.add(jt3);
		n1.add(t2);
		jt3.setEditable(false);
		t1.addActionListener(this);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText("");
				jt2.setText("");
				jt3.setText("");
			}
		});
		setVisible(true);
	}// ***界面实现

	public void actionPerformed(ActionEvent e) {
		double A, B;
		A = Double.parseDouble(jt1.getText());
		B = Double.parseDouble(jt2.getText());
		jt3.setText((A + B + " "));
	}

	public static void main(String[] args) {
		new add();
	}
}// 加法运算

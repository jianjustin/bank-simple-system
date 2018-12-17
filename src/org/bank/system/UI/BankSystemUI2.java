package org.bank.system.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BankSystemUI2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankSystemUI2 frame = new BankSystemUI2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BankSystemUI2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("转账");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(43, 31, 232, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("银行卡号：");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(30, 70, 74, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("账号密码：");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(30, 113, 74, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("取款金额：");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(30, 158, 74, 16);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("提交");
		btnNewButton_1.setBounds(371, 416, 117, 29);
		panel.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(145, 153, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 65, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(145, 103, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("对方卡号：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(30, 202, 74, 16);
		panel.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(145, 197, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		Panel panel_1 = new Panel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "name_44714480908909");
		
		JTree tree_1 = new JTree();
		scrollPane_1.setRowHeaderView(tree_1);
	}
	
	/**
	 * 初始化左侧菜单栏面板
	 * @return
	 */
	public DefaultMutableTreeNode getMenuPanel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("MENU");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("账户查询");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("存款");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("取款");
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("转账");
		DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("查看流水");
		DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("退出系统");
		root.add(node1);
		root.add(node2);
		root.add(node3);
		root.add(node4);
		root.add(node5);
		root.add(node6);
		//JTree jTree = new JTree(root);//目录树初始化
		//jTree.addTreeSelectionListener(treeListener);
		return root;
	}
}

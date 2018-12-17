package org.bank.system.UI.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.bank.system.UI.BankSystemUI;
import org.bank.system.UI.GridBagFn;
import org.bank.system.UI.UIContants;
import org.bank.system.UI.event.AddMoneyActionListener;
import org.bank.system.UI.event.GetMoneyActionListener;
import org.bank.system.UI.event.InformationChangedActionListener;
import org.bank.system.UI.event.LoginButtonActionListener;
import org.bank.system.UI.event.RegisterButtonActionListener;
import org.bank.system.UI.event.TransferMoneyActionListener;

public class MyPanel{
	
	public static BankSystemUI bankSystemUI;
	
	public JPanel loginUI(ActionEvent... e) {
		JPanel jPanel = new JPanel();
		
		FlowLayout flowLayout = new FlowLayout(1,UIContants.frame_width/2-UIContants.frame_login_panel_width/2,UIContants.frame_height/2-UIContants.frame_login_panel_height/2);
		bankSystemUI.jFrame.setLayout(flowLayout);
	
		jPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		jPanel.setPreferredSize(new Dimension(UIContants.frame_login_panel_width, UIContants.frame_login_panel_height));
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		bankSystemUI.contentPane.add(jPanel);
		
		JLabel jLabel1 = new JLabel("用户名");
		JTextField jTextField = new JTextField();
		JLabel jLabel2 = new JLabel("密码");
		JPasswordField jPasswordField = new JPasswordField();
		JButton loginButton = new JButton("登录");
		JButton regiterButton = new JButton("注册");
		
		/*添加登录接口*/
		loginButton.addActionListener(new LoginButtonActionListener(jTextField,jPasswordField));
		
		/*跳转注册页面*/
		regiterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel registerPanel = registerUI(e);
				updateUI(registerPanel, new FlowLayout(), UIContants.frame_main_width, UIContants.frame_main_height, null);
			}
		});
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		GridBagFn.addCompoment(jPanel,2, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jLabel1);
		GridBagFn.addCompoment(jPanel,0, 1, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jTextField);
		GridBagFn.addCompoment(jPanel,2, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jLabel2);
		GridBagFn.addCompoment(jPanel,0, 1, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jPasswordField);
		GridBagFn.addCompoment(jPanel,0, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, loginButton);
		GridBagFn.addCompoment(jPanel,0, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, regiterButton);
		
		return jPanel;
	}
	
	/**
	 * 注册
	 * @param e
	 * @return
	 */
	public JPanel registerUI(ActionEvent... e) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(UIContants.frame_main_width, UIContants.frame_main_height));
		panel.setLayout(null);
		
		JLabel label = new JLabel("姓名：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(58, 29, 78, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("身份证号码：");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(58, 57, 78, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("银行卡账号：");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(58, 93, 78, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("密码：");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(58, 129, 78, 16);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("确认密码：");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(58, 167, 78, 16);
		panel.add(label_4);
		
		JTextField textField = new JTextField();
		textField.setBounds(148, 24, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(148, 52, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(148, 88, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(148, 124, 130, 26);
		panel.add(passwordField);
		passwordField.setColumns(15);
		
		JPasswordField passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(148, 162, 130, 26);
		panel.add(passwordField_1);
		passwordField_1.setColumns(15);
		
		JButton button = new JButton("注册");
		button.setBounds(270, 224, 117, 29);
		/*添加注册事件*/
		button.addActionListener(new RegisterButtonActionListener(textField.getText(), textField_1.getText(), textField_2.getText(), new String(passwordField.getPassword()), new String(passwordField_1.getPassword())));
		panel.add(button);
		return panel;
		
	}
	
	/**
	 * 账户查询-返回右侧JPanel
	 * @return
	 */
	public JPanel showInformation(ActionEvent... e) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(UIContants.frame_main_width, UIContants.frame_main_height));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("储户名称：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(43, 31, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("账户卡号：");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(43, 70, 61, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("身份证号码：");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(43, 113, 61, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("账号密码：");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(43, 163, 61, 16);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("账户余额：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(43, 199, 61, 16);
		panel.add(lblNewLabel_1);
		
		JButton editButton = new JButton("修改");
		editButton.setBounds(225, 416, 117, 29);
		panel.add(editButton);
		
		JButton saveButton = new JButton("保存");
		saveButton.setBounds(371, 416, 117, 29);
		panel.add(saveButton);
		
		JTextField textField = new JTextField("请输入储户名称");
		textField.setBounds(145, 26, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JTextField textField_1 = new JTextField("请输入账号卡号");
		textField_1.setBounds(145, 65, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JTextField textField_2 = new JTextField("请输入身份证号码");
		textField_2.setBounds(145, 103, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		
		JTextField textField_3 = new JTextField("请输入密码");
		textField_3.setBounds(145, 158, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		
		JTextField textField_4 = new JTextField(null);
		textField_4.setBounds(145, 194, 130, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEditable(false);
		
		/*设置已登录用户信息*/
		if(null != bankSystemUI.loginSuccessBankAccount) {
			textField.setText(bankSystemUI.loginSuccessBankAccount.getBankAccountName());
			textField_1.setText(bankSystemUI.loginSuccessBankAccount.getBankAccountCard());
			textField_2.setText(bankSystemUI.loginSuccessBankAccount.getBankAccountIdCard());
			textField_3.setText(bankSystemUI.loginSuccessBankAccount.getBankAccountPassword());
		}
		
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
			}
		});
		
		saveButton.addActionListener(new InformationChangedActionListener(textField,textField_1,textField_2,textField_3));
		
		return panel;
	}
	
	/**
	 * 取款
	 * @param e
	 * @return
	 */
	public JPanel getMoney(ActionEvent... e) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(UIContants.frame_main_width, UIContants.frame_main_height));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("取款");
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
		
		JTextField textField = new JTextField();//取款金额
		textField.setBounds(145, 153, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JPasswordField jPasswordField = new JPasswordField();//密码
		jPasswordField.setBounds(145, 103, 130, 26);
		panel.add(jPasswordField);
		jPasswordField.setColumns(10);
		
		JTextField textField_2 = new JTextField();//用户名
		textField_2.setBounds(145, 65, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_1.addActionListener(new GetMoneyActionListener(textField,textField_2,jPasswordField));
		
		return panel;
	}
	
	/**
	 * 存款
	 * @param e
	 * @return
	 */
	public JPanel addMoney(ActionEvent... e) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(UIContants.frame_main_width, UIContants.frame_main_height));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("存款");
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
		
		JLabel lblNewLabel_6 = new JLabel("存款金额：");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(30, 158, 74, 16);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("提交");
		btnNewButton_1.setBounds(371, 416, 117, 29);
		panel.add(btnNewButton_1);
		
		//金额
		JTextField textField = new JTextField();
		textField.setBounds(145, 153, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		//密码
		JPasswordField jPasswordField = new JPasswordField();
		jPasswordField.setBounds(145, 103, 130, 26);
		panel.add(jPasswordField);
		jPasswordField.setColumns(10);
		
		//账户
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(145, 65, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_1.addActionListener(new AddMoneyActionListener(textField,textField_2,jPasswordField));
		return panel;
	}
	
	/**
	 * 转账
	 * @param e
	 * @return
	 */
	public JPanel transferMoney(ActionEvent... e) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(UIContants.frame_main_width, UIContants.frame_main_height));
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
		
		JLabel lblNewLabel_1 = new JLabel("对方卡号：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(30, 202, 74, 16);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("提交");
		btnNewButton_1.setBounds(371, 416, 117, 29);
		panel.add(btnNewButton_1);
		
		//对方卡号
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(145, 197, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		//取款金额
		JTextField textField = new JTextField();
		textField.setBounds(145, 153, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		//密码
		JPasswordField jPasswordField = new JPasswordField();
		jPasswordField.setBounds(145, 103, 130, 26);
		panel.add(jPasswordField);
		jPasswordField.setColumns(10);
		
		//用户名
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(145, 65, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_1.addActionListener(new TransferMoneyActionListener(textField_2,textField,textField_3,jPasswordField));
		
		return panel;
		
	}
	
	/**
	 * 查看流水
	 * @param e
	 * @return
	 */
	public JPanel showMoneyList(ActionEvent... e) {
		
		JPanel jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(UIContants.frame_main_width, UIContants.frame_main_height));
		jPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(43, 31, 61, 16);
		jPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("jian");
		lblNewLabel_1.setBounds(148, 31, 61, 16);
		jPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(43, 70, 61, 16);
		jPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("jian");
		lblNewLabel_3.setBounds(148, 70, 61, 16);
		jPanel.add(lblNewLabel_3);
		
		return jPanel;
		
	}
	
	/**
	 * 退出系统
	 * @param e
	 * @return
	 */
	public JPanel exit(ActionEvent... e) {
		bankSystemUI.jFrame.setSize(UIContants.frame_width, UIContants.frame_height);
		FlowLayout flowLayout = new FlowLayout(1,UIContants.frame_width/2-UIContants.frame_login_panel_width/2,UIContants.frame_height/2-UIContants.frame_login_panel_height/2);
		bankSystemUI.jFrame.setLayout(flowLayout);
		bankSystemUI.jFrame.setJMenuBar(null);
		bankSystemUI.contentPane.remove(bankSystemUI.jPanel);
		bankSystemUI.contentPane.add(bankSystemUI.loginJPanel);
		bankSystemUI.jPanel = bankSystemUI.loginJPanel;
		bankSystemUI.contentPane.validate();//重构内容面板
		bankSystemUI.contentPane.repaint();//重绘内容面板
		return bankSystemUI.jPanel;
		
	}
	
	/**
	 * 系统菜单页面
	 * @param e
	 * @param jPanel -- 移除面板
	 */
	public void updateUI(JPanel panel) {
		bankSystemUI.contentPane.remove(bankSystemUI.jPanel);//移除登录容器
		bankSystemUI.jFrame.setLayout(new FlowLayout());//清空布局
		bankSystemUI.jFrame.setJMenuBar(bankSystemUI.buildJMenuBar());//初始化头部菜单栏
		bankSystemUI.jFrame.setSize(UIContants.frame_main_width, UIContants.frame_main_height);
		bankSystemUI.contentPane.add(panel);
		bankSystemUI.jPanel = panel;//保存当前面板
		bankSystemUI.contentPane.validate();//重构内容面板
		bankSystemUI.contentPane.repaint();//重绘内容面板
	}
	
	/**
	 * 更新系统GUI
	 * @param panel
	 * @param layoutManager
	 * @param width
	 * @param height
	 * @param menubar
	 */
	public void updateUI(JPanel panel,LayoutManager layoutManager,int width,int height,JMenuBar menubar) {
		bankSystemUI.contentPane.remove(bankSystemUI.jPanel);//移除登录容器
		bankSystemUI.jFrame.setLayout(layoutManager);//清空布局
		bankSystemUI.jFrame.setJMenuBar(menubar);//初始化头部菜单栏
		bankSystemUI.jFrame.setSize(UIContants.frame_main_width, UIContants.frame_main_height);
		bankSystemUI.contentPane.add(panel);
		bankSystemUI.jPanel = panel;//保存当前面板
		bankSystemUI.contentPane.validate();//重构内容面板
		bankSystemUI.contentPane.repaint();//重绘内容面板
	}

}

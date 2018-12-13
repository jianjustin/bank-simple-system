package org.bank.system.UI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;


public class BankSystemUI {
	
	public JFrame jFrame;//登录页面
	public JFrame jFrame1;//主菜单
	public JSplitPane splitPane;//主菜单面板,进行面板拆分
	public Container contentPane;
	public JPanel jPanel;//登录面板
	public TreeListener treeListener = new TreeListener();
	
	/*当前账户数据*/
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	
	
	public BankSystemUI() {
		this.setUIManager(null);
		jFrame = new JFrame(UIContants.frame_name);
		jFrame.setSize(UIContants.frame_width, UIContants.frame_height);
		jFrame.setLocationRelativeTo(null);//设置窗体居中显示
		jFrame.setContentPane(new JPanel());//设置主容器
		contentPane = jFrame.getContentPane();
		TreeListener.bankSystemUI = this;//初始化菜单栏监听UI
		
		FlowLayout flowLayout = new FlowLayout(1,UIContants.frame_width/2-140,UIContants.frame_height/2-70);
		jFrame.setLayout(flowLayout);
		
		jPanel = new JPanel();
		jPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		jPanel.setPreferredSize(new Dimension(280, 140));
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		contentPane.add(jPanel);
		
		JLabel jLabel1 = new JLabel("用户名");
		JTextField jTextField = new JTextField();
		JLabel jLabel2 = new JLabel("密码");
		JPasswordField jPasswordField = new JPasswordField();
		JButton jButton = new JButton("登录");
		
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initMenuUI(e);
			}
		});
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		GridBagFn.addCompoment(jPanel,2, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jLabel1);
		GridBagFn.addCompoment(jPanel,0, 1, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jTextField);
		GridBagFn.addCompoment(jPanel,2, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jLabel2);
		GridBagFn.addCompoment(jPanel,0, 1, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jPasswordField);
		GridBagFn.addCompoment(jPanel,0, 0, 0.05, new Insets(5,5,5,5), gridBagLayout,gridBagConstraints, jButton);
		
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 系统菜单页面
	 * @param e
	 * @param jPanel -- 移除面板
	 */
	public void initMenuUI(ActionEvent e) {
		contentPane.remove(jPanel);//移除登录容器
		jFrame.setLayout(new FlowLayout());//清空布局
		splitPane = new JSplitPane();
		jFrame.setSize(UIContants.frame_menu_width, UIContants.frame_menu_height);
		splitPane.setPreferredSize(new Dimension(UIContants.frame_menu_width, UIContants.frame_menu_height));
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setDividerLocation(180);
		
		splitPane.setLeftComponent(getMenuPanel());
		splitPane.setRightComponent(showInformation());
		contentPane.add(splitPane);
		contentPane.validate();//重构内容面板
		contentPane.repaint();//重绘内容面板
	}
	
	/**
	 * 账户查询-返回右侧JPanel
	 * @return
	 */
	public JPanel showInformation(TreeSelectionEvent... e) {
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);//加入右侧面板
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("储户名称：");
		lblNewLabel.setBounds(43, 31, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("账户卡号：");
		lblNewLabel_2.setBounds(43, 70, 61, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("身份证号码：");
		lblNewLabel_4.setBounds(43, 113, 61, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("账号密码：");
		lblNewLabel_6.setBounds(43, 163, 61, 16);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("账户余额：");
		lblNewLabel_1.setBounds(43, 199, 61, 16);
		panel.add(lblNewLabel_1);
		
		JButton editButton = new JButton("修改");
		editButton.setBounds(225, 416, 117, 29);
		panel.add(editButton);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				textField_4.setEditable(true);
			}
		});
		
		JButton saveButton = new JButton("保存");
		saveButton.setBounds(371, 416, 117, 29);
		panel.add(saveButton);
		
		textField = new JTextField("111111");
		textField.setBounds(145, 26, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		textField_1 = new JTextField("111111");
		textField_1.setBounds(145, 65, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		textField_2 = new JTextField("111111");
		textField_2.setBounds(145, 103, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		
		textField_3 = new JTextField("111111");
		textField_3.setBounds(145, 158, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		
		textField_4 = new JTextField("111111");
		textField_4.setBounds(145, 194, 130, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEditable(false);
		
		return panel;
	}
	
	/**
	 * 取款
	 * @param e
	 * @return
	 */
	public JPanel getMoney(TreeSelectionEvent... e) {
		JPanel jPanel = new JPanel();
		splitPane.setRightComponent(jPanel);//加入右侧面板
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
	 * 存款
	 * @param e
	 * @return
	 */
	public JPanel addMoney(TreeSelectionEvent... e) {
		JPanel jPanel = new JPanel();
		splitPane.setRightComponent(jPanel);//加入右侧面板
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
	 * 转账
	 * @param e
	 * @return
	 */
	public JPanel transferMoney(TreeSelectionEvent... e) {
		
		JPanel jPanel = new JPanel();
		splitPane.setRightComponent(jPanel);//加入右侧面板
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
	 * 查看流水
	 * @param e
	 * @return
	 */
	public JPanel showMoneyList(TreeSelectionEvent... e) {
		
		JPanel jPanel = new JPanel();
		splitPane.setRightComponent(jPanel);//加入右侧面板
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
	public JPanel exit(TreeSelectionEvent... e) {
		contentPane.remove(splitPane);//移除登录容器
		jFrame.setSize(UIContants.frame_width, UIContants.frame_height);
		FlowLayout flowLayout = new FlowLayout(1,UIContants.frame_width/2-140,UIContants.frame_height/2-70);
		jFrame.setLayout(flowLayout);
		contentPane.add(jPanel);
		contentPane.validate();//重构内容面板
		contentPane.repaint();//重绘内容面板
		
		
		return jPanel;
		
	}
	
	
	/**
	 * 初始化左侧菜单栏面板
	 * @return
	 */
	public JPanel getMenuPanel() {
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
		JTree jTree = new JTree(root);//目录树初始化
		jTree.addTreeSelectionListener(treeListener);//添加目录监听器
		JScrollPane scrollPane = new JScrollPane(jTree);//初始化滚动面板
		scrollPane.setPreferredSize(new Dimension(UIContants.frame_menu_left_width, UIContants.frame_menu_height));
		JPanel jPanel = new JPanel();
		jPanel.add(scrollPane);
		return jPanel;
	}
	
	/**
	 * 设置系统级UI
	 * @param clazzName
	 */
	public void setUIManager(String clazzName) {
		try {
			if(null == clazzName || clazzName == "")
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			else
				UIManager.setLookAndFeel(clazzName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		BankSystemUI bankSystemUI = new BankSystemUI();
	}

}

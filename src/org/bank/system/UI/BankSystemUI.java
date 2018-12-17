package org.bank.system.UI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.bank.system.UI.event.ButtonActionListener;
import org.bank.system.UI.panel.MyPanel;
import org.bank.system.database.SqlPoolFactory;
import org.bank.system.model.BankAccount;


public class BankSystemUI {
	
	public JFrame jFrame;//应用
	public Container contentPane;//应用容器
	public JPanel jPanel;//当前面板
	public JPanel loginJPanel;//登录面板
	public MyPanel myPanel = new MyPanel();//面板操作柄
	public BankAccount loginSuccessBankAccount;//已登录信息
	public Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	
	/**
	 * 构造函数--是程序入口
	 */
	public BankSystemUI() {
		this.setUIManager(null);//默认UI
		jFrame = new JFrame(UIContants.frame_name);
		jFrame.setSize(UIContants.frame_width, UIContants.frame_height);
		jFrame.setLocationRelativeTo(null);//设置窗体居中显示
		jFrame.setContentPane(new JPanel());//设置主容器
		contentPane = jFrame.getContentPane();
		
		ButtonActionListener.bankSystemUI = this;//保存操作柄
		MyPanel.bankSystemUI = this;
		
		
		FlowLayout flowLayout = new FlowLayout(1,UIContants.frame_width/2-UIContants.frame_login_panel_width/2,UIContants.frame_height/2-UIContants.frame_login_panel_height/2);
		jFrame.setLayout(flowLayout);
		
		jPanel = myPanel.loginUI();//获取登录面板
		
		contentPane.add(jPanel);
		loginJPanel = jPanel;
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 系统菜单页面
	 * @param e
	 * @param jPanel -- 移除面板
	 */
	public void initMenuUI(ActionEvent e) {
		contentPane.remove(jPanel);//移除登录容器
		jFrame.setLayout(new FlowLayout());//清空布局
		jFrame.setJMenuBar(buildJMenuBar());//初始化头部菜单栏
		jFrame.setSize(UIContants.frame_main_width, UIContants.frame_main_height);
		JPanel panel = myPanel.showInformation();
		contentPane.add(panel);
		jPanel = panel;//保存当前面板容器
		contentPane.validate();//重构内容面板
		contentPane.repaint();//重绘内容面板
	}
	
	
	
	/**
	 * 创建头部菜单(菜单监听类MenuActionListener)
	 * @return
	 */
	public JMenuBar buildJMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();
		MenuActionListener menuActionListener = new MenuActionListener();
		JMenu jMenu1 = new JMenu("账户");
		JMenu jMenu2 = new JMenu("管理");
		JMenuItem jMenuItem1 = new JMenuItem("账户信息");
		jMenuItem1.addActionListener(menuActionListener);
		JMenuItem jMenuItem2 = new JMenuItem("退出系统");
		jMenuItem2.addActionListener(menuActionListener);
		JMenuItem jMenuItem3 = new JMenuItem("存款");
		jMenuItem3.addActionListener(menuActionListener);
		JMenuItem jMenuItem4 = new JMenuItem("取款");
		jMenuItem4.addActionListener(menuActionListener);
		JMenuItem jMenuItem5 = new JMenuItem("转账");
		jMenuItem5.addActionListener(menuActionListener);
		//JMenuItem jMenuItem6 = new JMenuItem("流水");
		//jMenuItem6.addActionListener(menuActionListener);
		jMenu1.add(jMenuItem1);
		jMenu1.add(jMenuItem2);
		jMenu2.add(jMenuItem3);
		jMenu2.add(jMenuItem4);
		jMenu2.add(jMenuItem5);
		//jMenu2.add(jMenuItem6);
		jMenuBar.add(jMenu1);
		jMenuBar.add(jMenu2);
		return jMenuBar;
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
		SqlPoolFactory.initConnectionPool();//初始化数据库
		BankSystemUI bankSystemUI = new BankSystemUI();//初始化GUI
	}

}

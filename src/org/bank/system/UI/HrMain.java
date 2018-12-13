package org.bank.system.UI;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class HrMain extends JFrame implements TreeSelectionListener {
	private static final URLClassLoader URLClassLoader = null;
//框架大小
	Dimension faceSize = new Dimension(650, 450);
//程序图标
	Image icon;
//建立JTree目录树对象
	JTree tree;
//书数据结构中的通用节点
//字面含义，默认可变的树节点类
	DefaultMutableTreeNode root;// 人事管理系统根节点
	DefaultMutableTreeNode node1;// 基本信息管理
	DefaultMutableTreeNode node2;// 人员管理节点
	DefaultMutableTreeNode node3;// 人员考核管理
	DefaultMutableTreeNode node4;// 劳资管理
	DefaultMutableTreeNode node5;//
	DefaultMutableTreeNode leafnode;// 二级节点
	TreePath treePath;// 节点路径类
//主界面面板
//JSplitPane 用于分割两个（只能是两个）Component
//两个Component图形化分割
//并且这两个Component可以由用户交互式调整大小
//split--分裂，拆分
	public static JSplitPane splitPane;
	JPanel panel1;// 在左侧放目录树
	JPanel panel2;
	JPanel panel3;// 在右侧显示选择节点后的界面

	JLabel welcome = new JLabel();//用于显示欢迎信息的标签

	JScrollPane scrollPane;//滚动面板对象

	public HrMain() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(faceSize);
		this.setTitle("人事管理系统");
		this.setIconImage(icon);
		this.setResizable(false);
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 初始化方法 创建左侧菜单树
	 */
	private void Init() {
		root = new DefaultMutableTreeNode("人事管理系统");
		node1 = new DefaultMutableTreeNode("基本信息管理");
		node2 = new DefaultMutableTreeNode("人员调动管理");
		node3 = new DefaultMutableTreeNode("人员考核管理");
		node4 = new DefaultMutableTreeNode("劳资管理");
		root.add(node1);
		leafnode = new DefaultMutableTreeNode("添加人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("修改人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("删除人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("查询人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("部门管理");
		node1.add(leafnode);
		root.add(node2);
		leafnode = new DefaultMutableTreeNode("人员调动");
		node2.add(leafnode);
		leafnode = new DefaultMutableTreeNode("调动历史查询");
		node2.add(leafnode);
		root.add(node3);
		leafnode = new DefaultMutableTreeNode("人员考核");
		node3.add(leafnode);
		leafnode = new DefaultMutableTreeNode("考核历史查询");
		node3.add(leafnode);
		root.add(node4);
		leafnode = new DefaultMutableTreeNode("劳资分配管理");
		node4.add(leafnode);
		leafnode = new DefaultMutableTreeNode("劳资历史查询");
		node4.add(leafnode);
		tree = new JTree(root);
//添加tree到滚动面板 即菜单树内容多时 出现滚动条
		scrollPane = new JScrollPane(tree);
		scrollPane.setPreferredSize(new Dimension(150, 400));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//初始化Panel面板
		panel1 = new JPanel();// 左侧面板
		panel2 = new JPanel();// 没用到该面板
		panel3 = new JPanel();// 右侧面板
		panel1.add(scrollPane);// 显示目录树 ，目录树在滚动面板上
		welcome.setText("欢迎使用人事管理系统");
		welcome.setFont(new Font("Dialog", 0, 16));
		panel3.add(welcome);// 加到panel3面板上
//创建分割面板
		splitPane = new JSplitPane();
//设置oneTouchExpandable属性的值 Expandable--展开式
//要使JsplitPane在分割条上提供一个小部件快速展开/折叠分割条 次属性必须为true
		splitPane.setOneTouchExpandable(false);
//设置连续布局为true
//如果分割条改变位置是逐渐连续重绘
		splitPane.setContinuousLayout(true);
//设置分割面板的大小
		splitPane.setPreferredSize(new Dimension(150, 400));
//设置分割面板的方向--水平分割
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
//设置分割面板左侧组件-panel1 左侧组件-panel3
		splitPane.setLeftComponent(panel1);
		splitPane.setRightComponent(panel3);
//设置分割面板分割条大小
		splitPane.setDividerSize(2);
//设置分割条的位置  divider--分割线
//可以将数值分别设定为150，161，171进行测试 查看效果
		splitPane.setDividerLocation(161);
//生成主界面 等价this.add(spitpane)
		this.setContentPane(splitPane);
		this.setVisible(true);
//添加目录树选择事件监听
		tree.addTreeSelectionListener(this);
//设置关闭操作监听代码
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/*
	 * Jtree事件处理 addTreeSelectionListener增加目录树监听 必须实现的方法 (non-Javadoc)
	 * 
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.
	 * TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent tse) {
// TODO Auto-generated method stub
//定义默认书节点对象
//tse--在目录树中选择触发的时间
//tse.getPath.getLatPathComponent
//--返回在目录树的节点
		DefaultMutableTreeNode dnode = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
//在控制台输出选中的节点
		System.out.println("dnode=" + dnode);
//获取节点名称 转换成字符串
		String node_str = dnode.toString();
//如果选择的节点为人事管理系统
		if (node_str == "人事管理系统") {
//设置分割面板右侧组件为panel3
//显示欢迎使用认识管理系统
			splitPane.setRightComponent(panel3);
		} else if (node_str == "基本信息管理") {
			treePath = new TreePath(node1.getPath());
			if (tree.isExpanded(treePath)) {
				tree.collapsePath(treePath);
			} else {
				tree.expandPath(treePath);
			}
		} else if (node_str == "添加人员信息") {
//Node11Panel node11Panel = new Node11Panel();
//splitPane.setRightComponent(node11Panel); 
		}
	}

	/** * 通过给定的文件名获得图像 */
	Image getImage(String filename) {
		URLClassLoader urlLoader = (URLClassLoader);
		this.getClass().getClassLoader();
		URL url = null;
		Image image = null;
		url = urlLoader.findResource(filename);
		image = Toolkit.getDefaultToolkit().getImage(url);
		MediaTracker mediaTracker = new MediaTracker(this);
		try {
			mediaTracker.addImage(image, 0);
			mediaTracker.waitForID(0);
		} catch (InterruptedException _ex) {
			image = null;
		}
		if (mediaTracker.isErrorID(0)) {
			image = null;
		}
		return image;
	}

	public static void main(String[] args) {
		HrMain hrMain = new HrMain();
	}
}

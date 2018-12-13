package org.bank.system.UI;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 目录树监听器
 * @author jian
 *
 */
public class TreeListener implements TreeSelectionListener{
	
	public static BankSystemUI bankSystemUI;//操作UI

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode dnode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();//获取树节点
		String itemstr = dnode.toString();
		System.out.println(dnode.toString());
		
		if("账户查询".equals(itemstr))
			bankSystemUI.showInformation(e);
		else if("取款".equals(itemstr))
			bankSystemUI.getMoney(e);
		else if("存款".equals(itemstr))
			bankSystemUI.addMoney(e);
		else if("转账".equals(itemstr))
			bankSystemUI.transferMoney(e);
		else if("查看流水".equals(itemstr))
			bankSystemUI.showMoneyList(e);
		else if("退出系统".equals(itemstr))
			bankSystemUI.exit(e);
		else 
			return;
		
	}

}

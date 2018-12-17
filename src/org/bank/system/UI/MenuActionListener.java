package org.bank.system.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.bank.system.UI.event.ButtonActionListener;

/**
 * 目录树监听器
 * @author jian
 *
 */
public class MenuActionListener extends ButtonActionListener{

	public void actionPerformed(ActionEvent e) {
		String itemstr = e.getActionCommand();
		JPanel panel = null;
		if("账户信息".equals(itemstr))
			panel = bankSystemUI.myPanel.showInformation(e);
		else if("取款".equals(itemstr))
			panel = bankSystemUI.myPanel.getMoney(e);
		else if("存款".equals(itemstr))
			panel = bankSystemUI.myPanel.addMoney(e);
		else if("转账".equals(itemstr))
			panel = bankSystemUI.myPanel.transferMoney(e);
		else if("流水".equals(itemstr))
			panel = bankSystemUI.myPanel.showMoneyList(e);
		else if("退出系统".equals(itemstr))
			bankSystemUI.myPanel.exit(e);
		else 
			return;
		
		if(null != panel)bankSystemUI.myPanel.updateUI(panel);
	}
	
	



}

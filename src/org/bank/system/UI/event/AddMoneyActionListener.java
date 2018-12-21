package org.bank.system.UI.event;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bank.system.model.AccountList;
import org.bank.system.model.BankAccount;
import org.bank.system.service.AccountListService;
import org.bank.system.service.BankAccountService;

/**
 * 修改账户信息
 * @author jian
 *
 */
public class AddMoneyActionListener extends ButtonActionListener{
	
	public JTextField textField;//银行卡号
	public JTextField textField_2;//取款金额
	public JPasswordField jPasswordField;//密码
	
	public AddMoneyActionListener() {}
	public AddMoneyActionListener(JTextField textField,JTextField textField_2,JPasswordField jPasswordField) {
		this.textField = textField;
		this.textField_2 = textField_2;
		this.jPasswordField = jPasswordField;
	}
	
	public void actionPerformed(ActionEvent e) {
		BankAccount bankAccount = bankSystemUI.loginSuccessBankAccount;
		/*信息校验*/
		String bankAccountCard = textField_2.getText();
		String money = textField.getText();
		String bankAccountPassword = new String(jPasswordField.getPassword());
		if(null == bankAccountCard || !bankAccountCard.equals(bankAccount.getBankAccountCard())) {
			Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			JOptionPane.showOptionDialog(null, "银行卡卡号有误，请重新输入", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(image), null, null);
			return;
		}
		if(null == bankAccountPassword || !bankAccountPassword.equals(bankAccount.getBankAccountPassword())) {
			Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			JOptionPane.showOptionDialog(null, "密码有误，请重新输入", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(image), null, null);
			return;
		}
		
		/*判断金额是否不足*/
		String oldMoney = bankAccount.getBankAccountMoney();
		double oldMoneyValue = new Double(oldMoney);
		double moneyValue = new Double(money);
		double newMoneyValue = oldMoneyValue + moneyValue;
		String newMoney = Double.toString(newMoneyValue);
		bankAccount.setBankAccountMoney(newMoney);
		
		bankAccount = BankAccountService.update(bankAccount);
		bankSystemUI.loginSuccessBankAccount = bankAccount;//更新登录信息
		/*生成账户流水信息*/
		AccountList accountList = new AccountList(bankAccount.getBankAccountCode(), null, "0", Double.toString(moneyValue), "0");
		AccountListService.insert(accountList);
		
		/*重置数据*/
		this.textField.setText("");;
		this.textField_2.setText("");;
		this.jPasswordField.setText("");;
		bankSystemUI.myPanel.addMoney(e);
		JOptionPane.showOptionDialog(null, "存款完成！         ", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
	}

}

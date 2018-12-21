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
import org.bank.system.utils.StringUtils;

/**
 * 修改账户信息
 * @author jian
 *
 */
public class TransferMoneyActionListener extends ButtonActionListener{
	
	public JTextField textField;//银行卡号
	public JTextField textField_2;//取款金额
	public JTextField textField_3;//对方卡号
	public JPasswordField jPasswordField;//密码
	
	public TransferMoneyActionListener() {}
	public TransferMoneyActionListener(JTextField textField,JTextField textField_2,JTextField textField_3,JPasswordField jPasswordField) {
		this.textField = textField;
		this.textField_2 = textField_2;
		this.textField_3 = textField_3;
		this.jPasswordField = jPasswordField;
	}
	
	public void actionPerformed(ActionEvent e) {
		BankAccount bankAccount = bankSystemUI.loginSuccessBankAccount;
		/*信息校验*/
		String bankAccountCard = textField.getText();
		String money = textField_2.getText();
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
		
		/*判断对方账户是否存在*/
		BankAccount otherBankAccount = new BankAccount();
		otherBankAccount.setBankAccountCard(textField_3.getText());
		BankAccount oldOtherBankAccount = BankAccountService.queryOne(otherBankAccount);
		if(null == oldOtherBankAccount || StringUtils.isBlank(oldOtherBankAccount.getBankAccountCode())) {
			Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			JOptionPane.showOptionDialog(null, "对方账户不存在，请重新输入", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(image), null, null);
			return;
		}
		
		/*判断金额是否不足*/
		String oldMoney = bankAccount.getBankAccountMoney();
		String oldMoney2 = oldOtherBankAccount.getBankAccountMoney();
		double oldMoneyValue = new Double(oldMoney);
		double oldMoneyValue2 = new Double(oldMoney2);
		double moneyValue = new Double(money);
		double newMoneyValue = oldMoneyValue - moneyValue;
		String newMoney = Double.toString(newMoneyValue);
		double newMoneyValue2 = oldMoneyValue2 + moneyValue;
		String newMoney2 = Double.toString(newMoneyValue2);
		bankAccount.setBankAccountMoney(newMoney);
		oldOtherBankAccount.setBankAccountMoney(newMoney2);
		
		bankAccount = BankAccountService.update(bankAccount);
		bankAccount = BankAccountService.update(oldOtherBankAccount);
		bankSystemUI.loginSuccessBankAccount = bankAccount;//更新登录信息
		/*生成账户流水信息*/
		AccountList accountList = new AccountList(oldOtherBankAccount.getBankAccountCode(), bankAccount.getBankAccountCode(), "2", Double.toString(moneyValue), "0");
		AccountListService.insert(accountList);
		
		/*清空数据*/
		this.textField.setText("");;
		this.textField_2.setText("");;
		this.textField_3.setText("");;
		this.jPasswordField.setText("");;
		bankSystemUI.myPanel.transferMoney(e);
		JOptionPane.showOptionDialog(null, "转账完成！           ", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
	
	}

}

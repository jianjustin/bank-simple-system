package org.bank.system.UI.event;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bank.system.model.BankAccount;
import org.bank.system.service.BankAccountService;
import org.bank.system.utils.StringUtils;

public class LoginButtonActionListener extends ButtonActionListener{

	public JTextField jTextField;
	public JPasswordField jPasswordField;
	
	public LoginButtonActionListener(JTextField jTextField,JPasswordField jPasswordField) {
		this.jTextField = jTextField;
		this.jPasswordField = jPasswordField;
	}
	public LoginButtonActionListener() {}
	
	
	public void actionPerformed(ActionEvent e) {
		String username = jTextField.getText();
		String password = new String(jPasswordField.getPassword());
		
		/*校验是否为空*/
		if(StringUtils.isBlank(username)) {
			JOptionPane.showOptionDialog(null, "登录失败：用户名为空", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
			return;
		}
		
		if(StringUtils.isBlank(password)) {
			JOptionPane.showOptionDialog(null, "登录失败：密码为空", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
			return;
		}
		
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBankAccountName(username);
		bankAccount.setBankAccountPassword(password);
		BankAccount oldBankAccount = BankAccountService.login(bankAccount);
		if(StringUtils.isNotBlank(oldBankAccount.getBankAccountCode())) {
			/*存储当前用户信息*/
			bankSystemUI.loginSuccessBankAccount = oldBankAccount;
			/*登录成功，跳转主界面*/
			bankSystemUI.initMenuUI(e);
		}else {//登录失败
			JOptionPane.showOptionDialog(null, "登录失败：用户名或密码错误，请重新输入", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
		}
	}

}

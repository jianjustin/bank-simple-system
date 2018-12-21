package org.bank.system.UI.event;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bank.system.UI.BankSystemUI;
import org.bank.system.UI.UIContants;
import org.bank.system.model.BankAccount;
import org.bank.system.service.BankAccountService;
import org.bank.system.utils.StringUtils;

public class RegisterButtonActionListener extends ButtonActionListener{

	public JTextField textField;//姓名
	public JTextField textField_1;//身份证号码
	public JTextField textField_2;//银行卡号码
	public JPasswordField passwordField;//密码
	public JPasswordField passwordField_1;//再次输入密码
	
	public String bankAccountName;
	public String bankAccountIdCard;
	public String bankAccountCard;
	public String password1;
	public String password2;
	
	public RegisterButtonActionListener(JTextField textField,JTextField textField_1,JTextField textField_2,JPasswordField passwordField,JPasswordField passwordField_1) {
		this.textField = textField;
		this.textField_1 = textField_1;
		this.textField_2 = textField_2;
		this.passwordField = passwordField;
		this.passwordField_1 = passwordField_1;
	}
	public RegisterButtonActionListener() {}
	
	/**
	 * 注册
	 */
	public void actionPerformed(ActionEvent e) {
		/*数据获取*/
		this.bankAccountName = this.textField.getText();
		this.bankAccountIdCard = this.textField_1.getText();
		this.bankAccountCard = this.textField_2.getText();
		this.password1 = new String(this.passwordField.getPassword());
		this.password2 = new String(this.passwordField_1.getPassword());
		/*判断两次密码是否一致*/
		if(StringUtils.isBlank(password1)) {
			System.out.println("密码不能为空");
			return;
		}
		if(!password1.equals(password2)) {
			System.out.println("密码不一致，请重新输入");
			return;
		}
		
		/*数据校验(校验用户名，身份证和银行卡是否重复)*/
		BankAccount queryBankAccount = new BankAccount();
		queryBankAccount.setBankAccountName(this.bankAccountName);
		BankAccount queryBankAccount1 = new BankAccount();
		queryBankAccount1 = BankAccountService.queryOne(queryBankAccount);
		if(StringUtils.isNotBlank(queryBankAccount1.getBankAccountCode())) {
			JOptionPane.showOptionDialog(null, "注册失败：用户名重复", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
		    return;
		}
		queryBankAccount = new BankAccount();
		queryBankAccount.setBankAccountIdCard(this.bankAccountIdCard);
		queryBankAccount1 = new BankAccount();
		queryBankAccount1 = BankAccountService.queryOne(queryBankAccount);
		if(StringUtils.isNotBlank(queryBankAccount1.getBankAccountCode())) {
			JOptionPane.showOptionDialog(null, "注册失败：身份证重复", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
		    return;
		}
		queryBankAccount = new BankAccount();
		queryBankAccount.setBankAccountCard(this.bankAccountCard);;
		queryBankAccount1 = new BankAccount();
		queryBankAccount1 = BankAccountService.queryOne(queryBankAccount);
		if(StringUtils.isNotBlank(queryBankAccount1.getBankAccountCode())) {
			JOptionPane.showOptionDialog(null, "注册失败：银行卡号码重复", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
		    return;
		}
		
		/*账号注册逻辑*/
		BankAccount registerBankAccount = new BankAccount();
		registerBankAccount.setBankAccountCard(bankAccountCard);
		registerBankAccount.setBankAccountIdCard(bankAccountIdCard);
		registerBankAccount.setBankAccountName(bankAccountName);
		registerBankAccount.setBankAccountPassword(password1);
		
		/*数据插入*/
		BankAccount myBankAccount = BankAccountService.register(registerBankAccount);
		
		/*消息框*/
		this.textField.setText("");;
		this.textField_1.setText("");;
		this.textField_2.setText("");;
		this.passwordField.setText("");;
		this.passwordField_1.setText("");;
		JOptionPane.showOptionDialog(null, myBankAccount.getBankAccountName() + ",你已成功注册账号", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(bankSystemUI.image), null, null);
	}
	

}

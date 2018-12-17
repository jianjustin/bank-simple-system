package org.bank.system.UI.event;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import org.bank.system.model.BankAccount;
import org.bank.system.service.BankAccountService;

/**
 * 修改账户信息
 * @author jian
 *
 */
public class InformationChangedActionListener extends ButtonActionListener{
	
	public JTextField textField;//储户名称
	public JTextField textField_1;//用户银行卡号码
	public JTextField textField_2;//用户身份证
	public JTextField textField_3;//用户密码
	public JTextField textField_4;//
	
	public InformationChangedActionListener() {}
	public InformationChangedActionListener(JTextField textField,JTextField textField_1,JTextField textField_2,JTextField textField_3) {
		this.textField = textField;
		this.textField_1 = textField_1;
		this.textField_2 = textField_2;
		this.textField_3 = textField_3;
	}
	
	public InformationChangedActionListener(JTextField textField_4) {
		this.textField_4 = textField_4;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		BankAccount bankAccount = bankSystemUI.loginSuccessBankAccount;
		bankAccount.setBankAccountName(textField.getText());
		bankAccount.setBankAccountCard(textField_1.getText());
		bankAccount.setBankAccountIdCard(textField_2.getText());
		bankAccount.setBankAccountPassword(textField_3.getText());
		
		/*TODO 密码修改需要权限验证操作*/
		
		bankAccount = BankAccountService.update(bankAccount);
		bankSystemUI.loginSuccessBankAccount = bankAccount;//更新登录信息
	}

}

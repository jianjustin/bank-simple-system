package org.bank.system.UI.event;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.bank.system.UI.BankSystemUI;
import org.bank.system.UI.UIContants;
import org.bank.system.model.BankAccount;
import org.bank.system.service.BankAccountService;
import org.bank.system.utils.StringUtils;

public class RegisterButtonActionListener extends ButtonActionListener{

	public String bankAccountName;
	public String bankAccountIdCard;
	public String bankAccountCard;
	public String password1;
	public String password2;
	
	public RegisterButtonActionListener(String bankAccountName,String bankAccountIdCard,String bankAccountCard,String password1,String password2) {
		this.bankAccountCard = bankAccountCard;
		this.bankAccountIdCard = bankAccountIdCard;
		this.bankAccountName = bankAccountName;
		this.password1 = password1;
		this.password2 = password2;
	}
	public RegisterButtonActionListener() {}
	
	public void actionPerformed(ActionEvent e) {
		/*判断两次密码是否一致*/
		if(StringUtils.isBlank(password1))System.out.println("密码不能为空");
		if(!password1.equals(password2)) {
			System.out.println("密码不一致，请重新输入");
			return;
		}
		
		/*数据校验*/
		/*账号注册逻辑*/
		BankAccount registerBankAccount = new BankAccount();
		registerBankAccount.setBankAccountCard(bankAccountCard);
		registerBankAccount.setBankAccountIdCard(bankAccountIdCard);
		registerBankAccount.setBankAccountName(bankAccountName);
		registerBankAccount.setBankAccountPassword(password1);
		
		/*数据插入*/
		BankAccount myBankAccount = BankAccountService.register(registerBankAccount);
		JOptionPane jOptionPane = new JOptionPane();
		
		/*消息框*/
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("确认");
		/*注册成功返回登录页面*/
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel loginJPanel = bankSystemUI.myPanel.loginUI();
				FlowLayout flowLayout = new FlowLayout(1,UIContants.frame_width/2-UIContants.frame_login_panel_width/2,UIContants.frame_height/2-UIContants.frame_login_panel_height/2);
				bankSystemUI.myPanel.updateUI(loginJPanel, flowLayout, UIContants.frame_width, UIContants.frame_height, null);
			}
		});
		Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		JOptionPane.showOptionDialog(null, "你已成功注册平台账户,请点击确定返回登录页面.", "消息",JOptionPane.PLAIN_MESSAGE, 0, new ImageIcon(image), buttons, buttons[0]);	
	}

}

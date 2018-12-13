package org.bank.system.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 银行账户表
 * @author chenjianrui
 * @since 2018-12-09
 */
public class BankAccount {
	
	public static Map<String,String> objToModelMap = new HashMap<String,String>();
	public static Map<String,String> modelToObjMap = new HashMap<String,String>();
	
	static {
		objToModelMap.put("pkId", "PK_ID");
		objToModelMap.put("bankAccountCode", "BANK_ACCOUNT_CODE");
		objToModelMap.put("bankAccountCard", "BANK_ACCOUNT_CARD");
		objToModelMap.put("bankAccountPassword", "BANK_ACCOUNT_PASSWORD");
		objToModelMap.put("bankAccountName", "BANK_ACCOUNT_NAME");
		objToModelMap.put("bankAccountIdCard", "BANK_ACCOUNT_ID_CARD");
		objToModelMap.put("bankAccountMoney", "BANK_ACCOUNT_MONEY");
		objToModelMap.put("bankAccountCreateTime", "BANK_ACCOUNT_CREATE_TIME");
		objToModelMap.put("bankAccountUpdateTime", "BANK_ACCOUNT_UPDATE_TIME");
		
		modelToObjMap.put("PK_ID", "PK_ID");
		modelToObjMap.put("BANK_ACCOUNT_CODE", "bankAccountCode");
		modelToObjMap.put("BANK_ACCOUNT_CARD", "bankAccountCard");
		modelToObjMap.put("BANK_ACCOUNT_PASSWORD", "bankAccountPssword");
		modelToObjMap.put("BANK_ACCOUNT_NAME", "bankAccountName");
		modelToObjMap.put("BANK_ACCOUNT_ID_CARD", "bankAccountIdCard");
		modelToObjMap.put("BANK_ACCOUNT_MONEY", "bankAccountMoney");
		modelToObjMap.put("BANK_ACCOUNT_CREATE_TIME", "bankAccountCreateTime");
		modelToObjMap.put("BANK_ACCOUNT_UPDATE_TIME", "bankAccountUpdateTime");
		
	}

    /**
     * 主键ID
     */
    public BigDecimal pkId;
    /**
     * 账户编号
     */
    public String bankAccountCode;
    /**
     * 账户卡号
     */
    public String bankAccountCard;
    /**
     * 账户卡号
     */
    public String bankAccountPassword;
	/**
     * 储户名称
     */
    public String bankAccountName;
    /**
     * 储户身份证好嘛
     */
    public String bankAccountIdCard;
    /**
     * 账户余额
     */
    public String bankAccountMoney;
    /**
     * 账户创建时间
     */
    public Date bankAccountCreateTime;
    /**
     * 账户最后更新时间
     */
    public Date bankAccountUpdateTime;

    public BigDecimal getPkId() {
        return pkId;
    }

    public void setPkId(BigDecimal pkId) {
        this.pkId = pkId;
    }

    public String getBankAccountCode() {
        return bankAccountCode;
    }

    public void setBankAccountCode(String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }

    public String getBankAccountCard() {
        return bankAccountCard;
    }

    public void setBankAccountCard(String bankAccountCard) {
        this.bankAccountCard = bankAccountCard;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountIdCard() {
        return bankAccountIdCard;
    }

    public void setBankAccountIdCard(String bankAccountIdCard) {
        this.bankAccountIdCard = bankAccountIdCard;
    }

    public String getBankAccountMoney() {
        return bankAccountMoney;
    }

    public void setBankAccountMoney(String bankAccountMoney) {
        this.bankAccountMoney = bankAccountMoney;
    }

    public Date getBankAccountCreateTime() {
        return bankAccountCreateTime;
    }

    public void setBankAccountCreateTime(Date bankAccountCreateTime) {
        this.bankAccountCreateTime = bankAccountCreateTime;
    }

    public Date getBankAccountUpdateTime() {
        return bankAccountUpdateTime;
    }

    public void setBankAccountUpdateTime(Date bankAccountUpdateTime) {
        this.bankAccountUpdateTime = bankAccountUpdateTime;
    }
    public String getBankAccountPassword() {
		return bankAccountPassword;
	}

	public void setBankAccountPassword(String bankAccountPassword) {
		this.bankAccountPassword = bankAccountPassword;
	}
}

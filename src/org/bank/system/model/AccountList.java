package org.bank.system.model;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 银行账户流水
 * @author chenjianrui
 * @since 2018-12-09
 */
public class AccountList {

	public static Map<String,String> objToModelMap = new HashMap<String,String>();
	public static Map<String,String> modelToObjMap = new HashMap<String,String>();
	
	static {
		objToModelMap.put("pkId", "PK_ID");
		objToModelMap.put("accountListCode", "ACCOUNT_LIST_CODE");
		objToModelMap.put("accountListInCode", "ACCOUNT_LIST_IN_CODE");
		objToModelMap.put("accountListOutCode", "ACCOUNT_LIST_OUT_CODE");
		objToModelMap.put("accountListType", "ACCOUNT_LIST_TYPE");
		objToModelMap.put("accountListMoney", "ACCOUNT_LIST_MONEY");
		objToModelMap.put("accountListStatus", "ACCOUNT_LIST_STATUS");
		objToModelMap.put("accountListCreateDate", "ACCOUNT_LIST_CREATE_DATE");
		objToModelMap.put("accountListUpdateDate", "ACCOUNT_LIST_UPDATE_DATE");
		
		modelToObjMap.put("PK_ID", "PK_ID");
		modelToObjMap.put("ACCOUNT_LIST_CODE", "accountListCode");
		modelToObjMap.put("ACCOUNT_LIST_IN_CODE", "accountListInCode");
		modelToObjMap.put("ACCOUNT_LIST_OUT_CODE", "accountListOutCode");
		modelToObjMap.put("ACCOUNT_LIST_TYPE", "accountListType");
		modelToObjMap.put("ACCOUNT_LIST_MONEY", "accountListMoney");
		modelToObjMap.put("ACCOUNT_LIST_STATUS", "accountListStatus");
		modelToObjMap.put("ACCOUNT_LIST_CREATE_DATE", "accountListCreateDate");
		modelToObjMap.put("ACCOUNT_LIST_UPDATE_DATE", "accountListUpdateDate");
		
	}
	
    /**
     * 主键ID
     */
    public BigDecimal pkId;
    /**
     * 流水编号
     */
    public String accountListCode;
    /**
     * 入金账号编号
     */
    public String accountListInCode;
    /**
     * 出金账号
     */
    public String accountListOutCode;
    /**
     * 流水类型
     */
    public String accountListType;
    /**
     * 流水金额
     */
    public String accountListMoney;
    /**
     * 流水状态
     */
    public String accountListStatus;
    /**
     * 流水创建时间
     */
    public Date accountListCreateDate;
    /**
     * 流水更新时间
     */
    public Timestamp accountListUpdateDate;

    public BigDecimal getPkId() {
        return pkId;
    }

    public void setPkId(BigDecimal pkId) {
        this.pkId = pkId;
    }

    public String getAccountListCode() {
        return accountListCode;
    }

    public void setAccountListCode(String accountListCode) {
        this.accountListCode = accountListCode;
    }

    public String getAccountListInCode() {
        return accountListInCode;
    }

    public void setAccountListInCode(String accountListInCode) {
        this.accountListInCode = accountListInCode;
    }

    public String getAccountListOutCode() {
        return accountListOutCode;
    }

    public void setAccountListOutCode(String accountListOutCode) {
        this.accountListOutCode = accountListOutCode;
    }

    public String getAccountListType() {
        return accountListType;
    }

    public void setAccountListType(String accountListType) {
        this.accountListType = accountListType;
    }

    public String getAccountListMoney() {
        return accountListMoney;
    }

    public void setAccountListMoney(String accountListMoney) {
        this.accountListMoney = accountListMoney;
    }

    public String getAccountListStatus() {
        return accountListStatus;
    }

    public void setAccountListStatus(String accountListStatus) {
        this.accountListStatus = accountListStatus;
    }

    public Date getAccountListCreateDate() {
        return accountListCreateDate;
    }

    public void setAccountListCreateDate(Date accountListCreateDate) {
        this.accountListCreateDate = accountListCreateDate;
    }

    public Timestamp getAccountListUpdateDate() {
        return accountListUpdateDate;
    }

    public void setAccountListUpdateDate(Timestamp accountListUpdateDate) {
        this.accountListUpdateDate = accountListUpdateDate;
    }
}

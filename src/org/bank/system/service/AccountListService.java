package org.bank.system.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.bank.system.database.BankAccountAPI;
import org.bank.system.model.BankAccount;
import org.bank.system.utils.SqlUtils;

/**
 * 账号注册(组装数据库操作)
 * @author jian
 *
 */
public class AccountListService {
	
	public static BankAccountAPI bankAccountAPI = new BankAccountAPI();
	
	/**
	 * 用户注册
	 * @param bankAccount
	 * @return
	 */
	public static BankAccount register(BankAccount bankAccount) {
		bankAccount.setBankAccountMoney("0.00");
		String bankAccountCode = bankAccountAPI.insert(bankAccount);
		bankAccount.setBankAccountCode(bankAccountCode);
		/*根据用户编号进行查询*/
		BankAccount newBankAccount = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap1(SqlUtils.resultSetToMap(bankAccountAPI.queryOne(bankAccount)).get(0)),BankAccount.class);
		return newBankAccount;
	}
	
	/**
	 * 用户登录
	 * @param bankAccount
	 * @return
	 */
	public static BankAccount login(BankAccount bankAccount) {
		//根据账号密码进行查询
		ResultSet resultSet = bankAccountAPI.queryAll(bankAccount);
		/*resultSet转list*/
		List<Map<String,Object>> mapList = SqlUtils.resultSetToMap(resultSet);
		BankAccount oldBankAccount = new BankAccount();
		if(null == mapList || mapList.size() == 0)return oldBankAccount;
		oldBankAccount = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap1(mapList.get(0)),BankAccount.class);
		return oldBankAccount;
	}

}

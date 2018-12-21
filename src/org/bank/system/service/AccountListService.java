package org.bank.system.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bank.system.database.AccountListAPI;
import org.bank.system.database.BankAccountAPI;
import org.bank.system.model.AccountList;
import org.bank.system.model.BankAccount;
import org.bank.system.utils.SqlUtils;

/**
 * 账号注册(组装数据库操作)
 * @author jian
 *
 */
public class AccountListService {
	
	public static AccountListAPI accountListAPI = new AccountListAPI();
	
	/**
	 * 插入流水
	 * @param bankAccount
	 * @return
	 */
	public static AccountList insert(AccountList accountList) {
		String accountListCode = accountListAPI.insert(accountList);
		accountList.setAccountListCode(accountListCode);
		/*根据流水编号进行查询*/
		AccountList newAccountList = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap1(SqlUtils.resultSetToMap(accountListAPI.queryOne(accountList)).get(0)),AccountList.class);
		return newAccountList;
	}
	
	/**
	 * 查询流水
	 * @param bankAccount
	 * @return
	 */
	public static List<AccountList> queryAll(AccountList accountList) {
		//根据账号密码进行查询
		ResultSet resultSet = accountListAPI.queryAll(accountList);
		/*resultSet转list*/
		List<Map<String,Object>> mapList = SqlUtils.resultSetToMap(resultSet);
		List<AccountList> list = new ArrayList<AccountList>();
		if(null == mapList || mapList.size() == 0)return list;
		for (int i = 0; i < mapList.size(); i++) {
			AccountList oldAccountList = new AccountList();
			oldAccountList = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap1(mapList.get(i)),AccountList.class);
			list.add(oldAccountList);
		}
		return list;
	}

}

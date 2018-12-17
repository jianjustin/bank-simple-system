package org.bank.system.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bank.system.model.BankAccount;
import org.bank.system.utils.SqlUtils;
import org.bank.system.utils.StringUtils;


/**
 * 银行账户操作类API
 */
public class BankAccountAPI {





//-------------------基本操作(CURD)--------------------
  public ResultSet queryOne(BankAccount bankAccount){
	  Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = SqlPoolFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			List<Object> paramValueList = new ArrayList<Object>();
			
			String queryTableSQL = "select * from "+Contants.mySqlSchema+".bank_account"
								 + " where 1=1 and BANK_ACCOUNT_CODE = ?"; 
			System.out.println("执行SQL："+queryTableSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(queryTableSQL);
			preparedStatement.setString(1, bankAccount.getBankAccountCode());
			resultSet = preparedStatement.executeQuery();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			SqlPoolFactory.returnConnection(connection);
		}
		return resultSet;
  }
  
  /**
   * 根据查询条件查询所有符合条件数据
   * @param bankAccount
   * @return
   */
  public ResultSet queryAll(BankAccount bankAccount){
	Statement statement = null;
	ResultSet resultSet = null;
	Connection connection = SqlPoolFactory.getConnection();
	try {
		connection.setAutoCommit(false);
		List<Object> paramValueList = new ArrayList<Object>();
		
		String queryTableSQL = "select * from "+Contants.mySqlSchema+".bank_account"
							 + " where "; 
		queryTableSQL += SqlUtils.getModleToWhereSql("", bankAccount, null, null, paramValueList, BankAccount.objToModelMap);
		System.out.println("执行SQL："+queryTableSQL);
		PreparedStatement preparedStatement = connection.prepareStatement(queryTableSQL);
		for (int i = 0; i < paramValueList.size(); i++) {
			preparedStatement.setObject(i+1, paramValueList.get(i));
		}
		resultSet = preparedStatement.executeQuery();
		connection.commit();
	} catch (SQLException e) {
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}finally {
		SqlPoolFactory.returnConnection(connection);
	}
	return resultSet;
  }
  
  /**
   * 
   * @param bankAccount
   * @param offset -- 偏移量(其实位置)
   * @param limit --- 分页量
   * @return
   */
  public ResultSet queryByPage(BankAccount bankAccount,int offset,int limit){
	  Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = SqlPoolFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			List<Object> paramValueList = new ArrayList<Object>();
			
			String queryTableSQL = "select * from "+Contants.mySqlSchema+".bank_account"
								 + " where "; 
			queryTableSQL += SqlUtils.getModleToWhereSql("", bankAccount, null, null, paramValueList, BankAccount.objToModelMap);
			//queryTableSQL += SqlUtils.getLimitParam(offset, limit);
			System.out.println("执行SQL："+queryTableSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(queryTableSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			preparedStatement.setMaxFieldSize(offset+limit);//最大数据位置
			for (int i = 0; i < paramValueList.size(); i++) {
				preparedStatement.setObject(i+1, paramValueList.get(i));
			}
//			if(resultSet.next()){
//				resultSet = preparedStatement.executeQuery();
//				resultSet.relative(offset);
//			}
			resultSet = preparedStatement.executeQuery();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			SqlPoolFactory.returnConnection(connection);
		}
		return resultSet;
  }
  
  /**
   * 插入数据
   * @param bankAccount
   * @return
   */
  public String insert(BankAccount bankAccount) {
	 Statement statement = null;
	 Connection connection = SqlPoolFactory.getConnection();
	 bankAccount.setBankAccountCode(getNextCode("bank_account","BANK_ACCOUNT_CODE"));
	 try {
		 
		 connection.setAutoCommit(false);
		String insertTableSQL = "INSERT INTO "+Contants.mySqlSchema+".bank_account"
							  + "(BANK_ACCOUNT_CODE, BANK_ACCOUNT_CARD, BANK_ACCOUNT_NAME, BANK_ACCOUNT_ID_CARD,BANK_ACCOUNT_PASSWORD, BANK_ACCOUNT_MONEY) VALUES"
							  + "(?,?,?,?,?,?)";
		
		System.out.println("执行SQL："+insertTableSQL);
		PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
		//参数设置
		preparedStatement.setString(1, bankAccount.getBankAccountCode());
		preparedStatement.setString(2, bankAccount.getBankAccountCard());
		preparedStatement.setString(3, bankAccount.getBankAccountName());
		preparedStatement.setString(4, bankAccount.getBankAccountIdCard());
		preparedStatement.setString(5, bankAccount.getBankAccountPassword());
		preparedStatement.setString(6, bankAccount.getBankAccountMoney());
		
		preparedStatement .executeUpdate();
		connection.commit();
		
	} catch (SQLException e) {
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}finally {
		SqlPoolFactory.returnConnection(connection);
	}
	 return bankAccount.getBankAccountCode();
  }
  
  /**
   * 更新数据
   * @param bankAccount
   * @return
   */
  public int update(BankAccount bankAccount) {
		 Statement statement = null;
		 int result = 0;
		 Connection connection = SqlPoolFactory.getConnection();
		 try {
			connection.setAutoCommit(false);
			String updateTableSQL = "update "+Contants.mySqlSchema+".bank_account set"
								  + " BANK_ACCOUNT_CARD = ?,"
								  + " BANK_ACCOUNT_NAME = ?,"
								  + " BANK_ACCOUNT_ID_CARD = ?,"
								  + " BANK_ACCOUNT_PASSWORD = ?,"
								  + " BANK_ACCOUNT_MONEY = ?"
								  + " where BANK_ACCOUNT_CODE = ?";
			System.out.println("执行SQL："+updateTableSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
			//参数设置
			preparedStatement.setString(1, bankAccount.getBankAccountCard());
			preparedStatement.setString(2, bankAccount.getBankAccountName());
			preparedStatement.setString(3, bankAccount.getBankAccountIdCard());
			preparedStatement.setString(4, bankAccount.getBankAccountPassword());
			preparedStatement.setString(5, bankAccount.getBankAccountMoney());
			preparedStatement.setString(6, bankAccount.getBankAccountCode());
			
			result = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			SqlPoolFactory.returnConnection(connection);
		}
		 return result;
	  }
  
  /**
   * 删除数据
   * @param bankAccount
   * @return
   */
  public ResultSet delete(BankAccount bankAccount) {
		 Statement statement = null;
		 ResultSet resultSet = null;
		 Connection connection = SqlPoolFactory.getConnection();
		 try {
			connection.setAutoCommit(false);
			String deleteTableSQL = "delete from "+Contants.mySqlSchema+".bank_account "
								  + " where BANK_ACCOUNT_CODE = ?";
			
			System.out.println("执行SQL："+deleteTableSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL);
			//参数设置
			preparedStatement.setString(1, bankAccount.getBankAccountCode());
			
			int i = preparedStatement.executeUpdate();
			connection.commit();
			if(i>0)
				System.out.println("数据删除成功");
			else
				System.out.println("数据删除失败");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			SqlPoolFactory.returnConnection(connection);
		}
		 return resultSet;
	  }
  
  /**
   * 获取code最大值
   * @param tableName
   * @param columnName
   * @return
   */
  public String getNextCode(String tableName,String columnName) {
	    Statement statement = null;
		ResultSet resultSet = null;
		String result = "00000000";
		Connection connection = SqlPoolFactory.getConnection();
		try {
			statement = connection.createStatement();
			statement.executeQuery("select max("+columnName+") as max_code from "+Contants.mySqlSchema+"."+tableName);
			resultSet = statement.getResultSet();
			if(!resultSet.next())return result;
			result = resultSet.getString("max_code");
			if(StringUtils.isBlank(result))result = "00000000";
			result = String.format("%08d", (new Integer(result)+1));
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlPoolFactory.returnConnection(connection);
		}
		return result;
  }
  
  public static void main(String[] args) {
	  //TEST BEGIN
	  
	  BankAccountAPI bankAccountAPI = new BankAccountAPI();
	  /*1. INSERT*/
//	  BankAccount insertBankAccount = new BankAccount();
//	  insertBankAccount.setBankAccountCode("00000001");
//	  insertBankAccount.setBankAccountName("蓟份号");
//	  insertBankAccount.setBankAccountCard("6222020903001483077");
//	  insertBankAccount.setBankAccountIdCard("420101199104178366");
//	  insertBankAccount.setBankAccountPassword("123456");
//	  insertBankAccount.setBankAccountMoney("0.00");
//	  bankAccountAPI.insert(insertBankAccount);
	  
	  /*4. queryOne*/
	  BankAccount bankAccount = new BankAccount();
	  bankAccount.setBankAccountCode("00000001");
	  ResultSet resultSet = bankAccountAPI.queryOne(bankAccount);
	  List<Map<String,Object>> list = SqlUtils.resultSetToMap(resultSet);
	  System.out.println(list);
	  
	  /*2. UPDATE*/
//	  bankAccount = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap1(list.get(0)), BankAccount.class);
//	  bankAccount.setBankAccountPassword("12345678");
//	  bankAccountAPI.update(bankAccount);
	  
	  /*2. DELETE*/
//	  bankAccount = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap1(list.get(0)), BankAccount.class);
//	  bankAccountAPI.delete(bankAccount);
	  
	  /*4. query*/
	  bankAccount = new BankAccount();
	  bankAccount.setBankAccountCode("00000001");
	  resultSet = bankAccountAPI.queryByPage(bankAccount,0,10);
	  list = SqlUtils.resultSetToMap(resultSet);
	  System.out.println(list);
	  
	  /*4. queryAll*/
	  bankAccount = new BankAccount();
	  resultSet = bankAccountAPI.queryAll(bankAccount);
	  list = SqlUtils.resultSetToMap(resultSet);
	  System.out.println(list);
	  
	  //TEST END
  }

}

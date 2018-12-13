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
		Connection connection = BasicSqlFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			List<Object> paramValueList = new ArrayList<Object>();
			
			String queryTableSQL = "select * from bankSystemDb.dbo.bank_account"
								 + " where 1=1 and BANK_ACCOUNT_CODE = ?"; 
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
	Connection connection = BasicSqlFactory.getConnection();
	try {
		connection.setAutoCommit(false);
		List<Object> paramValueList = new ArrayList<Object>();
		
		String queryTableSQL = "select * from bankSystemDb.dbo.bank_account"
							 + " where "; 
		queryTableSQL += SqlUtils.getModleToWhereSql("", bankAccount, null, null, paramValueList, BankAccount.objToModelMap);
		System.out.println(queryTableSQL);
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
		Connection connection = BasicSqlFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			List<Object> paramValueList = new ArrayList<Object>();
			
			String queryTableSQL = "select * from bankSystemDb.dbo.bank_account"
								 + " where "; 
			queryTableSQL += SqlUtils.getModleToWhereSql("", bankAccount, null, null, paramValueList, BankAccount.objToModelMap);
			//queryTableSQL += SqlUtils.getLimitParam(offset, limit);
			System.out.println(queryTableSQL);
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
	 Connection connection = BasicSqlFactory.getConnection();
	 bankAccount.setBankAccountCode(getNextCode("bankSystemDb.dbo.bank_account","BANK_ACCOUNT_CODE"));
	 try {
		 
		 connection.setAutoCommit(false);
		String insertTableSQL = "INSERT INTO bankSystemDb.dbo.bank_account"
							  + "(BANK_ACCOUNT_CODE, BANK_ACCOUNT_CARD, BANK_ACCOUNT_NAME, BANK_ACCOUNT_ID_CARD,BANK_ACCOUNT_PASSWORD, BANK_ACCOUNT_MONEY) VALUES"
							  + "(?,?,?,?,?,?)";
		
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
	}
	 return bankAccount.getBankAccountCode();
  }
  
  public ResultSet update(BankAccount bankAccount) {
		 Statement statement = null;
		 ResultSet resultSet = null;
		 Connection connection = BasicSqlFactory.getConnection();
		 try {
			connection.setAutoCommit(false);
			String insertTableSQL = "update bankSystemDb.dbo.bank_account set"
								  + " BANK_ACCOUNT_CARD = ?,"
								  + " BANK_ACCOUNT_NAME = ?,"
								  + " BANK_ACCOUNT_ID_CARD = ?,"
								  + " BANK_ACCOUNT_PASSWOED = ?,"
								  + " BANK_ACCOUNT_MONEY = ?"
								  + " where BANK_ACCOUNT_CODE = ?";
			System.out.println(insertTableSQL);
			PreparedStatement preparedStatement = BasicSqlFactory.getConnection().prepareStatement(insertTableSQL);
			//参数设置
			preparedStatement.setString(1, bankAccount.getBankAccountCard());
			preparedStatement.setString(2, bankAccount.getBankAccountName());
			preparedStatement.setString(3, bankAccount.getBankAccountIdCard());
			preparedStatement.setString(4, bankAccount.getBankAccountPassword());
			preparedStatement.setString(5, bankAccount.getBankAccountMoney());
			preparedStatement.setString(6, bankAccount.getBankAccountCode());
			
			preparedStatement .executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		 return resultSet;
	  }
  
  public ResultSet delete(BankAccount bankAccount) {
		 Statement statement = null;
		 ResultSet resultSet = null;
		 Connection connection = BasicSqlFactory.getConnection();
		 try {
			connection.setAutoCommit(false);
			String deleteTableSQL = "delete bankSystemDb.dbo.bank_account "
								  + " where BANK_ACCOUNT_CODE = ?";
			
			System.out.println("执行SQL："+deleteTableSQL);
			PreparedStatement preparedStatement = BasicSqlFactory.getConnection().prepareStatement(deleteTableSQL);
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
		try {
			statement = BasicSqlFactory.getConnection().createStatement();
			statement.executeQuery("select max("+columnName+") as max_code from "+tableName);
			resultSet = statement.getResultSet();
			if(!resultSet.next())return result;
			result = resultSet.getString("max_code");
			if(StringUtils.isNotBlank(result))
			  result = String.format("%08d", (new Integer(result)+1));;
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
  }
  
  public static void main(String[] args) {
	  BankAccount bankAccount = new BankAccount();
	  bankAccount.setBankAccountCode("00000000");
	  BankAccountAPI bankAccountAPI = new BankAccountAPI();
	  ResultSet resultSet = bankAccountAPI.queryByPage(bankAccount,0,10);
	  List<Map<String,Object>> list = SqlUtils.resultSetToMap(resultSet);
	  System.out.println(list);
  }

}

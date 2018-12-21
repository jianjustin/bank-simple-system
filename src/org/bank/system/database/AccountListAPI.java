package org.bank.system.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bank.system.model.AccountList;
import org.bank.system.model.BankAccount;
import org.bank.system.utils.SqlUtils;
import org.bank.system.utils.StringUtils;

/**
 * 账户流水API操作类
 * @author chenjianrui
 *
 */
public class AccountListAPI {
	


//-------------------基本操作(CURD)--------------------
	  public ResultSet queryOne(AccountList accountList){
		  Statement statement = null;
			ResultSet resultSet = null;
			Connection connection = SqlPoolFactory.getConnection();
			try {
				connection.setAutoCommit(false);
				List<Object> paramValueList = new ArrayList<Object>();
				
				String queryTableSQL = "select * from "+Contants.mySqlSchema+".account_list"
									 + " where 1=1 and ACCOUNT_LIST_CODE = ?"; 
				PreparedStatement preparedStatement = connection.prepareStatement(queryTableSQL);
				preparedStatement.setString(1, accountList.getAccountListCode());
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
	  public ResultSet queryAll(AccountList accountList){
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = SqlPoolFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			List<Object> paramValueList = new ArrayList<Object>();
			
			String queryTableSQL = "select * from "+Contants.mySqlSchema+".account_list"
								 + " where "; 
			queryTableSQL += SqlUtils.getModleToWhereSql("", accountList, null, null, paramValueList, AccountList.objToModelMap);
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
	  public ResultSet queryByPage(AccountList accountList,int offset,int limit){
		  Statement statement = null;
			ResultSet resultSet = null;
			Connection connection = SqlPoolFactory.getConnection();
			try {
				connection.setAutoCommit(false);
				List<Object> paramValueList = new ArrayList<Object>();
				
				String queryTableSQL = "select * from "+Contants.mySqlSchema+".account_list"
									 + " where "; 
				queryTableSQL += SqlUtils.getModleToWhereSql("", accountList, null, null, paramValueList, AccountList.objToModelMap);
				//queryTableSQL += SqlUtils.getLimitParam(offset, limit);
				System.out.println("执行SQL："+queryTableSQL);
				PreparedStatement preparedStatement = connection.prepareStatement(queryTableSQL);
				preparedStatement.setMaxFieldSize(offset+limit);//最大数据位置
				for (int i = 0; i < paramValueList.size(); i++) {
					preparedStatement.setObject(i+1, paramValueList.get(i));
				}
				resultSet = preparedStatement.executeQuery();
				//resultSet.relative(offset);
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
	  public String insert(AccountList accountList) {
		 Statement statement = null;
		 Connection connection = SqlPoolFactory.getConnection();
		 accountList.setAccountListCode(getNextCode("account_list","ACCOUNT_LIST_CODE"));
		 try {
			 
			 connection.setAutoCommit(false);
			String insertTableSQL = "INSERT INTO "+Contants.mySqlSchema+".account_list"
								  + "(ACCOUNT_LIST_CODE, ACCOUNT_LIST_IN_CODE, ACCOUNT_LIST_OUT_CODE, ACCOUNT_LIST_TYPE, ACCOUNT_LIST_MONEY, ACCOUNT_LIST_STATUS) VALUES"
								  + "(?,?,?,?,?,?)";
			
			System.out.println("执行SQL："+insertTableSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
			//参数设置
			preparedStatement.setString(1, accountList.getAccountListCode());
			preparedStatement.setString(2, accountList.getAccountListInCode());
			preparedStatement.setString(3, accountList.getAccountListOutCode());
			preparedStatement.setString(4, accountList.getAccountListType());
			preparedStatement.setString(5, accountList.getAccountListMoney());
			preparedStatement.setString(6, accountList.getAccountListStatus());
			
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
		 return accountList.getAccountListCode();
	  }
	  
	  public AccountList update(AccountList accountList) {
			 Statement statement = null;
			 Connection connection = SqlPoolFactory.getConnection();
			 try {
				connection.setAutoCommit(false);
				String updateTableSQL = "update "+Contants.mySqlSchema+".account_list set"
									  + " ACCOUNT_LIST_IN_CODE = ?,"
									  + " ACCOUNT_LIST_OUT_CODE = ?,"
									  + " ACCOUNT_LIST_TYPE = ?,"
									  + " ACCOUNT_LIST_MONEY = ?,"
									  + " ACCOUNT_LIST_STATUS = ?"
									  + " where ACCOUNT_LIST_CODE = ?";
				
				System.out.println("执行SQL："+updateTableSQL);
				PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
				//参数设置
				preparedStatement.setString(1, accountList.getAccountListInCode());
				preparedStatement.setString(2, accountList.getAccountListOutCode());
				preparedStatement.setString(3, accountList.getAccountListType());
				preparedStatement.setString(4, accountList.getAccountListMoney());
				preparedStatement.setString(5, accountList.getAccountListStatus());
				preparedStatement.setString(6, accountList.getAccountListCode());
				
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
			 return accountList;
		  }
	  
	  /**
	   * 账户流水删除
	   * @param accountList
	   * @return
	   */
	  public ResultSet delete(AccountList accountList) {
			 Statement statement = null;
			 ResultSet resultSet = null;
			 Connection connection = SqlPoolFactory.getConnection();
			 try {
				connection.setAutoCommit(false);
				String deleteTableSQL = "delete from "+Contants.mySqlSchema+".account_list "
									  + " where ACCOUNT_LIST_CODE = ?";
				
				System.out.println("执行SQL："+deleteTableSQL);
				PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL);
				//参数设置
				preparedStatement.setString(1, accountList.getAccountListCode());
				
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
				statement.executeQuery("select max("+ columnName+") as max_code from "+Contants.mySqlSchema + "."+tableName);
				resultSet = statement.getResultSet();
				if(!resultSet.next())return result;
				result = resultSet.getString("max_code");
				if(StringUtils.isBlank(result))result = "00000000";
				result = String.format("%08d", (new Integer(result)+1));;
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
		  
		  AccountListAPI accountListAPI = new AccountListAPI();
		  /*1. INSERT*/
//		  AccountList insertAccountList = new AccountList();
//		  insertAccountList.setAccountListType("0");
//		  insertAccountList.setAccountListMoney("100");
//		  insertAccountList.setAccountListStatus("0");
//		  accountListAPI.insert(insertAccountList);
		  
		  /*4. queryOne*/
		  AccountList accountList = new AccountList();
		  accountList.setAccountListCode("00000001");
		  ResultSet resultSet = accountListAPI.queryOne(accountList);
		  List<Map<String,Object>> list = SqlUtils.resultSetToMap(resultSet);
		  System.out.println(list);
		  
		  /*2. UPDATE*/
//		  accountList = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap2(list.get(0)), AccountList.class);
//		  accountList.setAccountListMoney("1001");
//		  accountListAPI.update(accountList);
		  
		  /*2. DELETE*/
		  accountList = SqlUtils.mapToObject(SqlUtils.columnMapToObjMap2(list.get(0)), AccountList.class);
		  accountListAPI.delete(accountList);
//		  
		  /*4. query*/
		  accountList = new AccountList();
		  accountList.setAccountListCode("00000001");;
		  resultSet = accountListAPI.queryByPage(accountList,0,10);
		  list = SqlUtils.resultSetToMap(resultSet);
		  System.out.println(list);
		  
		  /*4. queryAll*/
		  accountList = new AccountList();
		  resultSet = accountListAPI.queryAll(accountList);
		  list = SqlUtils.resultSetToMap(resultSet);
		  System.out.println(list);
		  
		  //TEST END
	  }

}

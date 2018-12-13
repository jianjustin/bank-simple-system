package org.bank.system.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

/**
 * 数据库基础类
 */
public class BasicSqlFactory {
	
	public static Connection connection;

	/**
	 * 获取默认数据库连接
	 * @return
	 */
    public static Connection getConnection(){
    	if(null == connection)connection = BasicSqlFactory.getSqlServerConnection();
		return connection;

    }

    /**
     * 获取指定数据库连接
     * @param databaseType
     * @return
     */
    public static Connection getConnection(String databaseType){
        if(null == connection && Contants.mysqlType.equals(databaseType))
        	connection = BasicSqlFactory.getMySqlConnection();
        else if(null == connection)
        	connection = BasicSqlFactory.getConnection();
        return connection;
    }
    
    /**
     * 获取sqlserver数据库连接
     * @return
     */
    public static Connection getSqlServerConnection() {
    	 if(null != connection)return connection;
         try {
             DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
             connection = DriverManager.getConnection(Contants.sqlserverUrl, Contants.sqlserverUsername, Contants.sqlserverPassword);
         }catch (Exception e) {
             e.printStackTrace();
         }
         if(null != connection)
             System.out.println("连接数据库成功");
         return connection;
    }
    
    /**
     * 获取mysql数据库连接
     * @return
     */
    public static Connection getMySqlConnection() {
    	 if(null != connection)return connection;
         try {
             DriverManager.registerDriver(new Driver());
             connection = DriverManager.getConnection(Contants.mysqlUrl, Contants.mysqlUsername, Contants.mysqlPassword);
         }catch (Exception e) {
             e.printStackTrace();
         }
         if(null != connection)
             System.out.println("连接数据库成功");
         return connection;
    }

}

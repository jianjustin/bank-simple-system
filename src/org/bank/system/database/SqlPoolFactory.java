package org.bank.system.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接代理工厂
 * 1. 初始化连接池
 * 2. 获取连接
 * 3. 释放连接
 * @author chenjianrui
 *
 */
public class SqlPoolFactory {
	
	public static List<Connection> connectionList;/*数据库连接池*/
	public static List<Connection> freeConnectionList;/*数据库连接池*/
	public static int initConnectionSize = 2;/*初始化连接数*/
	public static int freeConnectionSize = initConnectionSize;/*可用连接数*/
	public static int maxConnectionSize = 100;/*最大连接数*/
	
	
	static {
		SqlPoolFactory.initConnectionSize = 2;
		//SqlPoolFactory.initConnectionPool();
	}
	
	/**
	 * 初始化连接池
	 * @return
	 */
	public static boolean initConnectionPool() {
		boolean result = false;
		if(null == connectionList)connectionList = new ArrayList<Connection>();
		if(null == freeConnectionList)freeConnectionList = new ArrayList<Connection>();
		for (int i = 0; i < initConnectionSize; i++) {
			connectionList.add(BasicSqlFactory.getConnectionToPool(Contants.dbType));
		}
		freeConnectionList.addAll(connectionList);
		freeConnectionSize = initConnectionSize;
		return result;
	}
	
	/**
	 * 连接池中获取连接
	 * @return
	 */
	public synchronized static Connection getConnection() {
		//TODO 判断是否存在连接可用
		if(freeConnectionSize <1) {
			System.out.println("无连接可用");
			return null;
		}
			
		Connection connection = freeConnectionList.get(freeConnectionSize-1);
		freeConnectionSize--;
		return connection;
	}
	
	/**
	 * 连接池返回连接到连接池
	 * @param connection
	 * @return
	 */
	public synchronized static boolean returnConnection(Connection connection) {
		boolean result = freeConnectionList.add(connection);
		freeConnectionSize++;
		return result;
	}
	
	

}

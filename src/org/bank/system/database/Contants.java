package org.bank.system.database;

/**
 * 数据库常量
 * @author chenjianrui
 * @since 2018-12-09
 */
public class Contants {
    /*数据库类型*/
    public static final String sqlserverType = "sqlserver";
    public static final String mysqlType = "mysql";
    public static final String dbType = Contants.sqlserverType;

    /*sqlserver数据库连接*/
    public static final String sqlserverUrl = "jdbc:sqlserver://127.0.0.1:1401;DatabaseName=bankSystemDb";
    public static final String sqlserverDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String sqlserverUsername = "sa";
    public static final String sqlserverPassword = "jian031018";
    public static final String sqlserverSchema = "bankSystemDb.dbo";

    /*mysql数据库连接*/
    public static final String mysqlUrl = "jdbc:mysql://67.209.179.154:3306/bankSystemDb?useUnicode=true&characterEncoding=UTF-8";
    public static final String mysqlUsername = "root";
    public static final String mysqlPassword = "jian031018";


}

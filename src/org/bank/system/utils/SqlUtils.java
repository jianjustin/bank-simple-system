package org.bank.system.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL工具类
 * @author jian
 *
 */
public class SqlUtils {
	
	/**
	 * 根据obj转换为where条件
	 * @param prefix 前缀
	 * @param obj 数据对象
	 * @param notCols 不需要转的属性
	 * @param likeCols 模糊查询的属性
	 * @param columnMap 字段名映射
	 * @return
	 */
	public static String getModleToWhereSql(String prefix,Object obj,String[] notCols,String[] likeCols, List<Object> paramValueList, Map<String,String> columnMap){
		String sql="1 = 1",split=" and ";//sql初始化
		Field[] fields=obj.getClass().getFields();//获取所有属性值
		if(null!=fields){
			for (Field field : fields) {
				field.setAccessible(true);
				String colType=field.getGenericType().toString();//属性类型
				String colName=field.getName();//属性名称
				Object objValue = "";//属性值
				try {
					objValue = field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String columnName = columnMap.get(colName);//获取字段名称
				
				if(StringUtils.isBlank(columnName))continue;//无效字段
				if(null != notCols && !ArrayUtils.contains(notCols, colName)) continue;//该字段是否需要忽略
				Boolean likeFlag= ArrayUtils.contains(likeCols, colName);//该字段是否需要模糊查询
				if(null == likeCols)likeFlag = false;
				if(StringUtils.isNotBlank(prefix))colName=prefix+"."+colName;
					
				if(StringUtils.isNotBlank(objValue)){
					if(StringUtils.isNotBlank(sql)) sql+=split;//添加and分隔符
					if (colType.equals("class java.lang.String")) {
						if(likeFlag) {
							sql += columnName + " like ?";
							paramValueList.add("%" + objValue + "%");
						}else {
							sql += columnName + " = ?";
							paramValueList.add(objValue);
						}
					}else if(colType.equals("class java.lang.Long")||colType.equals("class java.lang.Integer")|| colType.equals("class java.lang.Double")|| colType.equals("class java.lang.Float")){
						sql += columnName + " = ?";
						paramValueList.add(objValue);
						
					}
				}
			}
		}
		return sql;
	}
	
	/**
	 * 获取数据插入sql
	 * @param prefix
	 * @param obj
	 * @param notCols
	 * @param likeCols
	 * @param paramValueList
	 * @param columnMap
	 * @return
	 */
	public static String getInsertSql(String prefix,Object obj,String[] notCols,String[] likeCols, List<Object> paramValueList, Map<String,String> columnMap){
		String sql="",split=" and ";//sql初始化
		Field[] fields=obj.getClass().getFields();//获取所有属性值
		if(null!=fields){
			for (Field field : fields) {
				field.setAccessible(true);
				String colType=field.getGenericType().toString();//属性类型
				String colName=field.getName();//属性名称
				Object objValue = "";//属性值
				try {
					objValue = field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String columnName = columnMap.get(colName);//获取字段名称
				
				if(StringUtils.isBlank(columnName))continue;//无效字段
				if(!ArrayUtils.contains(notCols, colName)) continue;//该字段是否需要忽略
				Boolean likeFlag= ArrayUtils.contains(likeCols, colName);//该字段是否需要模糊查询
				if(StringUtils.isNotBlank(prefix))colName=prefix+"."+colName;
					
				if(StringUtils.isNotBlank(objValue)){
					if(StringUtils.isNotBlank(sql)) sql+=split;//添加and分隔符
					if (colType.equals("class java.lang.String")) {
						if(likeFlag) {
							sql += colName + " like '?'";
							paramValueList.add("%" + objValue + "%");
						}else {
							sql += colName + " = '?'";
							paramValueList.add(objValue);
						}
					}else if(colType.equals("class java.lang.Long")||colType.equals("class java.lang.Integer")|| colType.equals("class java.lang.Double")|| colType.equals("class java.lang.Float")){
						sql += colName + " = ?";
						paramValueList.add(objValue);
						
					}
				}
			}
		}
		return sql;
	}
	
	/**
	 * 获取分页参数
	 * @param limit
	 * @param offset
	 * @return
	 */
	public static String getLimitParam(int offset,int limit) {
		return " AND RowNum >= " + limit + " AND RowNum <" + (limit+offset);
	}
	
	public static List<Map<String,Object>> resultSetToMap(ResultSet resultSet){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		ResultSetMetaData md;
		try {
			md = resultSet.getMetaData();
			int columnCount = md.getColumnCount();//获取行的数量
			while (resultSet.next()) {
				Map<String,Object> rowData = new HashMap<String,Object>();//声明Map
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), resultSet.getObject(i));//获取键名及值
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
//	public static <D> List<D> resultSetToPojo(ResultSet resultSet,Class<D> clazz){
//		List<D> list = new ArrayList<D>();
//		try {
//			D pojo = clazz.newInstance();
//			int columnCount = resultSet.getColumnCount();   //获得列数 
//			while (resultSet.next()) {
//				Map<String,Object> rowData = new HashMap<String,Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					rowData.put(resultSet.getColumnName(i), resultSet.getObject(i));
//				}
//				list.add(rowData);
//	 
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}

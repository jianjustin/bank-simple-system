package org.bank.system.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bank.system.model.AccountList;
import org.bank.system.model.BankAccount;

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
					if (colType.equals("class java.lang.String")) {
						if(likeFlag) {
							if(StringUtils.isNotBlank(sql)) sql+=split;//添加and分隔符
							sql += columnName + " like ?";
							paramValueList.add("%" + objValue + "%");
						}else {
							if(StringUtils.isNotBlank(sql)) sql+=split;//添加and分隔符
							sql += columnName + " = ?";
							paramValueList.add(objValue);
						}
					}else if(colType.equals("class java.lang.Long")||colType.equals("class java.lang.Integer")|| colType.equals("class java.lang.Double")|| colType.equals("class java.lang.Float")){
						if(StringUtils.isNotBlank(sql)) sql+=split;//添加and分隔符
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
	
	public static Map<String,Object> columnMapToObjMap1(Map<String,Object> columnMap){
		Map<String,Object> objMap = new HashMap<String,Object>();
		Iterator<String> iterator = columnMap.keySet().iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			objMap.put(BankAccount.modelToObjMap.get(item), columnMap.get(item));
		}
		return objMap;
	}
	
	public static Map<String,Object> columnMapToObjMap2(Map<String,Object> columnMap){
		Map<String,Object> objMap = new HashMap<String,Object>();
		Iterator<String> iterator = columnMap.keySet().iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			objMap.put(AccountList.modelToObjMap.get(item), columnMap.get(item));
		}
		return objMap;
	}
	
	/**
	 * map数据转obj对象数据
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static <D> D mapToObject(Map<String, Object> map, Class<D> beanClass){    
        if (map == null)  
            return null;    
  
        D obj = null;
		try {
			obj = beanClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}  
  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();   
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }    
  
            field.setAccessible(true);    
            try {
				field.set(obj, map.get(field.getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}   
        }   
  
        return obj;    
    }    
	
	/**
	 * map数据转obj对象数据
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static <D> List<D> mapListToObjectList(List<Map<String, Object>> mapList, Class<D> beanClass){    
        List<D> list = new ArrayList<D>();
        for (int i = 0; i < mapList.size(); i++) {
        	list.add(mapToObject(mapList.get(i), beanClass));
		}
        
        return list;    
    } 
  
	/**
	 * obj数据转map数据
	 * @param obj
	 * @return
	 * @throws Exception
	 */
    public static <D> Map<String, Object> objectToMap(D obj){    
        if(obj == null)return null;    
        Map<String, Object> map = new HashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            try {
				map.put(field.getName(), field.get(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}  
        }    

        return map;  
    }  

}

package com.sdw.soft.core.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * myBatis自定义类型转换
 * @author shangyd
 * @date 2015年11月9日 下午7:14:40
 **/
public class ValueEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E>{

	private Class<E> type;
	private Map<Integer,E> map = new HashMap<Integer,E>();
	
	public ValueEnumTypeHandler(Class<E> type) {
		if(type == null){
			throw new IllegalArgumentException("Type arguemnt cannot be null");
		}
		this.type = type;
		E[] enums = type.getEnumConstants();
		if(enums == null){
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type");
		}
		for(E e : enums){
			ValuedEnum valuedEnum = (ValuedEnum)e;
			map.put(valuedEnum.getValue(), e);
		}
	}
	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int value = rs.getInt(columnName);
		if(rs.wasNull()){
			return null;
		}else{
			return getValuedEnum(value);
		}
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int value = rs.getInt(columnIndex);
		if(rs.wasNull()){
			return null;
		}else{
			return getValuedEnum(value);
		}
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int value = cs.getInt(columnIndex);
		if(cs.wasNull()){
			return null;
		}else{
			return getValuedEnum(value);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E param,
			JdbcType jdbcType) throws SQLException {
		ValuedEnum valuedEnum = (ValuedEnum)param;
		ps.setInt(i, valuedEnum.getValue());
	}

	
	private E getValuedEnum(int value){
		try {
			return map.get(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("cannot convert " + value + " to " + type.getSimpleName() + " by value.",e);
		}
	}
}

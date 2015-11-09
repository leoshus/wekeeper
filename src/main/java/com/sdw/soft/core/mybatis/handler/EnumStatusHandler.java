package com.sdw.soft.core.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.sdw.soft.wekeeper.common.user.vo.UserStatus;

/**
 * myBatis自定义类型转换
 * @author shangyd
 * @date 2015年11月9日 下午7:14:40
 **/
public class EnumStatusHandler extends BaseTypeHandler<UserStatus>{

	private Class<UserStatus> type;
	
	private final UserStatus[] enums;
	
	public EnumStatusHandler(Class<UserStatus> type){
		if(type == null){
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		this.enums = type.getEnumConstants();
		if(this.enums == null){
			throw new IllegalArgumentException(type.getSimpleName() + "does not represent an enum type.");
		}
	}
	@Override
	public UserStatus getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int param = rs.getInt(columnName);
		if(rs.wasNull()){
			return null;
		}else{
			return locateEnumStatus(param);
		}
	}

	@Override
	public UserStatus getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		
		int param = rs.getInt(columnIndex);
		if(rs.wasNull()){
			return null;
		}else{
			return locateEnumStatus(param);
		}
	}

	@Override
	public UserStatus getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int param = cs.getInt(columnIndex);
		if(cs.wasNull()){
			return null;
		}else{
			return locateEnumStatus(param);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int param,
			UserStatus userStatus, JdbcType jdbcType) throws SQLException {
		ps.setInt(param, userStatus.getStatus());
	}

	private UserStatus locateEnumStatus(int code){
		for(UserStatus status : enums){
			if(status.getStatus() == (Integer.valueOf(code))){
				return status;
			}
		}
		throw new IllegalArgumentException("未知的枚举类型:" + code + ",请核对" + type.getSimpleName());
	}
}

/**
 * @author shangyd
 * @Date 2015年11月10日 下午10:41:33
 * Copyright (c) 2015
 **/
package com.sdw.soft.wekeeper.common.helper;

import com.sdw.soft.wekeeper.common.user.vo.SysUser;

public interface PasswordService {

	public void validate(SysUser user,String password);
	public boolean matches(SysUser user,String password);
	public SysUser encryptPassword(SysUser user);
}

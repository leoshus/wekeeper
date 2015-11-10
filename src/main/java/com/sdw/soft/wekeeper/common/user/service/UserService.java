package com.sdw.soft.wekeeper.common.user.service;

import java.util.List;

import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;
import com.sdw.soft.wekeeper.common.user.vo.UserToRole;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:53:00
 **/
public interface UserService {

	public int save(SysUser user);
	public List<SysUser> listUser();
	public SysUser findUserById(long id);
	
}

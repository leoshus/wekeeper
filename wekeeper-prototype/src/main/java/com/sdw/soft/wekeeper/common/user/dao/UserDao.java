package com.sdw.soft.wekeeper.common.user.dao;

import java.util.List;

import com.sdw.soft.core.mybatis.WekeeperRepository;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:04:14
 **/
@WekeeperRepository
public interface UserDao {

	public int saveUser(SysUser user);
	public List<SysUser> listUser();
	public SysUser findUserById(long id);
	public SysUser findUserByName(String username);
}

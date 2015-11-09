package com.sdw.soft.wekeeper.common.user.dao;

import com.sdw.soft.core.mybatis.WekeeperRepository;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:04:14
 **/
@WekeeperRepository
public interface UserDao {

	public int save(SysUser user);
}

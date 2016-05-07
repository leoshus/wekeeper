package com.sdw.soft.wekeeper.common.auth.dao;

import java.util.List;

import com.sdw.soft.core.mybatis.WekeeperRepository;
import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.user.vo.UserToRole;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:03:33
 **/
@WekeeperRepository
public interface RoleDao {

	public int saveRole(Role role);
	
	public List<Role> listRole();
	
	public Role findRoleById(long id);
	
	public int saveUserToRole(UserToRole userToRole);
	
	public List<Role> findRoleByUser(long userId);
}

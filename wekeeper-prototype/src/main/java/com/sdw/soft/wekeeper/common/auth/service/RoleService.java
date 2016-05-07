package com.sdw.soft.wekeeper.common.auth.service;

import java.util.List;

import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.user.vo.UserToRole;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:52:41
 **/
public interface RoleService {

	public int saveRole(Role role);
	
	public List<Role> listRole();
	
	public Role findRoleById(long id);
	
	
	public int saveUserToRole(UserToRole userToRole);
	public List<Role> findRoleByUser(long userId);
}

package com.sdw.soft.core.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.wekeeper.common.auth.service.RoleService;
import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.permission.service.PermissionService;
import com.sdw.soft.wekeeper.common.permission.vo.Permission;
import com.sdw.soft.wekeeper.common.user.service.UserService;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;

/**
 * author shangyd
 * date 2015年11月8日
 **/
public class ExtRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(ExtRealm.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String currentUsername = (String)super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		SysUser user = userService.findUserByName(currentUsername);
		List<Role> roles = roleService.findRoleByUser(user.getId());
		Set<String> roleSet = new HashSet<String>();
		Set<String> permissionSet = new HashSet<String>();
		if(null != roles && roles.size() > 0){
			for(Role role : roles){
				List<Permission> permissions = permissionService.findPermissionByRoleId(role.getId());
				if(null != permissions && permissions.size() > 0){
					for(Permission permission:permissions){
						permissionSet.add(permission.getPermission());
					}
				}
				roleSet.add(role.getRole());
			}
		}
		simpleAuthorInfo.setRoles(roleSet);
		simpleAuthorInfo.setStringPermissions(permissionSet);
		
		return simpleAuthorInfo;
	}

	/**
	 * LoginController.login()方法执行Subject.login()时被调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken = (UsernamePasswordToken)token;
		logger.info("验证当前Subject时获取到的token为:{}" , ReflectionToStringBuilder.toString(uptoken,ToStringStyle.MULTI_LINE_STYLE));
		String password = "";
		if(null != uptoken.getPassword()){
			password = new String(uptoken.getPassword());
		}
		SysUser user = userService.login(uptoken.getUsername(), password);
		this.setSession("currentUser", "hello");
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), password.toCharArray(), getName());
		return info;
	}

	/**
	 * 将一些数据放到ShiroSession中 便于其他地方使用
	 * @param key
	 * @param value
	 */
	private void setSession(Object key,Object value){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			logger.info("Session默认超时时间为[{}]毫秒",session.getTimeout());
			if(null != session){
				session.setAttribute(key, value);
			}
		}
		
	}
}

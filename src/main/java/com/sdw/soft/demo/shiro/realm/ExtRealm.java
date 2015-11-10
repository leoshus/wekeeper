package com.sdw.soft.demo.shiro.realm;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.wekeeper.common.user.service.UserService;

/**
 * @author shangyd
 * @date 2015年11月10日 下午6:36:52
 **/
public class ExtRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String currentUsername = (String)super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		if(null != currentUsername && "hello".equals(currentUsername)){
			simpleAuthorInfo.addRole("admin");
			simpleAuthorInfo.addStringPermission("admin:manage");
			System.out.println("已为用户[hello]赋予了[admin]角色和[admin:manage]权限");
			return simpleAuthorInfo;
		}
		return null;
	}

	/**
	 * LoginController.login()方法执行Subject.login()时被调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken = (UsernamePasswordToken)token;
		System.out.println("验证当前Subject时获取到的token为:" + ReflectionToStringBuilder.toString(uptoken,ToStringStyle.MULTI_LINE_STYLE));
		
		if("hello".equals(uptoken.getUsername())){
			AuthenticationInfo authInfo = new SimpleAuthenticationInfo("hello","123",this.getName());
			this.setSession("currentUser", "hello");
			return authInfo;
		}
		return null;
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
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if(null != session){
				session.setAttribute(key, value);
			}
		}
		
	}
}

package com.sdw.soft.demo.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sdw.soft.wekeeper.common.auth.service.RoleService;
import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.auth.vo.RoleToPermission;
import com.sdw.soft.wekeeper.common.permission.service.PermissionService;
import com.sdw.soft.wekeeper.common.permission.vo.Permission;
import com.sdw.soft.wekeeper.common.user.service.UserService;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;
import com.sdw.soft.wekeeper.common.user.vo.UserStatus;
import com.sdw.soft.wekeeper.common.user.vo.UserToRole;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:10:51
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/context/spring-context.xml","classpath:/context/spring-profiles.xml"})
@ActiveProfiles("mysql")
public class MybatisTest {

	@Autowired
	private UserService userService;
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Test
	public void test01(){
		SysUser user = new SysUser();
		user.setUsername("admin");
		user.setPassword("123");
		user.setUserStatus(UserStatus.normal);
		user.setAdmin(true);
		user.setHasdel(false);
		int num = userService.save(user);
		System.out.println(num);
	}
	
	@Test
	public void test02(){
		SysUser user = new SysUser();
		user.setUsername("admin");
		user.setPassword("123");
		user.setUserStatus(UserStatus.normal);
		user.setAdmin(true);
		user.setHasdel(false);
//		SqlSession session = sqlSessionFactory.openSession();
//		session.insert("com.sdw.soft.wekeeper.common.user.dao.UserDao.saveUser",user);
//		session.close();
		userService.save(user);
		
		Role role = new Role();
		role.setName("管理员");
		role.setRole("admin");
		role.setDescription("管理员角色");
		role.setAvailable(true);
		roleService.saveRole(role);
		
		UserToRole utor = new UserToRole();
		utor.setUserId(user.getId());
		utor.setRoleId(role.getId());
		roleService.saveUserToRole(utor);
		
		Permission permission = new Permission();
		permission.setName("菜单");
		permission.setPermission("admin:menu:*");
		permission.setAvailable(true);
		permission.setDescription("菜单权限");
		permissionService.savePermission(permission);
		
		RoleToPermission rtop = new RoleToPermission();
		rtop.setRoleId(role.getId());
		rtop.setPermissionId(permission.getId());
		permissionService.saveRoleToPermission(rtop);
		
	}
	@Test
	public void test03(){
//		SysUser user = userService.findUserById(1L);
//		System.out.println(user.getUsername() + ",status=" + user.getUserStatus().name());
		List<SysUser> list = userService.listUser();
		System.out.println(list.size());
		
	}
	
	@Test
	public void test04(){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
		token.setRememberMe(true);
		subject.login(token);
		subject.checkPermission("admin:menu");
	}
	
	@Test
	public void test05(){
		
	}
}

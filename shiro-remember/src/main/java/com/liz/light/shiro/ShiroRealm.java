package com.liz.light.shiro;

import com.liz.light.entity.Permission;
import com.liz.light.entity.Role;
import com.liz.light.entity.User;
import com.liz.light.repository.PermissionRepository;
import com.liz.light.repository.RoleRepository;
import com.liz.light.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	/**
	 * 获取用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		String username = user.getUsername();

		System.out.println("用户" + username + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		// 获取用户角色集
		List<Role> roleList = roleRepository.findRolesByUsername(username);
		Set<String> roleSet = new HashSet<String>();
		for (Role r : roleList) {
			roleSet.add(r.getName());
		}
		simpleAuthorizationInfo.setRoles(roleSet);

		// 获取用户权限集
		List<Permission> permissionList = permissionRepository.findPermissionsByUsername(username);
		Set<String> permissionSet = new HashSet<String>();
		for (Permission p : permissionList) {
			permissionSet.add(p.getDescription());
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");
		User user = userRepository.findUserByUsername(userName);

		if (user == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		if (user.getStatus().equals("0")) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}
}

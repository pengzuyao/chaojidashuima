package com.pzy.study.base.config;

import com.pzy.study.entity.RoleEntity;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Slf4j
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        List<RoleEntity> roleInfos = userService.findRoleInfo(user.getId());
        List<String> roles = roleInfos.stream().map(dto -> dto.getRname()).distinct().collect(Collectors.toList());
        authorizationInfo.addRoles(roles);
        List<String> collect = roleInfos.stream().map(dto -> dto.getRid()).distinct().collect(Collectors.toList());
        List<> permissionInfos = userService.findPermissionInfo(collect);
        List<String> urls = permissionInfos.stream().map(dto -> dto.getUrl()).distinct().collect(Collectors.toList());
        authorizationInfo.addStringPermissions(urls);*/
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       String userName = (String)authenticationToken.getPrincipal();
       log.info("{}" , authenticationToken.getCredentials());
        UserEntity user = userService.findUserInfoByName(userName);
        if(user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }
}

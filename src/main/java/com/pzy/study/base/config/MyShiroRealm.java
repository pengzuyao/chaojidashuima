package com.pzy.study.base.config;



/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
/*@Slf4j
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        List<RoleEntity> roleInfos = userService.findRoleInfo(user.getId());
        List<String> roles = roleInfos.stream().map(dto -> dto.getRname()).distinct().collect(Collectors.toList());
        authorizationInfo.addRoles(roles);
        List<String> collect = roleInfos.stream().map(dto -> dto.getRid()).distinct().collect(Collectors.toList());
        List<> permissionInfos = userService.findPermissionInfo(collect);
        List<String> urls = permissionInfos.stream().map(dto -> dto.getUrl()).distinct().collect(Collectors.toList());
        authorizationInfo.addStringPermissions(urls);
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
}*/

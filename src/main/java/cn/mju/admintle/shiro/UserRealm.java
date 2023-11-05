package cn.mju.admintle.shiro;

import cn.mju.admintle.domain.Permission;
import cn.mju.admintle.domain.Role;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //拿到用户主体
        Subject subject = SecurityUtils.getSubject();
        //从登录拿到的（user）principal,即认证中返回的SimpleAuthenticationInfo的user对象
        User userweb = (User) subject.getPrincipal();
        //通过登录user的id从数据库拿到的user
        User dbUser = userService.findUserById(userweb.getId());
        Collection<String> roleCollection = new HashSet<String>();
        Collection<String> permCollection = new HashSet<String>();
        //Set<Permission> permissions = new HashSet<Permission>();
        Permission perm = new Permission();
        if (dbUser != null) {
            //给资源进行授权
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //授权
            Set<Role> roles = dbUser.getRoles();
            for (Role role : roles) {
                roleCollection.add(role.getRoleName());

                Set<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions) {
                    permCollection.add(permission.getPermissionName());
                }
                info.addStringPermissions(permCollection);//权限
            }
            info.addRoles(roleCollection);//角色


            return info;
        }
        return null;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据token用户名从数据库里取的user对象
        User user = userService.findUserByName(token.getUsername());

        if (user == null) {

            return null;//shiro底层抛出UnknownAccountException用户名不存在异常
        }
        //当前realm对象真正的名字
        String realmName = getName();
        //将用户名作为md5加密的盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());

        //密码错误
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, realmName);
    }
}

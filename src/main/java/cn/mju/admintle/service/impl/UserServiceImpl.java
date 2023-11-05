package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.mapper.*;
import cn.mju.admintle.service.UserService;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;

    @Override
    public PageInfo<User> getUsersByPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.getUsers();
        PageInfo<User> page = new PageInfo<>(users);
        return page;
    }

    @Override
    public User findUserByName(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }

    @Override
    public boolean judUserByName(String username) {
        return userMapper.getUserByUsername(username) != null;
    }

    @Override
    public User findUserById(long id) {
        User user = userMapper.getUserById(id);
        Set<Role> roles = roleMapper.getRoleByUserId(id);
        for (Role role : roles) {
            Set<Permission> permissions = permissionMapper.getPermissionByRoleName(role.getRoleName());
            role.setPermissions(permissions);
        }
        user.setRoles(roles);
        return user;
    }

    @Override
    public boolean addUser(User user) {
         return userMapper.addUser(user) > 0;
    }

    //因为一个用户只有一个角色，所以只取set里的一个角色(懒得改)
    @Override
    public String getRoleName(long userId) {
        Set<Role> roleByUserId = roleMapper.getRoleByUserId(userId);
        String roleName = null;
        for (Role role : roleByUserId) {
            roleName = role.getRoleName();
        }
        return roleName;
    }


}


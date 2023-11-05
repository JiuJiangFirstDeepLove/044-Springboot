package cn.mju.admintle.service;


import cn.mju.admintle.domain.User;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    PageInfo<User> getUsersByPage(int pageNum, int pageSize);

    User findUserByName(String username);

    boolean judUserByName(String username);

    User findUserById(long id);

    boolean addUser(User user);

    String getRoleName(long userId);
}

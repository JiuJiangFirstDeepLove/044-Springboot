package cn.mju.admintle.service;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.vo.NoticeVo;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {

    PageInfo<User> getUserByCondition(String username, String deptName, String jobName,int pageNum, int pageSize);

    boolean addUser(User user,String roleName);

    boolean update(User user,String roleName);

    boolean delete(Long id);

    boolean delteBatch(Long[] ids);

    PageInfo<Role> getRolesByName(int pageNum, int pageSize,String roleName);

    PageInfo<File> getAllFiles(int pageNum, int pageSize);

    PageInfo<File> searchFile(String userName,int pageNum, int pageSize);

    boolean quitEmp(Long id);

    List<Dept> getDepts();


    List<Job> getJobs();

    Dept getDeptAndUsers(int id);

    boolean checkDept(int id);

    boolean addDept(Dept dept);

    boolean updateDept(Dept dept);

    PageInfo<Notice> getAllNotice(int pageNum, int pageSize);

    NoticeVo findNotice(Integer id);

    PageInfo<Notice> searchNotice(String title,int pageNum, int pageSize);

    boolean publishNotice(Notice notice, HttpServletRequest request);

    boolean delteBatchNotice(Integer[] ids);

    boolean deleteNotice(Integer id);

    List<UserVo> downloadUser();

    NoticeVo getLatest();

    boolean updatePass(User user);




}



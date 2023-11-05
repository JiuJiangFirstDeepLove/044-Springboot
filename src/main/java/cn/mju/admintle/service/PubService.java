package cn.mju.admintle.service;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.dto.FileDto;
import cn.mju.admintle.dto.RoleDto;
import cn.mju.admintle.dto.WagesDto;
import cn.mju.admintle.vo.*;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;


public interface PubService {
    PageInfo<User> getPage(int pageNum, int pageSize, HashMap<String, Object> map);

    PageInfo<Applicant> getAppPage(int pageNum, int pageSize, HashMap<String, Object> map);

    PageInfo<Leave> getLeavePage(int pageNum, int pageSize, List<Long> list);

    List<UserVo> changeVo(PageInfo<User> pageInfo);

    List<RoleDto> changeRoleDto(PageInfo<Role> pageInfo);

    List<FileDto> changeFileDto(PageInfo<File> pageInfo);

    List<NoticeVo> changeNoticeVo(PageInfo<Notice> pageInfo);

    List<WagesDto> changeWagesDto(PageInfo<Wages> pageInfo);

    List<ApplicantVo> changeApplicantVo(PageInfo<Applicant> pageInfo);

    List<SignVo> changeSignVo(PageInfo<Sign> pageInfo);

    List<LeaveVo> changeLeaveVo(PageInfo<Leave> pageInfo);

    List<HealthVo> changeHealthVo(PageInfo<Health> pageInfo);

    User passwordToMD5(User user);


}

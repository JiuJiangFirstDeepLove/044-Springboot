package cn.mju.admintle.service;

import cn.mju.admintle.domain.Applicant;
import cn.mju.admintle.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ApplicantService {

    PageInfo<Applicant> getApps(int pageNum,int pageSize);

    PageInfo<Applicant> getAppByCondition(String username, String deptName, String jobName, int pageNum, int pageSize);

    boolean addApp(Applicant applicant);

    boolean update(Applicant applicant);

    boolean delete(Long id);

    boolean delteBatch(Long[] ids);

    PageInfo<Applicant> getTalents(int pageNum,int pageSize);
}

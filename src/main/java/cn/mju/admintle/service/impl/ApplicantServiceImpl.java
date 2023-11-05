package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.mapper.ApplicantMapper;
import cn.mju.admintle.mapper.DeptMapper;
import cn.mju.admintle.mapper.JobMapper;
import cn.mju.admintle.service.ApplicantService;
import cn.mju.admintle.service.PubService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantMapper applicantMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private PubService pubService;

    @Override
    public PageInfo<Applicant> getApps(int pageNum,int pageSize) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        PageHelper.startPage(pageNum,pageSize);
        List<Applicant> all = applicantMapper.getApps(list);
        PageInfo<Applicant> page = new PageInfo<>(all);
        return page;
    }

    @Override
    public PageInfo<Applicant> getAppByCondition(String username, String deptName, String jobName, int pageNum, int pageSize) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("deptName",deptName);
        map.put("jobName",jobName);
        map.put("username",username);
        ArrayList<Integer> jobIds = new ArrayList<>();
        ArrayList<Integer> deptIds = new ArrayList<>();
        if (!username.equals("") && deptName.equals("") && jobName.equals("")){
            return pubService.getAppPage(pageNum,pageSize,map);

        }
        if (username.equals("") && deptName.equals("") && !jobName.equals("")){
            List<Job> jobs = jobMapper.getJobByName(map);
            for (Job job : jobs) {
                jobIds.add(job.getId());
            }
            map.put("jobId",jobIds);
            return pubService.getAppPage(pageNum,pageSize,map);
        }
        if (username.equals("") && !deptName.equals("") && jobName.equals("")){
            List<Dept> deptByName = deptMapper.getDeptByName(map);
            for (Dept dept : deptByName) {
                deptIds.add(dept.getId());
            }
            map.put("deptId",deptIds);
            return pubService.getAppPage(pageNum,pageSize,map);
        }else{
            List<Dept> deptByName = deptMapper.getDeptByName(map);
            for (Dept dept : deptByName) {
                deptIds.add(dept.getId());
            }
            map.put("deptId",deptIds);
            List<Job> jobByName = jobMapper.getJobByName(map);
            for (Job job : jobByName) {
                jobIds.add(job.getId());
            }
            map.put("deptId",deptIds);
            map.put("jobId",jobIds);
            return pubService.getAppPage(pageNum,pageSize,map);
        }

    }

    @Override
    public boolean addApp(Applicant applicant) {
        boolean flag = applicantMapper.addApplicant(applicant) >0;
        return flag;
    }

    @Override
    public boolean update(Applicant applicant) {
        boolean flag = applicantMapper.updateApplicant(applicant) >0;
        return flag;
    }

    @Override
    public boolean delete(Long id) {
        boolean flag = applicantMapper.deleteApplicant(id) >0;
        if (flag){
            Applicant app = applicantMapper.getApplicantById(id);

        }
        return flag;
    }

    @Override
    public boolean delteBatch(Long[] ids) {
        ArrayList<Long> cids = new ArrayList<>(Arrays.asList(ids));
        boolean flag = applicantMapper.deleleteBatch(cids) >0;
        return flag;
    }

    @Override
    public PageInfo<Applicant> getTalents(int pageNum, int pageSize) {
        List<Applicant> talents = applicantMapper.getApplicantByState(2);
        PageInfo<Applicant> page = new PageInfo<>(talents);
        return page;
    }
}

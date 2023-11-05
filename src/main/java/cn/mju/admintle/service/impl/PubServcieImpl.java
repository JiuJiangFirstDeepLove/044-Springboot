package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.dto.FileDto;
import cn.mju.admintle.dto.RoleDto;
import cn.mju.admintle.dto.WagesDto;
import cn.mju.admintle.mapper.*;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.service.TimeService;
import cn.mju.admintle.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PubServcieImpl implements PubService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ApplicantMapper applicantMapper;

    @Autowired
    private TimeService timeService;

    @Autowired
    private LeaveMapper leaveMapper;

    //条件查询分页
    @Override
    public PageInfo<User> getPage(int pageNum, int pageSize, HashMap<String, Object> map) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userByName = userMapper.getUserByName(map);
        PageInfo<User> page = new PageInfo<>(userByName);
        return page;
    }

    @Override
    public PageInfo<Applicant> getAppPage(int pageNum, int pageSize, HashMap<String, Object> map) {

        PageHelper.startPage(pageNum, pageSize);
        List<Applicant> apps = applicantMapper.getAppByCondition(map);
        PageInfo<Applicant> page = new PageInfo<>(apps);
        return page;
    }

    @Override
    public PageInfo<Leave> getLeavePage(int pageNum, int pageSize, List<Long> list) {
        PageHelper.startPage(pageNum, pageSize);
        List<Leave> leaveByUserIds = leaveMapper.getLeaveByUserIds(list);
        PageInfo<Leave> page = new PageInfo<>(leaveByUserIds);
        return page;
    }

    //转换user entity->vo
    @Override
    public List<UserVo> changeVo(PageInfo<User> pageInfo) {
        List<User> users = pageInfo.getList();
        List<UserVo> userVos = users.stream().map(e -> (
                new UserVo(e.getId(), e.getUsername(), e.getAddress(), e.getBirthday(), e.getEmail(), e.getPhone(),
                        deptMapper.getDeptById(e.getDeptId()).getDeptName(), jobMapper.getJobById(e.getJobId()).getJobName(),
                        e.getState()
                )
        )).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public List<RoleDto> changeRoleDto(PageInfo<Role> pageInfo) {
        List<Role> roles = pageInfo.getList();
        List<RoleDto> roleDtos = roles.stream().map(e -> (
                new RoleDto(e.getId(), userMapper.getUserById(e.getUserId()).getUsername()
                ))
        ).collect(Collectors.toList());
        return roleDtos;
    }

    @Override
    public List<FileDto> changeFileDto(PageInfo<File> pageInfo) {
        List<File> files = pageInfo.getList();
        List<FileDto> fileDtos = new ArrayList<>();
        for (File file : files) {
            User user = userMapper.getUserById(file.getUserId());
            if (user == null) {
                continue;
            }
            FileDto fileDto = new FileDto(file.getId(), user.getUsername(), file.getEntryTime(),
                    file.getQuitTime(), file.getDeptName(), file.getJobName()
            );
            fileDtos.add(fileDto);
        }
//        List<FileDto> fileDtos = files.stream().map(e -> (
//                new FileDto(e.getId(), userMapper.getUserById(e.getUserId()).getUsername(), e.getEntryTime(),
//                        e.getQuitTime(), e.getDeptName(), e.getJobName()
//                ))
//
//        ).collect(Collectors.toList());

        return fileDtos;
    }

    @Override
    public List<NoticeVo> changeNoticeVo(PageInfo<Notice> pageInfo) {
        List<Notice> notices = pageInfo.getList();
        List<NoticeVo> noticeVos = notices.stream().map(e -> (
                new NoticeVo(e.getId(), e.getHead(), e.getContent(), e.getCreateTime(),
                        userMapper.getUserById(e.getUserId()).getUsername())

        )).collect(Collectors.toList());
        return noticeVos;
    }

    @Override
    public List<WagesDto> changeWagesDto(PageInfo<Wages> pageInfo) {
        List<Wages> wageses = pageInfo.getList();
        List<WagesDto> wagesDtos = new ArrayList<>();
        for (Wages wages : wageses) {
            User user = userMapper.getUserById(wages.getUserId());
            if (user == null) {
                continue;
            }
            WagesDto wagesDto = new WagesDto(wages.getId(), user.getUsername(),
                    deptMapper.getDeptById(userMapper.getUserById(wages.getUserId()).getDeptId()).getDeptName(),
                    jobMapper.getJobById(userMapper.getUserById(wages.getUserId()).getJobId()).getJobName(), wages.getBasicWages(),
                    wages.getLivePay(), wages.getNightPay(), wages.getRewardPay(), wages.getSocialPay(), wages.getAbsenceFines(), wages.getLateFines(),
                    wages.getRealWages(), wages.getPayDate()
            );
            wagesDtos.add(wagesDto);
        }
//        List<WagesDto> wagesDtos = wageses.stream().map(e -> (
//                new WagesDto(e.getId(), userMapper.getUserById(e.getUserId()).getUsername(),
//                        deptMapper.getDeptById(userMapper.getUserById(e.getUserId()).getDeptId()).getDeptName(),
//                        jobMapper.getJobById(userMapper.getUserById(e.getUserId()).getJobId()).getJobName(), e.getBasicWages(),
//                        e.getLivePay(), e.getNightPay(), e.getRewardPay(), e.getSocialPay(), e.getAbsenceFines(), e.getLateFines(),
//                        e.getRealWages(), e.getPayDate()
//                ))
//
//        ).collect(Collectors.toList());
        return wagesDtos;
    }

    @Override
    public List<ApplicantVo> changeApplicantVo(PageInfo<Applicant> pageInfo) {
        List<Applicant> apps = pageInfo.getList();
        List<ApplicantVo> appVos = apps.stream().map(e -> (
                new ApplicantVo(e.getId(), e.getUsername(), deptMapper.getDeptById(e.getDeptId()).getDeptName(),
                        jobMapper.getJobById(e.getJobId()).getJobName(), e.getExpWages(), e.getComeDate(), e.getAddress(),
                        e.getBirthday(), e.getEmail(), e.getPhone(), e.getResume(), e.getState()
                )
        )).collect(Collectors.toList());
        return appVos;
    }

    @Override
    public List<SignVo> changeSignVo(PageInfo<Sign> pageInfo) {
        List<Sign> signs = pageInfo.getList();
        List<SignVo> signVos = signs.stream().map(e -> {
            SignVo signVo = new SignVo();
            try {
                signVo = new SignVo(e.getId(), userMapper.getUserById(e.getUserId()).getUsername(), e.getTime(), timeService.judgeLate(e.getTime())
                );

            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            return signVo;
        }).collect(Collectors.toList());
        return signVos;
    }

    @Override
    public List<LeaveVo> changeLeaveVo(PageInfo<Leave> pageInfo) {
        List<Leave> leaves = pageInfo.getList();
        List<LeaveVo> leaveVos = leaves.stream().map(e -> (
                new LeaveVo(e.getId(), userMapper.getUserById(e.getUserId()).getUsername(),
                        e.getBeginTime(), e.getEndTime(), e.getReason(), e.getState())
        )).collect(Collectors.toList());
        return leaveVos;
    }

    @Override
    public List<HealthVo> changeHealthVo(PageInfo<Health> pageInfo) {
        List<Health> healths = pageInfo.getList();
        List<HealthVo> healthVos = healths.stream().map(e -> (
                new HealthVo(e.getId(), userMapper.getUserById(e.getUserId()).getUsername(), deptMapper.getDeptById(userMapper.getUserById(e.getUserId()
                ).getDeptId()).getDeptName(), jobMapper.getJobById(userMapper.getUserById(e.getUserId()).getJobId()).getJobName(), e.getAddress(), e.getTemp(), e.getPath(), e.getTouch(), e.getState(), e.getToday())
        )).collect(Collectors.toList());

        return healthVos;
    }


    @Override
    public User passwordToMD5(User user) {
        String hashAlgorithmName = "MD5";
        Object crdentials = user.getPassword();
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        int hashIterations = 1024;
        Object password = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        user.setPassword(password.toString());
        return user;
    }

    public static User testPasswordToMD5(User user) {
        String hashAlgorithmName = "MD5";
        Object crdentials = user.getPassword();
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        int hashIterations = 1024;
        Object password = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        user.setPassword(password.toString());
        return user;
    }
    public static void main(String[] arg){
        User user = new User();
        user.setPassword("123456");
        user.setUsername("admin");
        testPasswordToMD5(user);
        System.out.println(user.getPassword());
    }


}

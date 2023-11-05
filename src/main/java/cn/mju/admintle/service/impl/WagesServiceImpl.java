package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.User;
import cn.mju.admintle.domain.Wages;
import cn.mju.admintle.dto.WagesDto;
import cn.mju.admintle.mapper.DeptMapper;
import cn.mju.admintle.mapper.JobMapper;
import cn.mju.admintle.mapper.UserMapper;
import cn.mju.admintle.mapper.WagesMapper;
import cn.mju.admintle.service.WagesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WagesServiceImpl implements WagesService {

    @Autowired
    private WagesMapper wagesMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;

    @Override
    public PageInfo<Wages> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Wages> wageses = wagesMapper.getAll();
        PageInfo<Wages> wagesPageInfo = new PageInfo<>(wageses);
        return wagesPageInfo;
    }


    @Override
    public PageInfo<Wages> findWagesByUserName(String userName, int pageNum, int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", userName);
        List<User> users = userMapper.getUserByName(map);
        ArrayList<Long> list = new ArrayList<>();
        for (User user : users) {
            Long id = user.getId();
            list.add(id);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Wages> wageses = wagesMapper.getWagesByIds(list);
        PageInfo<Wages> pageInfo = new PageInfo<>(wageses);
        return pageInfo;
    }

    @Override
    public WagesDto getOne(long id) {
        Wages wages = wagesMapper.getWagesById(id);
        WagesDto wagesDto = new WagesDto();
        wagesDto.setUsername(userMapper.getUserById(wages.getUserId()).getUsername());
        wagesDto.setDeptName(deptMapper.getDeptById(userMapper.getUserById(wages.getUserId()).getDeptId()).getDeptName());
        wagesDto.setJobName(jobMapper.getJobById(userMapper.getUserById(wages.getUserId()).getJobId()).getJobName());
        BeanUtils.copyProperties(wages, wagesDto);
        return wagesDto;
    }

    @Override
    public boolean insertWages(WagesDto wagesDto) {
        Wages wages = new Wages();
        User user = userMapper.getUserByUsername(wagesDto.getUsername());
        wages.setUserId(user.getId());
        BeanUtils.copyProperties(wagesDto, wages);
        boolean flag = wagesMapper.addWages(wages) > 0;
        return flag;
    }


    @Override
    public boolean deleteBatch(Long[] ids) {
        ArrayList<Long> list = new ArrayList<>(Arrays.asList(ids));
        boolean flag = wagesMapper.deleleteBatch(list) > 0;
        return flag;
    }

    @Override
    public List<WagesDto> downloadWages() {
        List<WagesDto> wagesDtos = new ArrayList<>();
        List<Wages> wageses = wagesMapper.getAll();
        for (Wages wages : wageses) {
            User user = userMapper.getUserById(wages.getUserId());
            if(user==null){
                continue;
            }
            WagesDto wagesDto =new WagesDto(wages.getId(), user.getUsername(),
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
    public List<WagesDto> getSelf(long userId) {
        List<Wages> list = wagesMapper.getWagesByUserId(userId);
        List<WagesDto> wagesDtos = list.stream().map(e -> (
                new WagesDto(e.getId(), userMapper.getUserById(e.getUserId()).getUsername(),
                        deptMapper.getDeptById(userMapper.getUserById(e.getUserId()).getDeptId()).getDeptName(),
                        jobMapper.getJobById(userMapper.getUserById(e.getUserId()).getJobId()).getJobName(), e.getBasicWages(),
                        e.getLivePay(), e.getNightPay(), e.getRewardPay(), e.getSocialPay(), e.getAbsenceFines(), e.getLateFines(),
                        e.getRealWages(), e.getPayDate()
                ))

        ).collect(Collectors.toList());
        return wagesDtos;
    }


}

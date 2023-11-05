package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.mapper.LeaveMapper;
import cn.mju.admintle.mapper.SignMapper;
import cn.mju.admintle.mapper.UserMapper;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.service.TimeService;
import cn.mju.admintle.utils.DateFormatUtil;
import cn.mju.admintle.vo.LeaveVo;
import cn.mju.admintle.vo.SignVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Pattern;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.SignStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimeServiceImpl implements TimeService {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PubService pubService;



    @Override
    public PageInfo<Sign> getAllSigns(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<Sign> all = signMapper.getAll();
        PageInfo<Sign> pageInfo = new PageInfo<>(all);

        return pageInfo;

    }

    @Override
    public Sign getOne(int id) {
        Sign one = signMapper.getOne(id);
        return one;
    }

    @Override
    public List<Sign> getOneList(long userId) {

        List<Sign> one = signMapper.getOneList(userId);
        return one;
    }

    @Override
    public Sign getSign(long userId) {
        Date today = DateFormatUtil.getToday();
        Sign sign = signMapper.getSignByUserIdDate(userId,today);
        return sign;

    }

    @Override
    public boolean addSign(long userId) {

        Sign sign = new Sign();
        sign.setUserId(userId);
        sign.setTime(new Date());
        boolean flag = signMapper.insertSign(sign) >0;
        return flag;
    }



    @Override
    //获取所有迟到的sign
    public  PageInfo<Sign> getAllLateSign(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Sign> dbSigns = signMapper.getAll();
        ArrayList<Sign> lateSigns = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date inTime = null;
        try {
            inTime = sdf.parse("09:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Sign sign : dbSigns) {
            Date time = sign.getTime();
            if (time.after(inTime)){
                lateSigns.add(sign);
            }
        }

        return new PageInfo<>(lateSigns);
    }


    @Override
    //获取某人指定月份的签到记录
    public PageInfo<Sign> getMonthSigns(int pageNum, int pageSize,long userId, int month) {
        PageHelper.startPage(pageNum,pageSize);
        List<Sign>  monthSigns = signMapper.getSignByUserIdMonth(userId, month);
        PageInfo<Sign> pageInfo = new PageInfo<>( monthSigns);

        return pageInfo;
    }


    @Override
    //判断当前时间是否迟到
    public boolean judgeLate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //上班时间
        Date inTime = sdf.parse("09:00:00");
        //实际时间
        Date nowTime = sdf.parse(sdf.format(date));
        if(nowTime.after(inTime)){
            return true;
        }
        return false;
    }
    //请假
    @Override
    public boolean applyLeave(long userId, String reason, Date beginTime, Date endTime) {
        Leave leave = new Leave();
        leave.setUserId(userId);
        leave.setReason(reason);
        leave.setBeginTime(beginTime);
        leave.setEndTime(endTime);
        leave.setState(0);
        boolean flag = leaveMapper.insertLeave(leave) >0;
        return flag;
    }

    //批准请假
    @Override
    public boolean approvalLeave(int id,int state) {
        Leave one = leaveMapper.getOne(id);
        one.setState(state);
        boolean flag = leaveMapper.updateLeave(one) >0;
        return flag;
    }


    @Override
    public PageInfo<Leave> getAllLeaves(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Leave> all = leaveMapper.getAll();
        PageInfo<Leave> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public PageInfo<Leave> getOneleaves(int pageNum, int pageSize, long userId) {
        List<Leave> oneList = leaveMapper.getOneList(userId);
        PageInfo<Leave> pageInfo = new PageInfo<>(oneList);
        return pageInfo;
    }

    @Override
    public PageInfo<Leave> getLeaveByState(int pageNum, int pageSize,int state) {
        PageHelper.startPage(pageNum,pageSize);
        List<Leave> all = leaveMapper.getLeaveByState(0);
        PageInfo<Leave> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public LeaveVo getLeave(int id) {
        Leave leave= leaveMapper.getOne(id);
        LeaveVo leaveVo = new LeaveVo();
        leaveVo.setUserName(userMapper.getUserById(leave.getUserId()).getUsername());
        BeanUtils.copyProperties(leave,leaveVo);
        return leaveVo;
    }
    //根据名字模糊查询请假条
    @Override
    public PageInfo<Leave> searchLeave(int pageNum, int pageSize,String username) {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Long> ids = new ArrayList<>();
        map.put("username",username);
        List<User> users = userMapper.getUserByName(map);
        for (User user : users) {
            Long userId = user.getId();
            ids.add(userId);
        }
        PageInfo<Leave> leavePage = pubService.getLeavePage(pageNum, pageSize, ids);
        return leavePage;
    }


}

package cn.mju.admintle.service;

import cn.mju.admintle.domain.Leave;
import cn.mju.admintle.domain.Role;
import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.vo.LeaveVo;
import cn.mju.admintle.vo.SignVo;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TimeService {

    PageInfo<Sign> getAllSigns(int pageNum, int pageSize);

    Sign getOne(int id);

    List<Sign> getOneList(long userId);

    Sign getSign(long userId);

    boolean addSign(long userId);

    //获取总迟到列表
    PageInfo<Sign> getAllLateSign(int pageNum, int pageSize);

    //获取某人指定月sign
    PageInfo<Sign> getMonthSigns(int pageNum, int pageSize,long userId, int month);

    //获取一个人的签到记录

    //判断当前时间是否迟到
    boolean judgeLate(Date date) throws ParseException;

    boolean applyLeave(long userId, String reason, Date beginTime,Date endTime);

    boolean approvalLeave(int id,int state);


    PageInfo<Leave> getAllLeaves(int pageNum, int pageSize);

    PageInfo<Leave> getOneleaves(int pageNum, int pageSize,long userId);

    PageInfo<Leave> getLeaveByState(int pageNum, int pageSize,int state);

    LeaveVo getLeave(int id);

    PageInfo<Leave> searchLeave(int pageNum, int pageSize,String username);




}

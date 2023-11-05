package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.Health;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.mapper.HealthMapper;
import cn.mju.admintle.mapper.UserMapper;
import cn.mju.admintle.service.HealthService;
import cn.mju.admintle.utils.DateFormatUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthMapper healthMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Health> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Health> all = healthMapper.getAll();
        PageInfo<Health> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public boolean addHealth(Health health) {
        boolean flag = healthMapper.addHealth(health) >0;
        return flag;
    }

    //获取某天所有的填表信息
    @Override
    public PageInfo<Health> getListByDate(int pageNum, int pageSize,Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date time = DateFormatUtil.getTime(sdf, date);
        PageHelper.startPage(pageNum,pageSize);
        List<Health> listByDate = healthMapper.getListByDate(time);
        PageInfo<Health> pageInfo = new PageInfo<>(listByDate);
        return pageInfo;
    }

    //获取某人当天填表
    @Override
    public Health getOneToday(long userId) {
        Date today = DateFormatUtil.getToday();
        Health oneToday = healthMapper.getOneToday(userId, today);
        return oneToday;
    }

    @Override
    public Map<String, Object> getDayData(Date date) {
        HashMap<String, Object> map = new HashMap<>();
        List<User> users = userMapper.getUsersByState(1);
        Date today = DateFormatUtil.getTime(new SimpleDateFormat("yyyyMMdd"),date);
        List<Health> listByDate = healthMapper.getListByDate(today);
        //应填人数（在岗数）
        map.put("total",users.size());
        //今天已填表人数
        map.put("already",listByDate.size());
        map.put("date",date);
        return map;
    }


}

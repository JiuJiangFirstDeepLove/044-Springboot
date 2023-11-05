package cn.mju.admintle.service;

import cn.mju.admintle.domain.Health;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HealthService {

    PageInfo<Health> getAll(int pageNum, int pageSize);

    boolean addHealth(Health health);

    PageInfo<Health> getListByDate(int pageNum, int pageSize,Date date);

    Health getOneToday(long userId);

    Map<String,Object> getDayData(Date date);


}

package cn.mju.admintle.service;

import cn.mju.admintle.domain.Wages;
import cn.mju.admintle.dto.WagesDto;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface WagesService {

    PageInfo<Wages> getAll(int pageNum, int pageSize);


    PageInfo<Wages> findWagesByUserName(String userName,int pageNum,int pageSize);

    WagesDto getOne(long id);

    boolean insertWages(WagesDto wagesDto);


    boolean deleteBatch(Long[] ids);

    List<WagesDto> downloadWages();


    List<WagesDto> getSelf(long userId);


}

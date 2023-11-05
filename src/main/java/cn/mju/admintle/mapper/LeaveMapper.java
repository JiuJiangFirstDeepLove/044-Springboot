package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.File;
import cn.mju.admintle.domain.Leave;
import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.provider.DeptProvider;
import cn.mju.admintle.provider.FileProvider;
import cn.mju.admintle.provider.LeaveProvider;
import cn.mju.admintle.provider.SignProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeaveMapper {

    @Select("select * from tb_leave order by begin_time desc")
    List<Leave> getAll();

    @Select("select * from tb_leave where id = #{id} ")
    Leave getOne(int id);

    @Select("select * from tb_leave where user_id = #{userId} order by begin_time desc")
    List<Leave> getOneList(long userId);

    @Select("select * from tb_leave where user_id =#{userId} and begin_Time = #{beginTime}")
    Leave getLeave(@Param("userId") long userId,@Param("beginTime") Date beginTime);

    @Select("select * from tb_leave where state =#{state} ")
    List<Leave> getLeaveByState(int state);

    @SelectProvider(type = LeaveProvider.class,method = "selectLeave")
    List<Leave> getLeaveByUserIds(List<Long> ids);

    @InsertProvider(type = LeaveProvider.class, method = "insertLeave")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertLeave(Leave leave);

    @UpdateProvider(type = LeaveProvider.class, method = "updateLeave")
    int updateLeave(Leave leave);

    @Delete("delete from tb_leave where user_id = #{userId}")
    int deleteLeaveByUserId(long userId);

    @DeleteProvider(type = LeaveProvider.class,method = "batchDeleteByUserId")
    int deleleteBatchByUserIdS(List<Long> userIds);

}

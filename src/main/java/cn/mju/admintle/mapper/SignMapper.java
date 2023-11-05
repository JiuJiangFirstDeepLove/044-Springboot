package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.provider.NoticeProvider;
import cn.mju.admintle.provider.RoleProvider;
import cn.mju.admintle.provider.SignProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Repository
public interface SignMapper {

    @Select("select * from tb_sign order by time desc")
    List<Sign> getAll();

    @Select("select * from tb_sign where id = #{id}")
    Sign getOne(int id);

    //查一个人的签到记录
    @Select("select * from tb_sign where user_id = #{userId} order by time desc")
    List<Sign> getOneList(long userId);

    @Select("SELECT * FROM tb_sign WHERE MONTH(time) = #{month} and user_id = #{userId} order by time desc")
    List<Sign> getSignByUserIdMonth(@Param("userId") long userId,@Param("month") int month);

    @Select("SELECT * FROM tb_sign WHERE DATE(time) = #{time} and user_id = #{userId} order by time desc")
    Sign getSignByUserIdDate(@Param("userId") long userId,@Param("time") Date time);

    @Select("select * from tb_sign where user_id =#{userId} and time = #{time}")
    Sign getSign(@Param("userId") long userId,@Param("time") Date time);

    @InsertProvider(type = SignProvider.class, method = "insertSign")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSign(Sign sign);

    @Delete("delete from tb_sign where user_id = #{userId}")
    int deleteSignByUserId(long userId);

    @DeleteProvider(type = SignProvider.class,method = "batchDeleteByUserId")
    int deleleteBatchByUserIdS(List<Long> userIds);


}

package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.File;
import cn.mju.admintle.domain.Health;
import cn.mju.admintle.provider.FileProvider;
import cn.mju.admintle.provider.HealthProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HealthMapper {

    @Select("select * from tb_health")
    List<Health> getAll();

    @Select("select * from tb_health where id = #{id}")
    Health getOne(Integer id);

    @Select("select * from tb_health where user_id = #{userId}")
    List <Health> getOneList(Long userId);

    @Select("select * from tb_health where today = #{today} ")
    List<Health> getListByDate(Date today);

    @Select("select * from tb_health where user_id =#{userId} and today = #{today}")
    Health getOneToday(@Param("userId") long userId,@Param("today") Date today);

    @InsertProvider(type = HealthProvider.class, method = "insertHealth")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addHealth(Health health);

    @UpdateProvider(type = HealthProvider.class, method = "updateHealth")
    int updateHealth(File file);

    @DeleteProvider(type = HealthProvider.class,method = "batchDelete")
    int deleleteBatch(List<Long> ids);

    @Delete("delete from tb_health where user_id = #{userId}")
    int delete(long userId);

}

package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Wages;
import cn.mju.admintle.provider.WagesProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagesMapper {
    @Select("select * from tb_wages order by pay_date desc")
    List<Wages> getAll();

    @Select("select * from tb_wages where id = #{id}")
    Wages getWagesById(long id);

    @Select("select * from tb_wages where user_id = #{userId} order by pay_date desc")
    List<Wages> getWagesByUserId(long userId);

    @SelectProvider(type = WagesProvider.class,method = "selectWages")
    List<Wages> getWagesByIds(List<Long> ids);

    @InsertProvider(type = WagesProvider.class, method = "insertWages")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addWages(Wages wages);

    @UpdateProvider(type = WagesProvider.class, method = "updateWages")
    int updateWages(Wages wages);

    @Delete("delete from tb_wages where id = #{id}")
    int deleteWages(Long id);

    @DeleteProvider(type = WagesProvider.class,method = "batchDelete")
    int deleleteBatch(List<Long> ids);

}

package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Notice;
import cn.mju.admintle.provider.NoticeProvider;
import cn.mju.admintle.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {

    @Select("select * from tb_notice order by create_time DESC")
    List<Notice> findAll();

    @Select("select * from tb_notice where id = #{id}" )
    Notice getNotice(int id);



    @Select("select * from tb_notice where head like concat ('%',#{head},'%') order by create_time DESC")
    List<Notice> getNoticesByTitle(String head);

    @InsertProvider(type = NoticeProvider.class, method = "insertNotice")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addNotice(Notice notice);

    @UpdateProvider(type = NoticeProvider.class, method = "updateNotice")
    int updateNotice(Notice notice);

    @DeleteProvider(type = NoticeProvider.class,method = "batchDelete")
    int deleleteBatch(List<Integer> ids);

    @DeleteProvider(type = NoticeProvider.class,method = "batchDeleteByUserId")
    int deleleteBatchByUserId(List<Long> ids);

    @Delete("delete from tb_notice where id = #{id}")
    int delete(int id);

    @Delete("delete from tb_notice where user_id = #{userId}")
    int deleteByUser(long userId);
}

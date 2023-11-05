package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.File;
import cn.mju.admintle.provider.DeptProvider;
import cn.mju.admintle.provider.FileProvider;
import cn.mju.admintle.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {

    @Select("select * from tb_file order by user_id,entry_time")
    List<File> getAll();

    @Select("select * from tb_file where id = #{id}")
    File getFileById(Long id);

    @Select("select * from tb_file where user_id = #{userId} order by entry_time")
    List<File> getFileByUserId(long userId);

    @SelectProvider(type = FileProvider.class,method = "selectFile")
    List<File> getFileByIds(List<Long> ids);

    @InsertProvider(type = FileProvider.class, method = "insertFile")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertFile(File file);

    @UpdateProvider(type = FileProvider.class, method = "updateFile")
    int updateFile(File file);

    @DeleteProvider(type = FileProvider.class,method = "batchDelete")
    int deleleteBatch(List<Long> ids);

    @Delete("delete from tb_file where user_id = #{userId}")
    int delete(long userId);
}

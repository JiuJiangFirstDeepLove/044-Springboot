package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Permission;
import cn.mju.admintle.provider.PermissionProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionMapper {

    @Select("select * from tb_permission where id = #{id}")
    Permission getPermission(Integer id);


    @Select("select * from tb_permission where role_name = #{roleName}")
    Set<Permission> getPermissionByRoleName(String roleName);

    @InsertProvider(type = PermissionProvider.class, method = "insertPermission")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addPermission(Permission permission);


    @UpdateProvider(type = PermissionProvider.class, method = "updatePermission")
    void updatePermission(Permission permission);
}

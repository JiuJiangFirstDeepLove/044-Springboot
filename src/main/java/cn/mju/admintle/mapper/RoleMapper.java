package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Role;
import cn.mju.admintle.provider.RoleProvider;
import cn.mju.admintle.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleMapper {

    @Select("select * from tb_role where role_name = #{roleName} order by user_id")
    List<Role> getRolesByName(String roleName);

    @Select("select * from tb_role where id = #{id}")
    Role getRole(Integer id);

    @Select("select * from tb_role where user_id = #{userId}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "roleName", column = "role_name"),
                    @Result(property = "permissions", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.PermissionMapper.getPermissionByRoleName", fetchType = FetchType.EAGER)
                    )
            })
    Set<Role> getRoleByUserId(Long userId);

    @InsertProvider(type = RoleProvider.class, method = "insertRole")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addRole(Role role);

    @UpdateProvider(type = RoleProvider.class, method = "updateRole")
    void updateRole(Role role);

    @Delete("delete from tb_role where user_id = #{userId}")
    int deleteRole(Long userId);

    @DeleteProvider(type = RoleProvider.class,method = "batchDelete")
    int deleteBatch(List<Long> ids);

}

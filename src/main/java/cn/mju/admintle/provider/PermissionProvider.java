package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Permission;
import org.apache.ibatis.jdbc.SQL;

public class PermissionProvider {
    public String insertPermission(final Permission permission) {
        return new SQL() {{
            INSERT_INTO("tb_permission");
            if (permission.getRoleName() != null) {
                VALUES("role_name", "#{roleName}");
            }
            if (permission.getPermissionName() != null) {
                VALUES("permission_name", "#{permissionName}");
            }

        }}.toString();
    }


    public String updatePermission(final Permission permission) {
        return new SQL() {{
            UPDATE("tb_permission");
            if ((permission.getRoleName() != null)) {
                SET("role_name = #{roleName}");
            }
            if (permission.getPermissionName() != null) {
                SET("permission_name = #{permissionName}");
            }

            WHERE("id = #{id}");
        }}.toString();

    }
}

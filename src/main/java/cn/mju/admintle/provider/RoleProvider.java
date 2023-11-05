package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Role;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class RoleProvider {
    public String insertRole(final Role role) {
        return new SQL() {{
            INSERT_INTO("tb_role");
            if (role.getUserId() != null) {
                VALUES("user_id", "#{userId}");
            }
            if (role.getRoleName() != null) {
                VALUES("role_name", "#{roleName}");
            }

        }}.toString();
    }


    public String updateRole(final Role role) {
        return new SQL() {{
            UPDATE("tb_role");
            if (role.getId() != null) {
                SET("id = #{id}");
            }
            if (role.getRoleName() != null) {
                SET("role_name = #{roleName}");
            }

            WHERE("user_id = #{userId}");
        }}.toString();

    }

    public String batchDelete(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_role WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }
}

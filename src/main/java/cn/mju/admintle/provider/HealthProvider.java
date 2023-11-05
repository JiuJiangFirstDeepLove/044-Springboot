package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Health;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class HealthProvider {

    /**
     * 动态插入
     * @param health
     * @return
     */
    public String insertHealth(final Health health) {
        return new SQL() {{
            INSERT_INTO("tb_health");
            if (health.getUserId() != null) {
                VALUES("user_id", "#{userId}");
            }if (health.getAddress() != null) {
                VALUES("address", "#{address}");
            }if (health.getTemp() != null) {
                VALUES("temp", "#{temp}");
            }if (health.getPath() != null) {
                VALUES("path", "#{path}");
            }if (health.getTouch() != null) {
                VALUES("touch", "#{touch}");
            }if (health.getState() != null) {
                VALUES("state", "#{state}");
            }if (health.getToday() != null) {
                VALUES("today", "#{today}");
            }


        }}.toString();
    }


    /**
     * 更新
     * @param
     * @return
     */
    public String updateHealth(final Health health) {
        return new SQL() {{
            UPDATE("tb_health");
            if (health.getUserId() != null) {
                SET("user_id = #{userId}");
            }
            if (health.getAddress() != null) {
                SET("address = #{address}");
            }if (health.getTemp() != null) {
                SET("temp = #{temp}");
            }if (health.getPath() != null) {
                SET("path = #{path}");
            }if (health.getTouch() != null) {
                SET("touch = #{touch}");
            }if (health.getState() != null) {
                SET("state = #{state}");
            }if (health.getToday() != null) {
                SET("today = #{today}");
            }

            WHERE("id = #{id}");
        }}.toString();

    }

    /**
     * 批量删除
     * @param map
     * @return
     */

    public String batchDelete(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_health WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }
}

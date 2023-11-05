package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Leave;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class LeaveProvider {
    public String insertLeave(final Leave leave) {
        return new SQL() {{
            INSERT_INTO("tb_leave");
            if (leave.getUserId() != null) {
                VALUES("user_id", "#{userId}");
            }
            if (leave.getBeginTime()!= null) {
                VALUES("begin_time", "#{beginTime}");
            }
            if (leave.getEndTime() != null) {
                VALUES("end_time", "#{endTime}");
            }
            if (leave.getReason() != null) {
                VALUES("reason", "#{reason}");
            }
            if (leave.getState() != null) {
                VALUES("state", "#{state}");
            }
        }}.toString();
    }


    public String updateLeave(final Leave leave) {
        return new SQL() {{
            UPDATE("tb_leave");
            if (leave.getUserId() != null) {
                SET("user_id = #{userId}");
            }
            if (leave.getBeginTime() != null) {
                SET("begin_time = #{beginTime}");
            }
            if (leave.getEndTime() != null) {
                SET("end_time = #{endTime}");
            }
            if (leave.getReason() != null) {
                SET("reason = #{reason}");
            }
            if (leave.getState() != null) {
                SET("state = #{state}");
            }

            WHERE("id = #{id}");
        }}.toString();

    }

    public String selectLeave(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM tb_leave WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("").append(ids.get(i)).append("");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }


    public String batchDeleteByUserId(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_leave WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }
}

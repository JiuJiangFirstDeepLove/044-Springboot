package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.File;
import cn.mju.admintle.domain.Role;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class FileProvider {
    public String insertFile(final File file) {
        return new SQL() {{
            INSERT_INTO("tb_file");
            if (file.getUserId() != null) {
                VALUES("user_id", "#{userId}");
            }
            if (file.getEntryTime() != null) {
                VALUES("entry_time", "#{entryTime}");
            }
            if (file.getQuitTime() != null) {
                VALUES("quit_time", "#{quitTime}");
            }
            if (file.getDeptName() != null) {
                VALUES("dept_name", "#{deptName}");
            }
            if (file.getJobName() != null) {
                VALUES("job_name", "#{jobName}");
            }

        }}.toString();
    }

    public String updateFile(final File file) {
        return new SQL() {{
            UPDATE("tb_file");
            if (file.getUserId() != null) {
                SET("user_id = #{userId}");
            }
            if (file.getEntryTime() != null) {
                SET("entry_time = #{entryTime}");
            }
            if (file.getQuitTime() != null) {
                SET("quit_time = #{quitTime}");
            }
            if (file.getDeptName() != null) {
                SET("dept_name = #{deptName}");
            }
            if (file.getJobName() != null) {
                SET("job_name = #{jobName}");
            }
            WHERE("id = #{id}");
        }}.toString();

    }

    public String selectFile(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM tb_file WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("").append(ids.get(i)).append("");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }

    public String batchDelete(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_file WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }
}

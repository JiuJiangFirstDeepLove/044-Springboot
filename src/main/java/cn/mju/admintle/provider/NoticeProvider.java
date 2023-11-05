package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class NoticeProvider {
    /**
     * 动态插入
     * @param
     * @return
     */
    public String insertNotice(final Notice notice) {
        return new SQL() {{
            INSERT_INTO("tb_notice");
            if (notice.getHead() != null) {
                VALUES("head", "#{head}");
            }
            if (notice.getContent() != null) {
                VALUES("content", "#{content}");
            }
            if (notice.getCreateTime() != null) {
                VALUES("create_time", "#{createTime}");
            }
            if (notice.getUserId() != null) {
                VALUES("user_id", "#{userId}");
            }

        }}.toString();
    }


    /**
     * 更新
     * @param
     * @return
     */
    public String updateNotice(final Notice notice) {
        return new SQL() {{
            UPDATE("tb_notice");
            if (notice.getHead() != null) {
                SET("head = #{head}");
            }
            if (notice.getContent() != null) {
                SET("content = #{content}");
            }
            if (notice.getCreateTime() != null) {
                SET("create_time = #{createTime}");
            }
            if (notice.getUserId() != null) {
                SET("user_id = #{userId}");
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

        List<Integer> ids = (List<Integer>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_notice WHERE id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }


    public String batchDeleteByUserId(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_notice WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }

}

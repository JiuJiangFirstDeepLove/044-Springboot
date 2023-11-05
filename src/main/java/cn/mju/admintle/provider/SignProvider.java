package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Sign;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class SignProvider {
        public String insertSign(final Sign sign) {
            return new SQL() {{
                INSERT_INTO("tb_sign");
                if (sign.getUserId() != null) {
                    VALUES("user_id", "#{userId}");
                }
                if (sign.getTime() != null) {
                    VALUES("time", "#{time}");
                }
            }}.toString();
        }



    public String batchDeleteByUserId(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_sign WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }


}

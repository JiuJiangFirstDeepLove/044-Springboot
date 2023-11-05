package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Role;
import cn.mju.admintle.domain.Wages;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class WagesProvider {
    /**
     * 添加
     * @param wages
     * @return
     */
    public String insertWages(final Wages wages) {
        return new SQL() {{
            INSERT_INTO("tb_wages");
            if (wages.getUserId() != null) {
                VALUES("user_id", "#{userId}");
            }
            if (wages.getBasicWages() != null) {
                VALUES("basic_wages", "#{basicWages}");
            }
            if (wages.getLivePay() != null) {
                VALUES("live_pay", "#{livePay}");
            }
            if (wages.getNightPay() != null) {
                VALUES("night_pay", "#{nightPay}");
            }
            if (wages.getSocialPay() != null) {
                VALUES("social_pay", "#{socialPay}");
            }
            if (wages.getRewardPay() != null) {
                VALUES("reward_pay", "#{nightPay}");
            }
            if (wages.getAbsenceFines() != null) {
                VALUES("absence_fines", "#{absenceFines}");
            }
            if (wages.getLateFines() != null) {
                VALUES("late_fines", "#{lateFines}");
            }
            if (wages.getRealWages() != null) {
                VALUES("real_wages", "#{realWages}");
            }
            if (wages.getPayDate() != null) {
                VALUES("pay_date", "#{payDate}");
            }

        }}.toString();
    }

    /**
     * 更新
     * @param wages
     * @return
     */
    public String updateWages(final Wages wages) {
        return new SQL() {{
            UPDATE("tb_wages");
            if (wages.getUserId() != null) {
                SET("user_id = #{userId}");
            }
            if (wages.getBasicWages() != null) {
                SET("basic_wages = #{basicWages}");
            }
            if (wages.getLivePay() != null) {
                SET("live_pay = #{livePay}");
            }
            if (wages.getNightPay() != null) {
                SET("night_pay = #{nightPay}");
            }
            if (wages.getSocialPay() != null) {
                SET("social_pay = #{socialPay}");
            }
            if (wages.getRewardPay() != null) {
                SET("reward_pay = #{rewardPay}");
            }
            if (wages.getAbsenceFines() != null) {
                SET("absence_fines = #{absenceFines}");
            }
            if (wages.getLateFines() != null) {
                SET("late_fines = #{lateFines}");
            }
            if (wages.getRealWages() != null) {
                SET("real_wages = #{realWages}");
            }
            if (wages.getPayDate() != null) {
                SET("pay_date = #{payDate}");
            }

            WHERE("user_id = #{userId}");
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

        sb.append("DELETE FROM tb_wages WHERE id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }

    public String selectWages(Map map) {

        List<Long> ids = (List<Long>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM tb_wages WHERE user_id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("").append(ids.get(i)).append("");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(") order by pay_date");

        return sb.toString();

    }
}

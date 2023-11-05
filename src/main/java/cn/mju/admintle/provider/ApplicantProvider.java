package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Applicant;
import cn.mju.admintle.domain.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class ApplicantProvider {
    /**
     * 动态插入
     * @param
     * @return
     */
    public String insertApplicant(final Applicant app) {
        return new SQL() {{
            INSERT_INTO("tb_app");
            if (app.getUsername() != null) {
                VALUES("username", "#{username}");
            }
            if (app.getDeptId() != null) {
                VALUES("dept_id", "#{deptId}");
            }
            if (app.getJobId() != null) {
                VALUES("job_id", "#{jobId}");
            }
            if (app.getExpWages() != null) {
                VALUES("exp_wages", "#{expWages}");
            }
            if (app.getComeDate() != null) {
                VALUES("come_date", "#{comeDate}");
            }
            if (app.getAddress() != null) {
                VALUES("address", "#{address}");
            }
            if (app.getBirthday() != null) {
                VALUES("birthday", "#{birthday}");
            }
            if (app.getEmail() != null) {
                VALUES("email", "#{email}");
            }
            if (app.getPhone() != null) {
                VALUES("phone", "#{phone}");
            }
            if (app.getResume() != null) {
                VALUES("resume", "#{resume}");
            }
            if (app.getState() != null) {
                VALUES("state", "#{state}");
            }

        }}.toString();
    }

    /**
     * 更新
     * @param
     * @return
     */
    public String updateApplicant(final Applicant app) {
        return new SQL() {{
            UPDATE("tb_app");
            if (app.getUsername() != null) {
                SET("username = #{username}");
            }
            if (app.getDeptId() != null) {
                SET("dept_id = #{deptId}");
            }
            if (app.getJobId() != null) {
                SET("job_id = #{jobId}");
            }
            if (app.getExpWages() != null) {
                SET("exp_wages = #{expWages}");
            }
            if (app.getComeDate() != null) {
                SET("come_date = #{comeDate}");
            }
            if (app.getAddress() != null) {
                SET("address = #{address}");
            }
            if (app.getBirthday() != null) {
                SET("birthday = #{birthday}");
            }
            if (app.getEmail() != null) {
                SET("email = #{email}");
            }
            if (app.getPhone() != null) {
                SET("phone = #{phone}");
            }
            if (app.getResume() != null) {
                SET("resume = #{resume}");
            }
            if (app.getState() != null) {
                SET("state = #{state}");
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

        sb.append("DELETE FROM tb_app WHERE id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }

    public String selectApps(Map map) {

        List<Integer> ids = (List<Integer>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM tb_app WHERE state IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("").append(ids.get(i)).append("");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(") ");

        return sb.toString();

    }




    /**
     * 动态查询
     */
    public String selectApp(Map<String,Object> params) {

        List<Integer> deptIds = (List<Integer>) params.get("deptId");
        List<Integer> jobIds = (List<Integer>) params.get("jobId");

        StringBuffer sql=new StringBuffer("select * from tb_app where 1 = 1"+" ");
        if (params.get("username") != null && !params.get("username").equals("")){
            sql.append("and username LIKE CONCAT ('%',#{username},'%') ");
        }
        if (params.get("deptId") != null ){
            sql.append("and dept_id in (");

            for (int i = 0; i < deptIds.size(); i++) {

                sql.append("'").append(deptIds.get(i)).append("'");

                if (i < deptIds.size() - 1)

                    sql.append(",");

            }

            sql.append(")");
        }
        if (params.get("jobId") != null ){
            sql.append("and job_id in (");

            for (int i = 0; i < jobIds.size(); i++) {

                sql.append("'").append(jobIds.get(i)).append("'");

                if (i < jobIds.size() - 1)

                    sql.append(",");

            }

            sql.append(")");
        }
        sql.append("and state IN (0,1) ");
        return sql.toString();
    }


}




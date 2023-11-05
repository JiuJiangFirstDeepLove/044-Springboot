package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class JobProvider {

    /**
     * 动态插入
     * @param job
     * @return
     */
    public String insertJob(final Job job) {
        return new SQL() {{
            INSERT_INTO("tb_dept");
            if (job.getJobName() != null) {
                VALUES("job_name", "#{jobName}");
            }

        }}.toString();
    }


    /**
     * 动态查询
     */

    public String selectJob(Map<String,Object> params) {

        StringBuffer sql=new StringBuffer("select * from tb_job  where 1 = 1"+" ");

        if (params.get("jobName") != null && !params.get("jobName").equals("")){
            sql.append("and job_name LIKE CONCAT ('%',#{jobName},'%') ");
        }
        return sql.toString();
    }
}

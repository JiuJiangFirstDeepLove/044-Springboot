package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DeptProvider {
    /**
     * 动态插入
     * @param dept
     * @return
     */
    public String insertDept(final Dept dept) {
        return new SQL() {{
            INSERT_INTO("tb_dept");
            if (dept.getDeptName() != null) {
                VALUES("dept_name", "#{deptName}");
            }
            if (dept.getBook() != null) {
                VALUES("book", "#{book}");
            }

        }}.toString();
    }

    /**
     * 动态查询
     */
    public String selectDept(Map<String,Object> params) {

        StringBuffer sql=new StringBuffer("select * from tb_dept  where 1 = 1"+" ");

        if (params.get("deptName") != null && !params.get("deptName").equals("")){
            sql.append("and dept_name LIKE CONCAT ('%',#{deptName},'%') ");
        }
        return sql.toString();
    }

    /**
     * 更新
     * @param
     * @return
     */
    public String updateDept(final Dept dept) {
        return new SQL() {{
            UPDATE("tb_dept");
            if (dept.getDeptName() != null) {
                SET("dept_name = #{deptName}");
            }
            if (dept.getBook() != null) {
                SET("book = #{book}");
            }
            WHERE("id = #{id}");
        }}.toString();

    }

}

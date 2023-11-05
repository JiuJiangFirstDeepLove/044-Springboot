package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.provider.DeptProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface DeptMapper {

    @Select("select * from tb_dept where id = #{id}")
    Dept getDeptById(int id);

    @Select("select * from tb_dept where id = #{id}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "deptName", column = "dept_name"),
                    @Result(property = "describe", column = "describe"),
                    @Result(property = "users", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.UserMapper.getUserByDeptId", fetchType = FetchType.EAGER)
                    )

            })
    Dept getDeptAndUser(int id);

    @Select("select * from tb_dept")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "deptName", column = "dept_name"),
                    @Result(property = "describe", column = "describe"),
                    @Result(property = "users", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.UserMapper.getUserByDeptId", fetchType = FetchType.EAGER)
                    )

            })
    List<Dept> getDeptData();


    @SelectProvider(type = DeptProvider.class,method = "selectDept")
    List<Dept> getDeptByName(HashMap<String, Object> map);

    @InsertProvider(type = DeptProvider.class, method = "insertDept")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addDept(Dept dept);

    @UpdateProvider(type = DeptProvider.class, method = "updateDept")
    int updateDept(Dept dept);


}

package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.User;
import cn.mju.admintle.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from tb_user")
    List<User> getUsers();


    @Select("select * from tb_user where state = #{state}")
    List<User> getUsersByState(int state);

    @Select("select * from tb_user where username = #{username}")
    User getUserByUsername(String username);

    @Select("select * from tb_user where job_id = #{jobId}")
    User getUserByJobId(int jobId);

    @Select("select * from tb_user where dept_id = #{deptId}")
    User getUserByDeptId(int deptId);

    @Select("select * from tb_user where username = #{username} and dept_id = #{deptId} and job_id = #{jobId}")
    User getUserByNameDeptJob(@Param("username") String username,@Param("deptId") int deptId,@Param("jobId") int jobId);

    @Select("select * from tb_user where id = #{id}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "birthday", column = "birthday"),
                    @Result(property = "phone", column = "phone"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "deptId", column = "dept_id"),
                    @Result(property = "jobId", column = "job_id"),
                    @Result(property = "state", column = "state"),
                    @Result(property = "files", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.FileMapper.getFileByUserId", fetchType = FetchType.EAGER)
                    )

            })
    User getUserById(long id);



    @Select("select * from tb_user where id = #{id}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "birthday", column = "birthday"),
                    @Result(property = "phone", column = "phone"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "deptId", column = "dept_id"),
                    @Result(property = "jobId", column = "job_id"),
                    @Result(property = "state", column = "state"),
                    @Result(property = "roles", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.RoleMapper.getRoleByUserId", fetchType = FetchType.EAGER)
                    )

            })
    User getUserAndRoleById(long id);


    @InsertProvider(type = UserProvider.class, method = "insertUser")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addUser(User user);


    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int updateUser(User user);

    @SelectProvider(type = UserProvider.class,method = "selectUser")
    List<User> getUserByName(HashMap<String, Object> map);

    @Delete("delete from tb_user where id = #{id}")
    int deleteUser(Long id);

    @DeleteProvider(type = UserProvider.class,method = "batchDelete")
    int deleleteBatch(List<Long> ids);




}

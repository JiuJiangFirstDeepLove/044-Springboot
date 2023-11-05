package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Job;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.provider.JobProvider;
import cn.mju.admintle.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface JobMapper {

    @Select("select * from tb_job where id = #{id}")
    Job getJobById(int id);


    @Select("select * from tb_job where id = #{id}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "jobName", column = "job_name"),
                    @Result(property = "users", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.UserMapper.getUserByJobId", fetchType = FetchType.EAGER)
                    )

            })
    Job getJobAndUser(int id);

    @Select("select * from tb_job")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "jobName", column = "job_name"),
                    @Result(property = "users", column = "id",
                            many = @Many(select = "cn.mju.admintle.mapper.UserMapper.getUserByJobId", fetchType = FetchType.EAGER)
                    )

            })
    List<Job> getJobData();



   @SelectProvider(type = JobProvider.class,method = "selectJob")
   List<Job> getJobByName(HashMap<String, Object> map);



    @InsertProvider(type = JobProvider.class, method = "insertJob")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addJob(Job job);
}

package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Applicant;
import cn.mju.admintle.provider.ApplicantProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ApplicantMapper {

    @Select("select * from tb_app")
    List<Applicant> getAll();

    @Select("select * from tb_app where id = #{id}")
    Applicant getApplicantById(long id);

    @Select("select * from tb_app where state = #{state}")
    List<Applicant> getApplicantByState(int state);

    @SelectProvider(type = ApplicantProvider.class,method = "selectApps")
    List<Applicant> getApps(List<Integer> ids);

    @InsertProvider(type = ApplicantProvider.class, method = "insertApplicant")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addApplicant(Applicant applicant);

    @UpdateProvider(type = ApplicantProvider.class, method = "updateApplicant")
    int updateApplicant(Applicant applicant);

    @Delete("delete from tb_app where id = #{id}")
    int deleteApplicant(long id);

    @DeleteProvider(type = ApplicantProvider.class,method = "batchDelete")
    int deleleteBatch(List<Long> ids);

    @SelectProvider(type = ApplicantProvider.class,method = "selectApp")
    List<Applicant> getAppByCondition(HashMap<String, Object> map);
}

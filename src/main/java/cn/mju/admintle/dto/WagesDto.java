package cn.mju.admintle.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class WagesDto {

    //员工信息
    private Long id;
    @NotBlank
    private String username;
    private String deptName;
    private String jobName;
    //基本工资
    @NotNull
    private Integer basicWages;
    //交通房补
    @NotNull
    private Integer livePay;
    //加班补贴
    @NotNull
    private Integer nightPay;
    //奖金
    @NotNull
    private Integer rewardPay;
    //保险金
    @NotNull
    private Integer socialPay;
    //缺勤罚款
    @NotNull
    private Integer absenceFines;
    //迟到罚款
    @NotNull
    private Integer lateFines;
    //实际工资
    @NotNull
    private Integer realWages;
    //发放日期
    @NotNull
    private Date payDate;

    public WagesDto() {
    }

    public WagesDto(Long id,String username, String deptName, String jobName, Integer basicWages, Integer livePay, Integer nightPay, Integer rewardPay, Integer socialPay, Integer absenceFines, Integer lateFines, Integer realWages, Date payDate) {
        this.id = id;
        this.username = username;
        this.deptName = deptName;
        this.jobName = jobName;
        this.basicWages = basicWages;
        this.livePay = livePay;
        this.nightPay = nightPay;
        this.rewardPay = rewardPay;
        this.socialPay = socialPay;
        this.absenceFines = absenceFines;
        this.lateFines = lateFines;
        this.realWages = realWages;
        this.payDate = payDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getBasicWages() {
        return basicWages;
    }

    public void setBasicWages(Integer basicWages) {
        this.basicWages = basicWages;
    }

    public Integer getLivePay() {
        return livePay;
    }

    public void setLivePay(Integer livePay) {
        this.livePay = livePay;
    }

    public Integer getNightPay() {
        return nightPay;
    }

    public void setNightPay(Integer nightPay) {
        this.nightPay = nightPay;
    }

    public Integer getRewardPay() {
        return rewardPay;
    }

    public void setRewardPay(Integer rewardPay) {
        this.rewardPay = rewardPay;
    }

    public Integer getSocialPay() {
        return socialPay;
    }

    public void setSocialPay(Integer socialPay) {
        this.socialPay = socialPay;
    }

    public Integer getAbsenceFines() {
        return absenceFines;
    }

    public void setAbsenceFines(Integer absenceFines) {
        this.absenceFines = absenceFines;
    }

    public Integer getLateFines() {
        return lateFines;
    }

    public void setLateFines(Integer lateFines) {
        this.lateFines = lateFines;
    }

    public Integer getRealWages() {
        return realWages;
    }

    public void setRealWages(Integer realWages) {
        this.realWages = realWages;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
